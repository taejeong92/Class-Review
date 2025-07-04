package org.doit.ik.di4;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RecordImpl4 implements Record4{
	
	private int kor;
	private int eng;
	private int mat;

	@Override
	public int total() { 
		return this.kor + this.eng + this.mat;
	}

	@Override
	public double avg() { 
		return this.total()/3.0;
	}

}
