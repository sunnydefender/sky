package com.sky.ico.service.service;

import com.sky.ico.service.data.dao.*;
import com.sky.ico.service.data.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MysqlIndependentService {

    @Autowired
    private EmailRegisterApplyMapper emailRegisterApplyMapper;

    @Autowired
    private UserLocalAuthMapper userLocalAuthMapper;

    @Autowired
    private UserRealAuthMapper userRealAuthMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PlatformEmailSendMapper platformEmailSendMapper;

    public void createUser(EmailRegisterApply emailRegisterApply,
                      UserLocalAuth userLocalAuth,
                      User user,
                      UserRealAuth userRealAuth) {

        emailRegisterApplyMapper.insert(emailRegisterApply);
        userLocalAuthMapper.insert(userLocalAuth);
        userMapper.insert(user);
        userRealAuthMapper.insert(userRealAuth);
    }

    public void insertPlatformEmailSend(PlatformEmailSend platformEmailSend) {
        platformEmailSendMapper.insert(platformEmailSend);
    }
}
