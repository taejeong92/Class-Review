package org.doit.ik;

import org.doit.ik.service.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class EmpController {
	
	private EmpService empService;
	
	@GetMapping(value = "/board/contrast")
	public void contrast(Model model) {
		log.info("> BoardController.contrast().... GET");
		model.addAttribute("list", this.empService.getContrast());
	}
}
