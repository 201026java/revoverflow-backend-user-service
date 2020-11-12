package com.revature.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopController {

    Logger logger = LoggerFactory.getLogger(AopController.class);


    @Before(value = "within(com.revature..*)")
    public void logBefore(JoinPoint jp) {

    	// logger.info(" Method "+jp.toShortString()+" called on "+jp.getTarget()+" args:
    	// "+Arrays.toString(jp.getArgs()));
    	
    	
    	System.out.println("Before: method" + jp.toShortString());
    }

    @AfterReturning(value = "within(com.revature..*)", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {

	  
    	logger.info(" method " + jp.toShortString());
    	// if(result!=null) {
    	// logger.info(" method "+jp.toShortString()+"returned "+result.toString());
    	// }else {
    	// logger.info(" method "+jp.toShortString()+" returned "+" it returns null");
    	// }

    	
    	System.out.println("AfterReturning: method" + jp.toShortString());
    }

    @AfterThrowing(value = "within(com.revature..*)", throwing = "error")
    public void afterThrowing(JoinPoint jp, Throwable error) {

    	logger.error(" method " + jp.toShortString() + " error " + error.getMessage());


    	System.out.println("AfterThrowing: method" + jp.toShortString());
    }


}
