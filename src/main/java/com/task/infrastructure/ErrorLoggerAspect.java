package com.task.infrastructure;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorLoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorLoggerAspect.class);

    @AfterThrowing(pointcut = "within(com.task.application.*) || within(com.task.infrastructure.messaging.*)", throwing= "exception")
    public void logExceptionMessage(JoinPoint joinPoint, Exception exception) {
        LOGGER.error(">> Error occurred in {}, with message: {}",
                        joinPoint.getSignature().toLongString(),
                        exception.getMessage() == null ? "Internal error" : exception.getMessage());
    }

}
