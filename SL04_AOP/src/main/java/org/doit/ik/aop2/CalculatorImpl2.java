package org.doit.ik.aop2;

import org.doit.ik.aop.Calculator;
import org.springframework.stereotype.Component;

@Component("calc")
public class CalculatorImpl2 implements Calculator{

	@Override
	public int add(int x, int y) {  
		int result = x + y; // 핵심 관심 사항 
		return result;
	}

	@Override
	public int sub(int x, int y) {  
		int result = x - y;  // 핵심 관심 사항
		return result;
	}

	@Override
	public int mul(int x, int y) {  
		int result = x * y;   // 핵심 관심 사항
		return result;
	}

	@Override
	public int div(int x, int y) {  
		int result = x / y;   // 핵심 관심 사항
		return result;
	}

}
