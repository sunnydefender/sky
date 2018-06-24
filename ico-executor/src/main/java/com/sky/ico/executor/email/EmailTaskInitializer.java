package com.sky.ico.executor.email;

import com.sky.framework.task.TaskManager;
import com.sky.framework.task.enums.ExecuteStrategy;
import com.sky.framework.task.handler.ITaskHandler;
import com.sky.ico.service.common.EmailExecuteContext;
import com.sky.ico.service.data.dao.PlatformEmailMapper;
import com.sky.ico.service.data.entity.PlatformEmail;
import com.sky.ico.service.enums.PlatformEmailGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by easyfun on 2018/6/4.
 */
@Component
public class EmailTaskInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTaskInitializer.class);

    @Resource(name = "emailSendHandler")
    private ITaskHandler emailSendHandler;

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private PlatformEmailMapper platformEmailMapper;

    @PostConstruct
    public void start() {
        EmailExecuteContext context = new EmailExecuteContext();
        context.handler = "EmailSendHandler";
        context.sleepMilis = 6000;
        context.executeStrategy = ExecuteStrategy.NORMAL;
        context.taskHandler = emailSendHandler;
        context.taskManager = taskManager;

        LOGGER.info("=============================>开始初始化邮箱处理器<=============================");
        PlatformEmailGroup[] platformEmailGroupList = PlatformEmailGroup.values();
        for (int i = 0; i < platformEmailGroupList.length; i++) {
            List<PlatformEmail> platformEmailList = platformEmailMapper.selectAllListByEmailGroup(platformEmailGroupList[i].name());
            int n = 0;
            for (PlatformEmail email : platformEmailList) {
                context.sleepMilis = email.getSendIntervalSeconds() * 1000;
                context.platformEmail = email;
                new Thread(new EmailExecuteRunnable(context), "email-" + platformEmailGroupList[i] + "-" + n++).start();
            }
        }
        LOGGER.info("=============================>结束初始化邮箱处理器<=============================");
    }

    public static String buildEmailPoolKey(String emailMode) {
        return "email." + emailMode;
    }
}
