package com.lxn.code.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
//        打印错误日志
        logger.error("Request URL:{}，Exception : {}",request.getRequestURL(),e);

//        判断是否有日志，判断是否是4xx页面(判断e.getClass()是否有第二个注解，没有就返回null)
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }


//          添加错误信息
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
//        跳转到指定错误页面
        mv.setViewName("error/error");
//          返回
        return mv;
    }
}
