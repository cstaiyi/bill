package com.zcit.common.aspect;

import com.zcit.common.utils.HttpContextUtil;
import com.zcit.common.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 控制器日志
 *
 * @author hifeng
 * @date 2018/7/27 15:58
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {
    @Pointcut("execution(* com.zcit.*.*.controller.*.*(..))")
    public void controllerAspect() {
    }

    @Before("controllerAspect()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 记录请求内容
        log.info("请求地址：" + request.getRequestURL().toString());
        log.info("HTTP METHOD:" + request.getMethod());
        log.info("CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("参数：" + Arrays.toString(joinPoint.getArgs()));
        log.info("IP:" + IpUtil.getIpAddress(request));
    }

    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void afterReturning(Object ret) {
        log.debug("返回值：" + ret);
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = point.proceed();
        log.info("耗时：" + (System.currentTimeMillis() - startTime));
        return proceed;
    }
}
