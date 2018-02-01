package servlets;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class LoggingAspect {

	@Before("execution(* services.EntrepriseService.createEntreprise(..))")
	public void logBefore(JoinPoint joinPoint) {

		System.out.println("logBefore() is running with AspectJ");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}
	
	
	
	@After("execution(* services.EntrepriseService.createEntreprise(..))")
	public void logAfter(JoinPoint joinPoint) {

		System.out.println("logAfter() is running with AspectJ");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}

}