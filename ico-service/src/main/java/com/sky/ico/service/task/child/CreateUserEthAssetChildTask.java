package com.sky.ico.service.task.child;

import com.alibaba.fastjson.JSONObject;
import com.sky.framework.common.id.IdUtils;
import com.sky.framework.task.Task;
import com.sky.framework.task.childtask.ChildTaskContent;
import com.sky.framework.task.childtask.IChildTaskHandler;
import com.sky.framework.task.entity.TaskPO;
import com.sky.framework.task.handler.TaskExecuteResult;
import com.sky.ico.service.data.dao.UserAssetMapper;
import com.sky.ico.service.data.entity.User;
import com.sky.ico.service.data.entity.UserAsset;
import com.sky.ico.service.data.entity.builder.UserAssetBuilder;
import com.sky.ico.service.task.CreateUserAssetTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserEthAssetChildTask implements IChildTaskHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserEthAssetChildTask.class);

    @Autowired
    private UserAssetMapper userAssetMapper;

    @Override
    public TaskExecuteResult execute(ChildTaskContent content, TaskPO taskPO) {
        if (taskPO.getProgress() != CreateUserAssetTask.ChildProgress.CREATED_ETH_ASSET.getValue()) {
            return TaskExecuteResult.success(taskPO.getProgress());
        }
        User user = (User) content.get(CreateUserAssetTask.ContentId.USER.name());

        JSONObject address = createEthAddress();
        long assetId = IdUtils.getInstance().createUid();
        UserAsset userAsset = UserAssetBuilder.build(user, assetId);
        // TODO:
        userAssetMapper.insert(userAsset);
        return TaskExecuteResult.success(CreateUserAssetTask.ChildProgress.CREATED_BTC_ASSET.getValue());
    }

    private JSONObject createEthAddress() {
        return null;
    }
}

