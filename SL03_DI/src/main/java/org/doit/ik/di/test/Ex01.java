package org.doit.ik.di.test;

import org.doit.ik.di.Record;
import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;

public class Ex01 {

	public static void main(String[] args) {
		// p40(42) 스프링을 사용하지 않고 객체 조립/사용하기 
		//         (성적정보를 입력받아서 출력하는 일)
		/*
		 * 1. Record.java        인터페이스
		 * 2. RecordImpl.java    구현 클래스
		 * 3. RecordView.java    인터페이스
		 * 4. RecordViewImpl.java구현 클래스
		 * */
		
		RecordImpl record = new RecordImpl();             // A
		
		// [1] 주입 방법 : 생성자 , 
		// RecordViewImpl rvi = new RecordViewImpl(record);    // B에게 A를 주입
		 
		// [2] 주입 방법 : setter
		RecordViewImpl rvi = new RecordViewImpl();    // B에게 A를 주입
		rvi.setRecord(record);
		
		rvi.input();  // 성적 입력
		rvi.output(); // 성적 출력
		
		System.out.println(" END. ");

	}

}







