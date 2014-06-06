package services.impl;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import services.Sleepable;


@Aspect
public class SleepHelper {
	
	public SleepHelper(){}
	
	
	@Pointcut("execution(* *.sleep())")
	public void sleeppoint(){}
	
	
	@Before("sleeppoint()")
	public void beforeSleep(){
		System.out.println("Before");
	}
	
	@AfterReturning("sleeppoint()")
	public void afterSleep(){
		System.out.println("After");
	}
	
}


