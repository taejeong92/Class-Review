package org.doit.ik.aop3.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.doit.ik.aop2.advice.LogPrintAroundAdvice;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class LogPrintProfiler3 {
	// p217(219) befere advice
	public void before(
			JoinPoint joinpoint
			) throws Throwable {
		String methodName = joinpoint.getSignature().getName();
		log.info(">> " + methodName +"() : LogPrintProfiler3.before() 호출됨...");
	}
	
	// p218 after returning Advice
	public void afterReturning(JoinPoint joinpoint, Object result) {  // int result
		String methodName = joinpoint.getSignature().getName();
		log.info("<< " + methodName +"() : LogPrintProfiler3.afterReturning() 호출됨..." + result );
	}
	
	// p222 around advice
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
