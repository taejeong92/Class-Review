package org.doit.ik.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.PageDTO;
import org.doit.ik.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	private BoardService boardService;

	/* [1] 페이징 처리 X - 컨트롤러 메서드이다.
	@GetMapping(value = "/list")
	public void list(Model model) {
		log.info("> BoardController.list()...");
		model.addAttribute("list", this.boardService.getList() );		
	}
	*/
	
	// [2] 페이징 처리 O - 컨트롤러 메서드 
	// http://localhost/board/list
	// http://localhost/board/list?pageNum=2&amount=10
	@GetMapping(value = "/list")
	public void list(Model model, Criteria criteria) {
		log.info("> BoardController.list()...");
		model.addAttribute("list", this.boardService.getListWithPaging(criteria) );
		// list.jsp 포워딩 : 페이징 블럭   1 2 [3] 4 5 6  6 7 8 9 10 >
		int total =  this.boardService.getTotal(criteria);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
	}

	@GetMapping(value = "/register")
	public void register() {
		log.info("> BoardController.register()... GET");			
	}

	@PostMapping(value = "/register")
	public String register(BoardVO boardVO, RedirectAttributes rttr ) {
		log.info("> BoardController.register()...POST");	
		this.boardService.register(boardVO);
		// rttr.addAttribute("result", 4);   // 쿼리스트링
		// rttr.addFlashAttribute("result", 6); // 세션 1회성
		rttr.addFlashAttribute("result", boardVO.getBno() ); // 세션 1회성

		return "redirect:/board/list";
		//		return "redirect:/board/list?error";
		//		return "redirect:/board/list?result=4";
		//		return "redirect:/board/list?success";
	}


	// [1] /board/get?bno=${ board.bno } + GET
	// [1] /board/modify?bno=${ board.bno } + GET
	@GetMapping(value = { "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model
			, @ModelAttribute("criteria") Criteria criteria) {
		log.info("> BoardController.get()...GET");	
		BoardVO boardVO = this.boardService.get(bno);
		model.addAttribute("boardVO", boardVO);
		
		// 이 코딩 필요없다. 이유? @ModelAttribute("criteria")
		// model.addAttribute("criteria", criteria);  
		// return "/board/get";
	} 

	/*
	//    /WEB-INF/views[/board/get/7].jsp
	// [2] /board/get/{ board.bno } + GET
	@GetMapping(value = "/get/{bno}")
	public String get(@PathVariable("bno") Long bno, Model model) {
		log.info("> BoardController.get()...GET");	
		BoardVO boardVO = this.boardService.get(bno);
		model.addAttribute("boardVO", boardVO);
		return "/board/get";
	}
	 */


	// 커맨드 객체 == BoardVO boardVO
	//  /board/modify + POST
	@PostMapping(value = "/modify")
	public String modify(BoardVO boardVO, RedirectAttributes rttr
			, @ModelAttribute("criteria") Criteria criteria) {
		log.info("> BoardController.modify()...POST");
		
		if( this.boardService.modify(boardVO) ) {
			rttr.addFlashAttribute("result", "SUCCESS");
		} // if
		
		// [1]
		// return String.format("redirect:/board/get?bno=%d", boardVO.getBno());
		
		// [2]
		rttr.addAttribute("bno", boardVO.getBno());  // ?bno=10
		
		rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
		rttr.addAttribute("type", criteria.getType());
	    rttr.addAttribute("keyword", criteria.getKeyword());
		
		//return "redirect:/board/get";
	    String queryString = criteria.getListLink();
	    return String.format("redirect:/board/get%s&bno=%d"
	    		, queryString, boardVO.getBno());
	}
	
	// [문제] ??? remove(???)  삭제할 글번호.
	// "action": "/board/remove","method": "get"
	@GetMapping("/remove")
	public String remove( @RequestParam("bno") Long bno
			, RedirectAttributes rttr 
			, @ModelAttribute("criteria") Criteria criteria) {
        log.info("> BoardController.remove()...GET");
		
		if( this.boardService.remove(bno) ) {
			rttr.addFlashAttribute("result", "REMOVESUCCESS");
			// rttr.addFlashAttribute("bno", bno);  // 1회성 세션
			rttr.addAttribute("bno", bno);  // ?bno=7
			
			// **
			int totalPages = (int)(Math.ceil((double)this.boardService.getTotal(criteria)/criteria.getAmount()));
			if( criteria.getPageNum() > totalPages ) criteria.setPageNum(totalPages == 0? 1:totalPages);
			// **
			
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
		} // if 
		
		return "redirect:/board/list";
	}
	
} // class








