package com.nhnent.study.springcore.aspect;

import com.nhnent.study.springcore.vo.Member;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);


    @AfterReturning(value = "execution(* com.nhnent.study.springcore.repository.MemberRepository.save(..))", returning = "member")
    public void newMemberInserted(JoinPoint joinPoint, Member member) {
        LOGGER.error("new member inserted: {}", member.getEmail());
    }

    @Around(value = "execution(* com.nhnent.study.springcore.service.*ServiceImpl.*(..))")
    public Object printLogActivityAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object ret = joinPoint.proceed();

        long end = System.currentTimeMillis();

        String className = joinPoint.getTarget().getClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1);

        String methodName = joinPoint.getSignature().getName();

        LOGGER.error("elapsed time: {}.{} - {}ms", className, methodName, (end - start));

        return ret;
    }

}
