package com.example.demo.aop.aspect;

import static com.example.demo.constant.AOPConst.Controller_ORDER;
import static com.example.demo.constant.AOPConst.POINTCUT_CONTROLLERLAYER;
import static com.example.demo.constant.AOPConst.POINTCUT_SERVICELAYER;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.util.JsonUtil;

@Component
@Aspect
@Order(Controller_ORDER)
public class LoggerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

//    @Before(value = POINTCUT_CONTROLLERLAYER)
//    public void logBeforeController(JoinPoint joinPoint) {
//        String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
//        String signatureName = joinPoint.getSignature().getName();
//        String methodName = String.format("%s：%s ", targetClassName, signatureName);
//        String args = Arrays.stream(joinPoint.getArgs())
//                .filter(e -> !(e instanceof byte[]))
//                .map(JsonUtil::convertObjectToJsonString)
//                .collect(Collectors.joining(", "));
//
//        LOGGER.info("{}({}) Start...", methodName, args);
//    }
//
//    @After(value = POINTCUT_CONTROLLERLAYER)
//    public void logAfterService(JoinPoint joinPoint) {
//        String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
//        String signatureName = joinPoint.getSignature().getName();
//        String methodName = String.format("%s：%s ", targetClassName, signatureName);
//
//        LOGGER.info("{}() End...", methodName);
//    }

    @Around(value = POINTCUT_CONTROLLERLAYER + "||" + POINTCUT_SERVICELAYER)
    public Object logAroundService(ProceedingJoinPoint jp) throws Throwable {
        String targetClassName = jp.getTarget().getClass().getSimpleName();
        String signatureName = jp.getSignature().getName();
        String methodName = String.format("%s：%s ", targetClassName, signatureName);
        String args = Arrays.stream(jp.getArgs())
                .filter(e -> !(e instanceof byte[]))
                .map(JsonUtil::convertObjectToJsonString)
                .collect(Collectors.joining(", "));
        
        /**Controller 開始執行 (@Before)*/
        LOGGER.info("{}({}) Start...", methodName, args);
        
        long beginTime = System.currentTimeMillis();
        
        /**Controller 執行中(try catch)*/
        Object result = jp.proceed();
        long serviceCostTime = System.currentTimeMillis() - beginTime;
        LOGGER.info("{}() cost time: {}", methodName, serviceCostTime);

        /**Controller 執行結束 (@After)*/
        LOGGER.info("{}() End...", methodName);
        
        return result;
    }
}
