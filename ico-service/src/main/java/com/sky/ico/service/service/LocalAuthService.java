package com.sky.ico.service.service;

import com.sky.framework.common.dto.base.BaseResultDTO;
import com.sky.framework.common.id.IdUtils;
import com.sky.framework.task.Task;
import com.sky.framework.task.TaskManager;
import com.sky.ico.service.data.entity.*;
import com.sky.ico.service.data.entity.builder.*;
import com.sky.ico.service.dto.EmailRegisterParamDTO;
import com.sky.ico.service.enums.*;
import com.sky.ico.service.task.CreateUserAssetTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class LocalAuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalAuthService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EmailVerificationCodeService emailVerificationCodeService;

    @Autowired
    private MysqlIndependentService mysqlIndependentService;

    @Autowired
    private TaskManager taskManager;

    public BaseResultDTO emailRegister(EmailRegisterParamDTO paramDTO, String registerIp) {
        Date current = new Date();

        userService.verifyUserNotExistedByEmail(paramDTO.getEmail());
        emailVerificationCodeService.verify(paramDTO.getEmailVerificationCode(), BusinessMode.REGISTER, paramDTO.getEmail());

        // TODO: 注册频率控制

        long applyId = IdUtils.getInstance().createFlowId();
        long userId = IdUtils.getInstance().createPrimaryKeyId();
        String userPwd = paramDTO.getUserPwd();
        String tradePwd = userPwd;

        EmailRegisterApply emailRegisterApply = EmailRegisterApplyBuilder.build(paramDTO, applyId, userId, userPwd, tradePwd, current, registerIp);

        UserLocalAuth userLocalAuth = UserLocalAuthBuilder.build(emailRegisterApply);

        User user = UserBuilder.build(emailRegisterApply, current);

        UserRealAuth userRealAuth = UserRealAuthBuilder.build(user);

        mysqlIndependentService.createUser(emailRegisterApply, userLocalAuth, user, userRealAuth);

        asyncCreateUserAsset(user);
        return BaseResultDTO.success();
    }

    private void asyncCreateUserAsset(User user) {
        Task task = new Task();
        task.setTaskKey(String.valueOf(user.getUserId()));
        // TODO:异步创建用户资产记录
        task.setHandler(CreateUserAssetTask.class.getSimpleName());
        taskManager.pushTask(task);
    }
}
