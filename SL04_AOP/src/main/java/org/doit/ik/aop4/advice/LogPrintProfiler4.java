package org.doit.ik.aop4.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

// Aspect 클래스
@Aspect  // <aop:aspect id="traceAspect" ref="logPrintProfiler4">
@Component
@Log4j
public class LogPrintProfiler4 {
	
	// <aop:pointcut expression="execution(* org.doit.ik.aop..*.*(*,*))" id="calcPointcut"/>
	@Pointcut("execution(* org.doit.ik.aop..*.*(*,*))")
	private void calcPointcut() {}
	
	// p217(219) befere advice
	@Before("calcPointcut()")
	public void before(
			JoinPoint joinpoint
			) throws Throwable {
		String methodName = joinpoint.getSignature().getName();
		log.info(">> " + methodName +"() : LogPrintProfiler3.before() 호출됨...");
	}
	
	// p218 after returning Advice 
	// 주의할 점	returning="이름"은 메서드 파라미터 이름과 정확히 일치해야 함
	@AfterReturning( pointcut = "calcPointcut()" , returning = "result" )
	public void afterReturning(JoinPoint joinpoint, Object result) {  // int result
		String methodName = joinpoint.getSignature().getName();
		log.info("<< " + methodName +"() : LogPrintProfiler3.afterReturning() 호출됨..." + result );
	}
 
	
	// p222 around advice
	// <aop:around method="trace" pointcut-ref="calcPointcut" />
	@Around("calcPointcut()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		
		StopWatch sw = new StopWatch();
		sw.start();
		                    //     method
		String methodName = joinPoint.getSignature().getName();
		
		log.info("> " + methodName + "() start.");
		
		Object result = joinPoint.proceed(); // target
		
		log.info("> " + methodName + "() stop.");
		sw.stop();
		log.info("> " + methodName + "() 처리 시간 : " + sw.getTotalTimeMillis()+"ms");
		return result;
		
	}

}
