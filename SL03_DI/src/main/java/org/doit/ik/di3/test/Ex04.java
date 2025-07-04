package org.doit.ik.di3.test;

import org.doit.ik.di4.RecordImpl4;
import org.doit.ik.di4.RecordViewImpl4;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		// p103 애노테이션을 이용한 객체 간 의존 자동 연결.
		String [] resourceLocations = {  "classpath:org/doit/ik/di3/application-context.xml" };
		GenericXmlApplicationContext ctx= new GenericXmlApplicationContext(resourceLocations);
		 
		RecordViewImpl4 rvi =  ctx.getBean("rvi", RecordViewImpl4.class);
		
		rvi.input();  // 성적 입력
		rvi.output(); // 성적 출력

		System.out.println(" END. ");

	}

}
