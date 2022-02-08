package com.lixinming.test.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lixinming
 * @Date 2022/1/27 15:01
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class LimitAspect {

    private static AtomicInteger limitCount = new AtomicInteger(0);
    @Pointcut("@annotation(com.lixinming.test.util.AccessLimit)")
    public void pointcut() {
        // do nothing
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        AccessLimit limitAnnotation = method.getAnnotation(AccessLimit.class);
        String name = limitAnnotation.name();
        int count = limitAnnotation.count();
        limitCount.getAndAdd(1);
        log.info("第{}次，描述为 [{}] 的接口", limitCount.get(),  name);
        if (limitCount.get() <= count) {
            return point.proceed();
        } else {
            throw new Exception("接口访问超出频率限制");
        }
}

}
