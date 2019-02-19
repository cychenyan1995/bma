package com.glsx.oms.bigdata.admin.bma.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.glsx.oms.bigdata.admin.bma.vo.RespMessage;
import com.glsx.oms.bigdata.admin.bma.vo.RespStatusEnum;

/**
 * AOP统一处理
 *
 * @Author yangbin
 * @Date 11:41 2018/7/31
 * @Version 1.0
 */
@Aspect
@Component
public class SystemAspect {

    private static final Logger logger = LoggerFactory.getLogger(SystemAspect.class);

    /**
     * Controller层切点
     *
     * @Author yangbin
     * @Date 11:41 2018/7/31
     * @Params []
     * @Return void
     */
    @Pointcut("execution (* com.glsx.oms.bigdata.admin.pa.deviceDetail.controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知
     *
     * @Author yangbin
     * @Date 11:41 2018/7/31
     * @Params [joinPoint]
     * @Return void
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
    }

    /**
     * 环绕通知
     *
     * @Author yangbin
     * @Date 11:42 2018/7/31
     * @Params [joinPoint]
     * @Return java.lang.Object
     */
    @Around("controllerAspect()")
    public Object around(JoinPoint joinPoint) {
        RespMessage rm = new RespMessage();
        try {
            return ((ProceedingJoinPoint) joinPoint).proceed();
        } catch (Throwable e) {
            logger.error(getMethodOfController(joinPoint), e);
            rm.setStatus(RespStatusEnum.SYSTEM);
        }
        return rm;
    }

    /**
     * 后置通知
     *
     * @Author yangbin
     * @Date 11:42 2018/7/31
     * @Params [joinPoint]
     * @Return void
     */
    @After(value = "controllerAspect()")
    public void after(JoinPoint joinPoint) {
    }

    /**
     * 后置返回通知
     *
     * @Author yangbin
     * @Date 11:42 2018/7/31
     * @Params [joinPoint]
     * @Return void
     */
    @AfterReturning(value = "controllerAspect()")
    public void afterReturn(JoinPoint joinPoint) {
    }

    private String getMethodOfController(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
    }
}
