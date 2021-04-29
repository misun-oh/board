package jmp.spring.controller;

import java.io.File;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class AttachFileController {

	@PostMapping("/ajaxFileUpload")
	public void upload(MultipartFile[] uploadFile) {
		for(MultipartFile file : uploadFile) {
			log.info("=============="+file.getOriginalFilename());
			log.info("=============="+file.getSize());
			
			File saveFile = new File(file.getOriginalFilename());
		  	
			try {
				// 업로드한 파일 데이터를 지정한 파일에 저장한다.
		    	file.transferTo(saveFile);
			} catch (Exception e) {
			    	log.error(e.getMessage());
			}

		}
		
	}
}
