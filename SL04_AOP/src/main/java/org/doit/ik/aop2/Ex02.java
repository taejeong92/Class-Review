package org.doit.ik.aop2;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {
		// 1. 스프링 API -> 스프링 AOP 구현		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop2/application-context.xml");		
		
		//CalculatorImpl2 calc = ctx.getBean("calcProxy", CalculatorImpl2.class);		
		Calculator calc = ctx.getBean("calcProxy", Calculator.class);
		
		//Calculator calc = ctx.getBean("calc", Calculator.class);
		
		System.out.println( calc.add(10, 20) );
		
		System.out.println(" end. ");

	}

}
