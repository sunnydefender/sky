package com.sky.ico.web.controller;

import com.sky.framework.common.dto.base.BaseResultDTO;
import com.sky.framework.common.exception.ErrorCode;
import com.sky.ico.service.dto.EmailRegisterParamDTO;
import com.sky.ico.service.service.LocalAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by sunnydefender on 2018/4/24.
 */
@RestController
@RequestMapping(value = "/local/auth")
public class LocalAuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalAuthController.class);

    @Autowired
    private LocalAuthService localAuthService;

    @RequestMapping(value = "/email/register", method = POST)
    @ResponseBody
    public BaseResultDTO emailRegister(@RequestBody EmailRegisterParamDTO paramDTO) {
        return localAuthService.emailRegister(paramDTO);
    }

    @RequestMapping(value = "/hello")
    @ResponseBody
    public BaseResultDTO helloAuth() {
        BaseResultDTO resultDTO = new BaseResultDTO();
        resultDTO.setResult(ErrorCode.FAIL);
        resultDTO.setFailCode("0x00001");
        resultDTO.setFailReason("test fail");
        return resultDTO;
    }
}