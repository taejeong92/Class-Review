package org.doit.ik.di4.test;

import org.doit.ik.di4.RecordImpl4;
import org.doit.ik.di4.RecordViewImpl4;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {
		// p115 컴포넌트 스캔을 이용한 빈 자동 등록까지하고 + 자동 의존 주입
		String [] resourceLocations = {  "classpath:org/doit/ik/di4/application-context.xml" };
		GenericXmlApplicationContext ctx= new GenericXmlApplicationContext(resourceLocations);
		 //                                recordViewImpl4
		RecordViewImpl4 rvi =  ctx.getBean("rvi", RecordViewImpl4.class);
		
		rvi.input();  // 성적 입력
		rvi.output(); // 성적 출력

		System.out.println(" END. ");

	}

}
