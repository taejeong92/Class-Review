package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.EmpMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class EmpServiceImpl implements EmpService {
	
	private EmpMapper empMapper;
	
	@Override
	public List<EmpDTO> getContrast() {
		log.info("> ContrastServiceImpl.getContrast()...");
		return this.empMapper.getContrast();
	}

}
