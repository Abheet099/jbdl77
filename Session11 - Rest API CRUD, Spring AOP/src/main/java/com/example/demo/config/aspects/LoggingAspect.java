package com.example.demo.config.aspects;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {
    private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // Defines a pointcut that matches any public method in classes under
    // com.example.demo.controller package
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void allControllerMethods() {
    }

    // Defines a advice which will be executed at pointcut
    @Before("allControllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before Method: " + joinPoint.getSignature().getName());
        // Access HttpServletRequest
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            // Log the method name
            log.info("Before Method: {}", joinPoint.getSignature().getName());

            // Log request parameters
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                log.info("Request Parameter - {}: {}", paramName, paramValue);
            }
        } else {
            log.warn("HttpServletRequest not found. Unable to log request parameters.");
        }
    }

    // Defines a advice that runs after execution of methods matched by the pointcut
    // regardless of their outcome
    @After("allControllerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("After Method: " + joinPoint.toLongString());
    }

    // Code should contain proceed() on the ProceedingJoinPoint and it causes the underlying lines of code to execute.
    // @Around will be invoked first with the before invocation and then only @Before will be called.
    @Around("allControllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Around Method (Before): {}", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed(); // Proceed with the original method
        log.info("Around Method (After): {}", joinPoint.getSignature().getName());
        return result;
    }

    // This advice will run as a step after @After advice.
    // Usually, used to inform about the successful resultant of the method.
    @AfterReturning(pointcut = "allControllerMethods()", returning = "result")
    // Advice that runs after a method matched by the pointcut returns successfully
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method returned: " + result);
    }

    // Executed whenever there is an exception in the code. We need to handle that by putting try-catch blocks
    // and it is always a good practice to handle exceptions.
    @AfterThrowing(pointcut = "allControllerMethods()", throwing = "error")
    // Advice that runs if a method matched by the pointcut throws an exception
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("Method threw exception: " + error);
    }
}
