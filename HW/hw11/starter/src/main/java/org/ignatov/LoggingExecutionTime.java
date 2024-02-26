package org.ignatov;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.event.Level;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LoggingExecutionTime {

    private final LoggingExecutionTimeProperties properties;

    @Pointcut("within(@org.ignatov.aspect.Time *)")
    public void beansAnnotatedWith() {

    }

    @Pointcut("@annotation(org.ignatov.aspect.Time)")
    public void methodsAnnotatedWith() {

    }

    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object getStringAspect(ProceedingJoinPoint joinPoint) {
        Long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.atLevel(Level.ERROR).log("Ошибка выполнения замера времени: {} {}", e.getClass().getName(),
                    e.getMessage());
        }
        Long end = System.currentTimeMillis();
        log.atLevel(properties.getLogLevel()).log(
                "{} - {} # {} секунд",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                (end - start) / 1000
        );
        return result;
    }
}