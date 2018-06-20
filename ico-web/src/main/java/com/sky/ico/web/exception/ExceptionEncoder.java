package com.sky.ico.web.exception;

import com.sky.framework.common.dto.base.BaseResultDTO;
import com.sky.framework.common.exception.BusinessException;
import com.sky.framework.common.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

/**
 * 
 * @Description: 异常信息转成应答对象
 * @author sunnydefender
 * @version V1.0
 */
@ControllerAdvice
public class ExceptionEncoder {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionEncoder.class);
	
	@ResponseBody
	@ExceptionHandler(value = BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResultDTO handleBusinessException(BusinessException e) {
		LOGGER.error("[业务异常]", e);
		return BaseResultDTO.fail(e.getErrorCode().getFailCode(), e.getErrorMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(value = SystemException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public BaseResultDTO handleSystemException(SystemException e) {
		LOGGER.error("[系统异常]", e);
		return BaseResultDTO.fail(e.getErrorCode().getFailCode(), e.getErrorMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(value = ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResultDTO handleValidationException(ValidationException e) {
		LOGGER.error("[参数校验异常]", e);
		return BaseResultDTO.fail("90000001", e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResultDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		LOGGER.error("[转换JSON异常]", e);
		return BaseResultDTO.fail("90000001", e.getMessage());
	}

	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public BaseResultDTO handleUnknownxception(Exception e) {
		LOGGER.error("[系统异常]", e);
		return BaseResultDTO.fail("90000002", e.getMessage());
	}
}
