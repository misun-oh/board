package jmp.spring.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class AttachFileController {

	@PostMapping("/ajaxFileUpload")
	public void upload(MultipartFile[] uploadFile) {
		String uploadPath = getFolder();
		for(MultipartFile file : uploadFile) {
			log.info("=============="+file.getOriginalFilename());
			log.info("=============="+file.getSize());
			
			// 소프트웨어 구축에 쓰이는 식별자 표준
			// UUID 표준에 따라 이름을 부여하면 고유성을 완벽하게 보장할 수는 없지만 실제 사용 상에서 중복될 가능성이 거의 없다고 인정!
			// 파일이름 뒤에 uuid를 붙여줍시다
			UUID uuid = UUID.randomUUID();
			
			String uploadFileName = uuid.toString()+"_"+file.getOriginalFilename();
			File saveFile = new File(uploadPath,uploadFileName);
		  	
			
			
			try {
				// 업로드한 파일 데이터를 지정한 파일에 저장한다.
		    	file.transferTo(saveFile);
			} catch (Exception e) {
			    	log.error(e.getMessage());
			}

		}
		
	}
	
	// 중복 방지용 업로드 날자를 폴더 이름으로 사용
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		String uploadPath = "C:\\upload\\" + str.replace("-", File.separator);
		File saveFile = new File(uploadPath);
		// 폴더가 없으면 생성 해줍니다!
		if(!saveFile.exists()) {
			if(saveFile.mkdirs()) {
				log.info("폴더 생성 완료");
			}else {
				log.info("폴더 생성 실패");
			}
		}
		return uploadPath;
	}
}
