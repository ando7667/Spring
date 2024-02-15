package ru.gb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;


@Slf4j
@Aspect
@Component
public class AspectTimer {
   @Around("execution(* ru.gb.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature().getDeclaringTypeName() + " - " +
                joinPoint.getSignature().getName() + " # " + elapsedTime);
        return result;
    }
}