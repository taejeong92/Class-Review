package org.doit.ik.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 공지사항 
@Controller
@RequestMapping("/customer/*")
public class CustomerController {

	@Autowired
	private NoticeDao noticeDao; 
	
	@GetMapping("/noticeDel.htm")
	public String noticeDel(
			@RequestParam("seq") String seq
			, RedirectAttributes rttr
			, Model model) throws ClassNotFoundException, SQLException {
		int rowCount = this.noticeDao.delete(seq); 
		rttr.addFlashAttribute("result", rowCount);
		return "redirect:notice.htm";
		
		// rttr.addAttribute("seq", seq);
		// return "redirect:noticeDetail.htm";
	}
	
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(
			NoticeVO notice
			, RedirectAttributes rttr
			, Model model) throws ClassNotFoundException, SQLException {
		int rowCount = this.noticeDao.update(notice);
		rttr.addFlashAttribute("result", rowCount);
		// rttr.addFlashAttribute("seq", notice.getSeq());   일회성
		// noticeDetail.htm?seq=3
		rttr.addAttribute("seq", notice.getSeq());
		return "redirect:noticeDetail.htm";
	}
	
	// <a class="btn-edit button" href="noticeEdit.htm?seq=${ notice.seq }">수정</a>
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(
			@RequestParam("seq") String seq
			, Model model) throws ClassNotFoundException, SQLException {
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		return "noticeEdit.jsp";
	}

	// <form action="" method="post">
	// [2]
	@PostMapping("/noticeReg.htm" )
	public String noticeReg(
			NoticeVO notice ,  // 커맨드 객체 p356   request 파라미터를 저장할 객체
			RedirectAttributes rttr
			) throws ClassNotFoundException, SQLException {
		
		int rowCount = this.noticeDao.insert(notice);		
		rttr.addFlashAttribute("result", rowCount);  
		return "redirect:notice.htm"; 
	}
	/* [1]
	@PostMapping("/noticeReg.htm" )
	public String noticeReg(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			RedirectAttributes rttr
			) throws ClassNotFoundException, SQLException {
		NoticeVO notice = new NoticeVO();
		notice.setTitle(title);
		notice.setContent(content);		
		int rowCount = this.noticeDao.insert(notice);		
		rttr.addFlashAttribute("result", rowCount); // 일회성		
		// 포워딩 - return "notice.jsp";
		// 리다이렉트 - redirect: 접두사
		return "redirect:notice.htm";
		// return "noticeReg.htm?error"
	}
	*/

	// <a class="btn-write button" href="noticeReg.htm">글쓰기</a>
	@GetMapping("/noticeReg.htm" )
	public String noticeReg(HttpSession session) {
		return "noticeReg.jsp";
	}

	// [2]
	// http://localhost/customer/noticeDetail.htm?seq=1
	@GetMapping("/noticeDetail.htm" )	
	public String noticeDetail(
			@RequestParam("seq") String seq
			, Model model) throws Exception {
		NoticeVO  notice  = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);		
		return "noticeDetail.jsp";
	}

	/* [1]
	// 공지사항 목록 요청 URL 
	// http://localhost/customer/noticeDetail.htm?seq=1
	@GetMapping("/noticeDetail.htm" )	
	public ModelAndView noticeDetail(
			HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		// 리턴자료형 : ModelAndView  p282
		ModelAndView mav = new ModelAndView("noticeDetail.jsp");		
		String seq = request.getParameter("seq"); 		
		NoticeVO  notice  = this.noticeDao.getNotice(seq);		
		mav.addObject("notice", notice); 			
		return mav;
	}
	 */

	// 컨트롤러 메서드  p356

	// [3]
	@GetMapping(value = "/notice.htm")
	public String notices(
			@RequestParam(value = "page", defaultValue = "1") int page , 
			@RequestParam(value = "field", defaultValue = "title") String field,
			@RequestParam(value = "query", defaultValue = "") String query,
			Model model) throws Exception {		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		model.addAttribute("list", list);
		model.addAttribute("message", "Hello World!");
		return "notice.jsp";
	}

	// [2]
	/*
	@GetMapping(value = "/notice.htm")
	public String notices(
			@RequestParam(value = "page", required = false) String ppage ,
			@RequestParam(value = "field", required = false) String pfield,
			@RequestParam(value = "query", required = false) String pquery,
			Model model) throws Exception {

		int page = 1;
		String field = "title";
		String query = "";

		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;

		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);

		model.addAttribute("list", list);
		model.addAttribute("message", "Hello World!");

		return "notice.jsp";
	}*/

	/* [1]
	// 공지사항 목록 요청 URL 
	// http://localhost/customer/notice.htm?page=2&field=검색조건&query=검색어
	//@RequestMapping(value = "/notice.htm" , method = RequestMethod.GET )
	@GetMapping("/notice.htm" )	
	public ModelAndView notices(
			HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		// 리턴자료형 : ModelAndView  p282
		ModelAndView mav = new ModelAndView();

		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");

		int page = 1;
		String field = "title";
		String query = "";

		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;

		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);

		mav.addObject("list", list);
		mav.addObject("message", "Hello World!");

		mav.setViewName("notice.jsp");

		return mav;
	}
	 */

} // class
