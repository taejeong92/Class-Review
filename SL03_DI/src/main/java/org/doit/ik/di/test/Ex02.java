package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {
		// p42(44) 스프링을 사용해서 객체 조립/사용하기 ( 스프링 설정 만들기 )
		//         (성적정보를 입력받아서 출력하는 일) 

		// p59(61) ApplicationContext 종류 및 설명
		// application-context.xml : 스프링 빈(객체) 생성 + 조립 설정 XML 파일
		String [] resourceLocations = {  "classpath:org/doit/ik/di/application-context.xml" };
		GenericXmlApplicationContext ctx= new GenericXmlApplicationContext(resourceLocations);
		
		// RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("rvi");
		RecordViewImpl rvi =  ctx.getBean("rvi", RecordViewImpl.class);
		
		rvi.input();  // 성적 입력
		rvi.output(); // 성적 출력

		System.out.println(" END. ");

	}

}
