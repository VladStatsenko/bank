package org.statsenko.service.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class BankAspect {

    @Pointcut("@annotation(Loggable)")
    public void executeLogging(){
    }

    @SneakyThrows
    @Around("executeLogging()")
    public Object logMethod(ProceedingJoinPoint joinPoint){

        Object returnValue = joinPoint.proceed();
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> message.append(" args: ").append(arg));

        log.info(message.toString());

        return returnValue;
    }


}
