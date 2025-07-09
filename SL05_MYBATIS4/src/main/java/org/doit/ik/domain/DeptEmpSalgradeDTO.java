package org.doit.ik.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("DeptEmpSalgradeDTO")
public class DeptEmpSalgradeDTO {
	
	// There is no getter for property named 'deptDTO' 
	// in 'class org.doit.ik.domain.DeptEmpSalgradeDTO'

	// 1:1  관계(연관관계 ) DeptDTO
	private DeptDTO deptDTO;
	
	// 1:N 관계(연관관계)  EmpDTO
	//private List<EmpDTO> empList;
	private EmpDTO empDTO;
	
}
