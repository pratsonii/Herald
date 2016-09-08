package com.pr.herald.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging 
{
	Logger log = Logger.getLogger(this.getClass());
	
	/**********  Point Cuts ***********/
	 @Pointcut("within(@org.springframework.stereotype.Service *)")
	private void serviceMethods(){}

	 @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	private void controllerMethods(){}

	
	
	/********** Advices **********/
	@Before("serviceMethods()")
	public void beforeServiceMethods(JoinPoint j)
	{
		log.info("--- Start Service: "+ j.getSignature().getName()+" ---" );
	}
	
//	@After("serviceMethods()")
//	public void afterServiceMethods(JoinPoint j)
//	{
//		log.info("--- End Service: "+ j.getSignature().getName()+" ---" );
//	}

	@Before("controllerMethods()")
	public void beforeControllerMethods(JoinPoint j)
	{
		log.info("--- Start Controller: "+ j.getSignature().getName()+" ---" );
	}
	
//	@After("controllerMethods()")
//	public void afterControllerMethods(JoinPoint j)
//	{
//		log.info("---   End Controller: "+ j.getSignature().getName()+" ---" );
//	}
}
