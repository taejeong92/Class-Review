package org.doit.ik.controller;

import java.io.File;
import java.util.List;

import org.doit.ik.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/ajax/*")
public class AjaxRestUploadController {
	
	// http://localhost/ajax/user
	// http://localhost/ajax/user.json
	// {"firstName":"John","lastName":"Doe","age":30}
	@GetMapping("/user")
    public User getUser() {
        return new User("John", "Doe", 30);
    }
	
	//    4가지 방법 중에 2번째 방법으로 처리.
	// p442 @RequestParam("attachList") 어노테이션을 사용한 파일 업로드 처리 방법
	@PostMapping("/uploadAjax")
	public void uploadAjax(
			@RequestParam("attachList") List<MultipartFile> attachList
			) {
		log.info("> AjaxRestUploadController.uploadAjax()... Post");	

		// C:\\upload 폴더 생성.
		String uploadFolder = "C:\\upload";

		for (MultipartFile attach : attachList) {
			if( ! attach.isEmpty() ) {	
				// 1. 첨부된 파일의 정보 출력
				log.info("-".repeat(30));
				log.info("> originalFilename : " + attach.getOriginalFilename());
				log.info("> size : " + attach.getSize());			 
				// 2. 첨부파일 저장
				File saveFile = new File(uploadFolder, attach.getOriginalFilename() );
				try {
					attach.transferTo(saveFile);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			} // if
		} // for		

		log.info(" = end ="); 
	}


}
