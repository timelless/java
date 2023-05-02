package com.example.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect // > Aspect - combination of pointcut and advice
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

//    Pointcut config could be set xxx.xxx.() or bean(*bean type*)
//    @Pointcut("execution(* com.example.aop.business.*.*(..))")
    @Before("execution(* com.example.aop.*.*.*(..))") // > pointcut
    // ProceedingJoinPoint - call .proceed()
    public void logMethodCall(JoinPoint joinPoint) {
        logger.info("Before aspect - method is called: {} with args: ", joinPoint, joinPoint.getArgs()); // > advice
    }

//    @AfterReturning
//    @AfterThrowing
//    @Around() - before and after
    @After("execution(* com.example.aop.*.*.*(..))")
    public void logMethodAfterCall(JoinPoint joinPoint) {
        logger.info("After aspect - method has executed", joinPoint);
    }
    // > weaver
}
