package ru.gb;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
@Component
public class DemoAspect {

  // pointcut
  @Pointcut("execution(* ru.gb.MyServiceBean.*(..))")
  public void myServiceBeanMethodsPointcut() {
  }

  // Advice
  // 1. Before
  // 2. AfterReturning
  // 3. AfterThrowing
  // 4. After
  // 5. Around

  @Before("myServiceBeanMethodsPointcut()")
  public void before(JoinPoint joinPoint) {
    log.info("before sign = {}, args = {}", joinPoint.getSignature(), joinPoint.getArgs()[0]);
  }

}
