package com.capgemini.wsb.fitnesstracker.aspect;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aspect for logging execution of service methods.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Logs method execution details before the method is executed.
     *
     * @param joinPoint The join point representing the method execution
     */
    @Before("execution(public * com.capgemini.wsb.fitnesstracker..*(..)) && @within(org.springframework.stereotype.Service)")
    public void logBefore(JoinPoint joinPoint) {
        String methodSignature = getMethodSignature(joinPoint);
        logger.info("Before: {}", methodSignature);
    }

    /**
     * Logs method execution details after the method is executed.
     *
     * @param joinPoint The join point representing the method execution
     * @param result The result of the method execution
     */
    @AfterReturning(pointcut = "execution(public * com.capgemini.wsb.fitnesstracker..*(..)) && @within(org.springframework.stereotype.Service)", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String methodSignature = getMethodSignature(joinPoint);
        logger.info("After: {} returned with value: {}", methodSignature, result);
    }

    /**
     * Constructs the method signature string.
     *
     * @param joinPoint The join point representing the method execution
     * @return The method signature string
     */
    private String getMethodSignature(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String returnType = joinPoint.getSignature().getDeclaringTypeName();
        String params = Arrays.stream(joinPoint.getArgs())
                .map(arg -> arg.getClass().getSimpleName() + " " + arg.toString())
                .collect(Collectors.joining(", "));
        return returnType + " " + className + "." + methodName + "(" + params + ")";
    }
}