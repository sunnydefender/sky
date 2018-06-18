package com.sky.ico.service.task;

import com.sky.framework.common.mybatis.IntegerValuedEnum;
import com.sky.framework.task.TaskHandler;
import com.sky.framework.task.childtask.ChildTaskContent;
import com.sky.framework.task.childtask.ChildTaskSequenceExecutor;
import com.sky.framework.task.entity.TaskPO;
import com.sky.framework.task.handler.ITaskHandler;
import com.sky.framework.task.handler.TaskExecuteResult;
import com.sky.ico.service.data.dao.UserMapper;
import com.sky.ico.service.data.entity.User;
import com.sky.ico.service.task.child.CreateUserBtcAssetChildTask;
import com.sky.ico.service.task.child.CreateUserEthAssetChildTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@TaskHandler
public class CreateUserAssetTask implements ITaskHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserAssetTask.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CreateUserEthAssetChildTask createUserEthAssetChildTask;

    @Autowired
    private CreateUserBtcAssetChildTask createUserBtcAssetChildTask;

    @Override
    public TaskExecuteResult execute(TaskPO taskPO, Object params) {
        long userId = Long.valueOf(taskPO.getTaskKey());
        User user = userMapper.selectByPrimaryKey(userId);

        if (null == user) {
            LOGGER.debug("用户不存在. userId={}", userId);
            return TaskExecuteResult.fail();
        }

        ChildTaskContent content = new ChildTaskContent();
        content.set(ContentId.USER.name(), user);
        content.addChildTask(createUserEthAssetChildTask, createUserBtcAssetChildTask);

        return ChildTaskSequenceExecutor.execute(content, taskPO);
    }

    public enum ChildProgress implements IntegerValuedEnum {
        // 开始
        NOT_EXECUTED(0),

        // 创建eth资产
        CREATED_ETH_ASSET(100),

        // 创建btc资产
        CREATED_BTC_ASSET(200),;

        private int value;

        private ChildProgress(int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    public enum ContentId {
        USER
    }

}
