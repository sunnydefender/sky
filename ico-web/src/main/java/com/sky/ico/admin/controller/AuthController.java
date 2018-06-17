package com.sky.ico.admin.controller;

import com.sky.framework.common.dto.base.BaseResultDTO;
import com.sky.framework.common.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by easyfun on 2018/4/24.
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/register", method = POST)
    @ResponseBody
    public BaseResultDTO register(/*@RequestBody MemberRegisterParamDTO paramDTO*/) {
//        LOGGER.info("paramDTO={}", JSON.toJSONString(paramDTO));
//        LOGGER.info("resultDTO={}", JSON.toJSONString(resultDTO));
//        return resultDTO;
        LOGGER.debug("hello");
        return null;
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