package com.lxn.code.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    private final Logger logger =LoggerFactory.getLogger(this.getClass());

//    定义切入点，拦截controller下的所有方法
    @Pointcut("execution(* com.lxn.code.controller.*.*(..))")
    public void log(){}

//    前置通知
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
//        返回地址
        String ip = request.getRemoteAddr();
//        返回方法类名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
//        返回方法参数
        Object[] args = joinPoint.getArgs();
        RequestLog log = new RequestLog(url,ip,classMethod,args);
        logger.info("Request:{}",log);
    }
//    后置通知
    @After("log()")
    public void doAfter(){
    }
//    最终通知 result：返回调用页面地址
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("Result:{}",result);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class  RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;


    }
}
