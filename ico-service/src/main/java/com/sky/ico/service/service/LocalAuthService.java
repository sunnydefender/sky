package com.sky.ico.service.service;

import com.sky.framework.common.dto.base.BaseResultDTO;
import com.sky.framework.common.id.IdUtils;
import com.sky.framework.task.Task;
import com.sky.framework.task.TaskManager;
import com.sky.ico.service.data.entity.*;
import com.sky.ico.service.data.entity.builder.*;
import com.sky.ico.service.dto.EmailRegisterParamDTO;
import com.sky.ico.service.enums.*;
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

    public BaseResultDTO emailRegister(EmailRegisterParamDTO paramDTO) {
        Date current = new Date();

        userService.verifyUserNotExistedByEmail(paramDTO.getEmail());
        emailVerificationCodeService.verify(paramDTO.getEmailVerificationCode(), BusinessMode.REGISTER, paramDTO.getEmail());

        long applyId = IdUtils.getInstance().createFlowId();
        long userId = IdUtils.getInstance().createUid();
        String userPwd = paramDTO.getUserPwd();
        String tradePwd = userPwd;
        String registerIp = "";

        EmailRegisterApply emailRegisterApply = EmailRegisterApplyBuilder.build(paramDTO);
        emailRegisterApply.setApplyId(applyId);
        emailRegisterApply.setUserId(userId);
        emailRegisterApply.setUserPwd(userPwd);
        emailRegisterApply.setTradePwd(tradePwd);
        emailRegisterApply.setRegisterTime(current);
        emailRegisterApply.setRegisterIp(registerIp);

        UserLocalAuth userLocalAuth = UserLocalAuthBuilder.build(emailRegisterApply);

        User user = UserBuilder.build(emailRegisterApply);
        user.setUserMode(UserMode.NORMAL.getValue());
        user.setAuthLevel(AuthLevel.NOT_AUTH.getValue());
        user.setEmailStatus(EmailStatus.USED.getValue());
        user.setEmailActiveTime(current);
        user.setMobileStatus(MobileStatus.NOT_USED.getValue());
        user.setUserStatus(UserStatus.USED.getValue());

        UserRealAuth userRealAuth = UserRealAuthBuilder.build(user);
        userRealAuth.setCardAuthStatu(CardAuthStatus.NOT_AUTH.getValue());

        mysqlIndependentService.createUser(emailRegisterApply, userLocalAuth, user, userRealAuth);

        asyncCreateUserAsset(user);
        return BaseResultDTO.success();
    }

    private void asyncCreateUserAsset(User user) {
        Task task = new Task();
        task.setTaskKey(String.valueOf(user.getUserId()));
        // TODO:异步创建用户资产记录
        task.setHandler("");
        taskManager.pushTask(task);
    }
}
