package org.doit.ik;

import java.util.List;
import java.util.Locale;

import org.doit.ik.domain.DeptEmpSalgradeDTO;
import org.doit.ik.mapper.DeptEmpSalgradeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class DeptEmpSalgradeController {
	
	// @Autowired
	private DeptEmpSalgradeMapper deptEmpSalgradeMapper;
	
	@GetMapping(value = "/dept/emp")
	public void getDeptEmpSalgrade(Locale locale, Model model) {
		log.info("> DeptEmpSalgradeController.getDeptEmpSalgrade()...");		
		List<DeptEmpSalgradeDTO> list = this.deptEmpSalgradeMapper.getDeptEmpInfo();
		model.addAttribute("list", list);
	}
	
}
