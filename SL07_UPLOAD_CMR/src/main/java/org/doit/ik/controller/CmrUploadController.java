package org.doit.ik.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.doit.ik.domain.Message;
import org.doit.ik.domain.MultiMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cmr/*")
public class CmrUploadController {
	
	//  /cmr/multiupload  컨트롤러 메서드 
	@GetMapping("/multiupload")	
	public void multiupload(){ }
	
	@PostMapping("/multiupload")	
	public void multiupload( MultiMessage multiMessage ){  // 첨부파일이 커맨드 객체 저장. 
		log.info("> CmrUploadController.multiupload()... + POST");
		log.info("-".repeat(30));
		// 1. <input type="text" name="output" value="hello world!">
		String output = multiMessage.getOutput();
		log.info("1. output : " + output);
		
		// 2. <input type="file" name="attach" multiple="multiple">
		List<CommonsMultipartFile> attachList = multiMessage.getAttach();
		
		for (CommonsMultipartFile attach : attachList) {
			if ( !attach.isEmpty() ) {  // 업로드된 파일 있니? 
				log.info("-".repeat(30));
				String originalFileName = attach.getOriginalFilename();
				log.info("2. originalFileName : " + originalFileName);
				long fileSize = attach.getSize();
				log.info("3. Size : " + fileSize);
				
				// 업로드된 파일 저장.
				String parent = "C:\\upload"; 
				File dest = new File(parent, originalFileName);
				try {
					attach.transferTo(dest);
				} catch (IllegalStateException | IOException e) {
					// e.printStackTrace();
					log.info(e.toString());
				}
			} // if	
		} // foreach 문
		
		
		
		log.info(" end. ");
	}
	
	
	//   /cmr/upload  컨트롤러 메서드 
	@GetMapping("/upload")	
	public void upload(){ }
	
	@PostMapping("/upload")	
	public void upload( Message message ){  // 첨부파일이 커맨드 객체 저장. 
		log.info("> CmrUploadController.upload()... + POST");
		log.info("-".repeat(30));
		// 1. <input type="text" name="output" value="hello world!">
		String output = message.getOutput();
		log.info("1. output : " + output);
		
		// 2. <input type="file" name="attach">
		MultipartFile attach = message.getAttach();
		if ( !attach.isEmpty() ) {  // 업로드된 파일 있니? 
			log.info("-".repeat(30));
			String originalFileName = attach.getOriginalFilename();
			log.info("2. originalFileName : " + originalFileName);
			long fileSize = attach.getSize();
			log.info("3. Size : " + fileSize);
			
			// 업로드된 파일 저장.
			String parent = "C:\\upload"; 
			File dest = new File(parent, originalFileName);
			try {
				attach.transferTo(dest);
			} catch (IllegalStateException | IOException e) {
				// e.printStackTrace();
				log.info(e.toString());
			}
		} // if	
		
		log.info(" end. ");
	}
	
}












