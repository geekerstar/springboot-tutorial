package com.geekerstar.aop.aspect;


import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author geekerstar
 * date: 2019-12-21 10:29
 * description: 利用AOP拦截请求，打印请求响应相关信息
 */
@Aspect
@Component
@Profile({"dev"})
public class WebLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 以自定义 @WebLog 注解为切点
     */
    @Pointcut("@annotation(com.geekerstar.aop.aspect.Weblog)")
    public void webLog() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        logger.info("****************************************** 【请求开始】 ******************************************");
        // 打印请求 url
        logger.info("** 【请求路径】         : {}", request.getRequestURL().toString());
        // 打印描述信息
        logger.info("** 【请求描述】         : {}", methodDescription);
        // 打印 Http method
        logger.info("** 【请求类型】         : {}", request.getMethod());
        // 打印调用 com.hantu.controller 的全路径以及执行方法
        logger.info("** 【调用方法】         : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.info("** 【请求IP地址】       : {}", request.getRemoteAddr());
        // 打印请求入参
        logger.info("** 【请求参数】         : {}", new Gson().toJson(joinPoint.getArgs()));
        // 记录dao层操作
        logger.info("------------------------------------------【数据层开始】------------------------------------------");
    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        logger.info("****************************************** 【请求结束】 ******************************************" + LINE_SEPARATOR);
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 记录dao层操作
        logger.info("------------------------------------------【数据层结束】------------------------------------------");
        // 打印出参
        logger.info("** 【响应参数】         : {}", new Gson().toJson(result));
        // 执行耗时
        logger.info("** 【请求耗时】         : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }


    /**
     * 获取切面注解的描述
     *
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     */
    public String getAspectLogDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(Weblog.class).description());
                }
            }
        }
        return description.toString();
    }

}
