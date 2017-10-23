package com.sijojosan.arya.core.anotation.processor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
@Aspect
public class AuditMethodAspect {

	private static final Logger logger = LoggerFactory.getLogger(AuditMethodAspect.class);

	private final Gson gson = new Gson();

	public AuditMethodAspect() {
		logger.info("Initializing " + this.getClass().getName());
	}

	@Before("@annotation(com.sijojosan.arya.core.annotation.AuditMethod)")
	public void parameterLogger(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Object[] parameters = joinPoint.getArgs();

		logger.info("Entering method - " + signature.getName());

		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				Object parameterValue = parameters[i];
				logger.info("Argument - " + parameterValue.getClass() + " - " + convertObject(parameterValue));
			}
		}
	}

	@AfterReturning(value = "@annotation(com.sijojosan.arya.core.annotation.AuditMethod)", returning = "returnValue")
	public void responseLogger(JoinPoint joinPoint, Object returnValue) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		if (returnValue != null) {
			logger.info("Returning - " + returnValue.getClass() + " - " + convertObject(returnValue));

		}
		logger.info("Exiting method - " + signature.getName());
	}

	private String convertObject(Object obj) {
		String json = gson.toJson(obj);
		return json;
	}
}
