package com.sky.ico.web.advice;

import com.alibaba.fastjson.JSON;
import com.sky.framework.common.utils.ValidateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.validation.ValidationException;

@Aspect
@Configuration
public class LogAdvice {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

	@Around(value = "execution(* com.sky.ico.service.service..*Service.*(..))")
	public Object serviceProcess(ProceedingJoinPoint point) throws ValidationException, Throwable {
		String method = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
		Object[] params = point.getArgs();

		Object[] filterParams = new Object[params.length];
		int i=0;
		for (Object param : params) {
			if (null == param) {
			} else if (param.getClass().getSimpleName().equals("RequestFacade")
					|| param.getClass().getSimpleName().equals("ResponseFacade")
					|| param.getClass().getSimpleName().equals("HttpServletResponse")
					|| param.getClass().getSimpleName().equals("HttpServletRequest")
					|| param.getClass().getSimpleName().equals("XssHttpServletRequestWrapper")
					|| param.getClass().getSimpleName().equals("byte[]")) {
				continue;
			}
			filterParams[i++] = param;
		}
		try {
			logger.info("[{}]请求参数:{}", method, JSON.toJSONString(filterParams));
		} catch (Exception e) {
			logger.error("[moniter]请求参数，method={}", method, e);
		}

		for (Object obj : params) {
			ValidateUtil.validate(obj);
		}
		Object returnValue = point.proceed(params);

		if (null == returnValue) {
			logger.info("[{}]返回结果:{}", method, JSON.toJSONString(returnValue));
		} else if (returnValue.getClass().getSimpleName().equals("ResponseFacade")
				|| returnValue.getClass().getSimpleName().equals("HttpServletResponse")
				|| returnValue.getClass().getSimpleName().equals("HttpServletRequest")
				|| returnValue.getClass().getSimpleName().equals("XssHttpServletRequestWrapper")
				|| returnValue.getClass().getSimpleName().equals("byte[]")) {
		} else {
			try {
				logger.info("[{}]返回结果:{}", method, JSON.toJSONString(returnValue));
			} catch (Exception e) {
				logger.error("[moniter]返回结果，method={}", method, e);
			}
		}

		return returnValue;
	}
	
//	private Object process(ProceedingJoinPoint point) throws ValidationException, Throwable {
//		String method = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
//		Object[] params = point.getArgs();
//		logger.info("[{}]请求参数:{}", method, JSON.toJSONString(params));
//		Object returnValue = point.proceed(params);
//		logger.info("[{}]返回结果:{}", method, JSON.toJSONString(returnValue));
//		return returnValue;
//	}
}
