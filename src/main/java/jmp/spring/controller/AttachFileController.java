package jmp.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jmp.spring.vo.AttachFileVo;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@RestController
@Log4j
public class AttachFileController {

    private static final String ATTACHES_DIR = "C:\\upload\\";

	@PostMapping("/ajaxFileUpload")
	public List<AttachFileVo> upload(MultipartFile[] uploadFile) {
		
		List<AttachFileVo> list = new ArrayList<AttachFileVo>();		
		String uploadPath = getFolder();
		
		for(MultipartFile file : uploadFile) {

			log.info("=============="+file.getOriginalFilename());
			log.info("=============="+file.getSize());
			
			try {
				
				
				// 소프트웨어 구축에 쓰이는 식별자 표준
				// UUID 표준에 따라 이름을 부여하면 고유성을 완벽하게 보장할 수는 없지만 실제 사용 상에서 중복될 가능성이 거의 없다고 인정!
				// 파일이름 뒤에 uuid를 붙여줍시다
				UUID uuid = UUID.randomUUID();
				
				String uploadFileName = uuid.toString()+"_"+file.getOriginalFilename();
				File saveFile = new File(ATTACHES_DIR+uploadPath+uploadFileName);
								
				// 업로드한 파일 데이터를 지정한 파일에 저장한다.
		    	file.transferTo(saveFile);
		    	
		    	// 이미지 파일인 경우 썸네일 생성하기 위해   
		    	String contentType = Files.probeContentType(saveFile.toPath());
		    	
		    	if(contentType.startsWith("image")) {
		    		String thmbnail = ATTACHES_DIR + uploadPath + "s_"+uploadFileName;
		    		Thumbnails.of(saveFile).size(100, 100).toFile(thmbnail);
		    	}
		    	
		    	AttachFileVo fileVo = new AttachFileVo();
		    	fileVo.setFileName(file.getOriginalFilename());
		    	fileVo.setUuid(uuid.toString());
		    	fileVo.setImage(contentType.startsWith("image"));
		    	fileVo.setUploadPath(uploadPath);
		    	
		    	list.add(fileVo);

			} catch (Exception e) {
			    	log.error(e.getMessage());
			}

		}
		
		return list;
		
	}
	
	@GetMapping("/display")
	// 이미지를 화면에 보여줍니다
	public ResponseEntity<byte[]> display(String fileName) {
		log.info("=====fileName : " + fileName);
		
		try {
			// 파일 객체를 생성
			File file = new File(ATTACHES_DIR+fileName);
			HttpHeaders headers = new HttpHeaders();
			
			// 이미지 파일이 존재하면 파일을 이미지를 다운로드
			if(file.exists()) {
				// Mime타입을 설정
				headers.add("Content-Type",Files.probeContentType(file.toPath()));
				return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/download")
	// Mime 타입을 다운로드 받을수 있는 형식으로 지정하여 브라우져에서 파일을 다운로드 합니다
	public ResponseEntity<byte[]> download(String fileName) throws IOException {
		
		log.info("download file : " + fileName);
		File file = new File(ATTACHES_DIR + fileName);
		
		// 파일의 타입및 암호화된패턴등은 헤더에 지정
		HttpHeaders headers = new HttpHeaders();
		
		try {
			
			// 파일이 존재하면 파일을 읽어들여 브라우져에 전달
			if(file.exists()) {
				// Mime 타입을 지정 - 다운받을수 있는 타입으로 지정
				headers.add("Content-Type",MediaType.APPLICATION_OCTET_STREAM.toString());

				// 다운로드시 저장되는 이름을 지정 (한글이 깨지는것을 막기위해 인코딩 처리가 필요합니다 )
				// 컨텐츠에 대한 추가 설명 및 파일 이름
				headers.add("Content-Disposition", 
						"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO-8859-1") + "\"");
				
				return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			// 파일이 없는경우 상태코드 404 전달
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (UnsupportedEncodingException e) {
			// 오류 발생시 상태코드 500 전달
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

	}
	
	// 중복 방지용 업로드 날자를 폴더 이름으로 사용
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		String uploadPath = str.replace("-", File.separator)+"\\";
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






