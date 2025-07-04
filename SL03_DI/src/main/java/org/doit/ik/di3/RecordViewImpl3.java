package org.doit.ik.di3;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;

import lombok.Setter;

@Setter
public class RecordViewImpl3 implements RecordView3{
	
	// private RecordImpl record ;
	// 결합력이 높은 코딩은 좋은 코딩이 아니다. 
	// private RecordImpl record = new RecordImpl();
	@Autowired
	//@Autowired ( required = false )
	// X @Resource( name = "record2") 의존 주입 자동
	
	//@Inject
	//@Named( value = "record2")
	private RecordImpl3 record = null;

	public RecordViewImpl3() {  
	}
	public RecordViewImpl3(RecordImpl3 record) { 
		this.record = record;
	}

	@Override
	public void input() {
		try (Scanner scanner = new Scanner(System.in)){
			System.out.print("> kor, eng, mat input ? ");
			int kor = scanner.nextInt();
			int eng = scanner.nextInt();
			int mat = scanner.nextInt();
			
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void output() {
		System.out.printf("> kor=%d, eng=%d, mat=%d, tot=%d, avg=%.2f\n",
				this.record.getKor()
				, this.record.getEng()
				, this.record.getMat()
				, this.record.total()
				, this.record.avg()
				);
		
	}

}









