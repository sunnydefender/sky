package com.sky.ico.executor.email;

import com.alibaba.fastjson.JSONObject;
import com.sky.framework.common.utils.JsonUtil;
import com.sky.framework.task.QuartzHandler;
import com.sky.framework.task.handler.IQuartzHandler;
import com.sky.framework.task.util.DateUtil;
import com.sky.ico.service.data.dao.PlatformEmailMapper;
import com.sky.ico.service.data.entity.PlatformEmail;
import com.sky.ico.service.enums.EmailSendStatus;
import com.sky.ico.service.enums.PlatformEmailGroup;
import com.sky.ico.service.enums.PlatformEmailStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by easyfun on 2018/6/4.
 */
@QuartzHandler(initialDelay = 30, delay = 30, unit = TimeUnit.SECONDS)
public class EmailUnfreezeQuartzHandler implements IQuartzHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUnfreezeQuartzHandler.class);

    @Autowired
    private PlatformEmailMapper platformEmailMapper;

    @Override
    public void execute(Object params) {
        doJob();
    }

    private void doJob() {
        PlatformEmailGroup[] platformEmailGroupList = PlatformEmailGroup.values();
        for (int i = 0; i < platformEmailGroupList.length; i++) {
            List<PlatformEmail> platformEmailList = platformEmailMapper.selectAllListByEmailGroup(platformEmailGroupList[i].name());
            for (PlatformEmail entry : platformEmailList) {
                if (entry.getEmailStatus() == PlatformEmailStatus.FROZEN.getValue()) {
                    LOGGER.info("邮箱暂时不可用.platformEmail={}", JsonUtil.toJSONString(entry));

                    boolean unfreeze = checkUnfreeze(entry);
                    if (unfreeze) {
                        LOGGER.info("邮箱解冻成功.platformEmail={}", JsonUtil.toJSONString(entry));
                    }
                }
            }
        }
    }

    private boolean checkUnfreeze(PlatformEmail platformEmail) {
        Date current = new Date();
        PlatformEmail update = null;
        if (null == platformEmail.getFreezeEndTime()) {
            platformEmail.setFreezeStartTime(current);
            platformEmail.setFreezeEndTime(DateUtil.addSeconds(DateUtil.addDays(current, 1), 600));
            platformEmail.setUpdateTime(current);

            update = new PlatformEmail();
            update.setEmailId(platformEmail.getEmailId());
            update.setFreezeStartTime(platformEmail.getFreezeStartTime());
            update.setFreezeEndTime(platformEmail.getFreezeEndTime());
            update.setUpdateTime(platformEmail.getUpdateTime());
            platformEmailMapper.updateByPrimaryKeySelective(update);
            return false;
        }

        if (current.compareTo(platformEmail.getFreezeEndTime()) < 0) {
            return false;
        }

        platformEmail.setEmailStatus(PlatformEmailStatus.AVAILABLE.getValue());
        platformEmail.setFreezeStartTime(null);
        platformEmail.setFreezeEndTime(null);
        platformEmail.setUpdateTime(current);

        update = new PlatformEmail();
        update.setEmailId(platformEmail.getEmailId());
        update.setEmailStatus(platformEmail.getEmailStatus());
        update.setFreezeStartTime(platformEmail.getFreezeStartTime());
        update.setFreezeEndTime(platformEmail.getFreezeEndTime());
        update.setUpdateTime(platformEmail.getUpdateTime());
        platformEmailMapper.updateByPrimaryKeySelective(update);
        return true;
    }
}
