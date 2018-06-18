package com.sky.ico.service.service;

import com.sky.framework.common.exception.BusinessException;
import com.sky.ico.service.data.dao.UserMapper;
import com.sky.ico.service.data.entity.User;
import com.sky.ico.service.errorcode.CommonErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public void verifyUserNotExistedByEmail(String email) {
        if (isUserExistedByEmail(email)) {
            throw new BusinessException(CommonErrorCode.User.USER_EXISTED);
        }
    }

    public void verifyUserExistedByEmail(String email) {
        if (false == isUserExistedByEmail(email)) {
            throw new BusinessException(CommonErrorCode.User.USER_NOT_EXISTED);
        }

    }

    public boolean isUserExistedByEmail(String email) {
        User user = getUserByEmail(email);
        if (null != user) {
            return true;
        }
        return false;
    }

    public User getUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }
}
