package com.ic.annotation.aop;

import com.ic.annotation.Output;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect()
@Component("outputAspect")
public class OutputAspect {
    @Pointcut("@annotation(com.ic.annotation.Output)")
    private void pointCut1() {
    }
    @Around("pointCut1()")
    public void after(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("通知");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Output output = joinPoint.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(Output.class);
        if (output == null) {
            System.out.println("kong");
        } else {
            String value = output.value();
            System.out.println(value);
        }
        joinPoint.proceed();
    }
}
