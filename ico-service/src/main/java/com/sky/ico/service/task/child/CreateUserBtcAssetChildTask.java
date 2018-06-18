package com.sky.ico.service.task.child;

import com.sky.framework.task.childtask.ChildTaskContent;
import com.sky.framework.task.childtask.IChildTaskHandler;
import com.sky.framework.task.entity.TaskPO;
import com.sky.framework.task.handler.TaskExecuteResult;
import com.sky.ico.service.task.CreateUserAssetTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateUserBtcAssetChildTask implements IChildTaskHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserBtcAssetChildTask.class);

    @Override
    public TaskExecuteResult execute(ChildTaskContent content, TaskPO taskPO) {
        if (taskPO.getProgress() != CreateUserAssetTask.ChildProgress.NOT_EXECUTED.getValue()) {
            return TaskExecuteResult.success(taskPO.getProgress());
        }

        return TaskExecuteResult.success(CreateUserAssetTask.ChildProgress.CREATED_ETH_ASSET.getValue());
    }
}

