package com.sky.ico.admin.controller;

import com.sky.framework.common.dto.base.BaseResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by easyfun on 2018/4/24.
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/register", method = POST)
    @ResponseBody
    public BaseResultDTO agreementCallBackNotify(/*@RequestBody MemberRegisterParamDTO paramDTO*/) {
//        LOGGER.info("paramDTO={}", JSON.toJSONString(paramDTO));
//        LOGGER.info("resultDTO={}", JSON.toJSONString(resultDTO));
//        return resultDTO;
        return null;
    }
}