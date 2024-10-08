package zec.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotatedLoggingAspect {

    @Before("execution(public int zec.basics.services.impl.CalServiceImpl.*(..))")
    public void logBeforeCall(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName());
    }
}
