package io.github.zhiweicoding.aop;

import io.github.zhiweicoding.support.ResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Created by zhiwei on 2022/4/6.
 */
@Aspect
@Slf4j
@Component
public class ControllerAop {

    @Pointcut("execution(public * io.github.zhiweicoding.api.*.*(..))")
    public void apiPointCut() {
    }

    @Around("apiPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object res = null;
        long time = System.currentTimeMillis();
        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    request = (HttpServletRequest) arg;
                }
            }

            if (request != null) {
                String requestURI = request.getRequestURI();
                log.debug("请求地址：{}", requestURI);
                String remoteHost = request.getRemoteHost();
                log.debug("请求ip:{}", remoteHost);
                Map<String, String[]> parameterMap = request.getParameterMap();
                for (String key : parameterMap.keySet()) {
                    log.debug("参数：{}入参：{}", key, parameterMap.get(key));
                }

            }

            res = joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            log.debug("方法一共运行了：{}ms", time);
            return res;
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return new ResponseFactory<Map<String, Object>>().fail(null);
        }
    }

}
