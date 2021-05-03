package jmp.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jmp.spring.service.AttachFileService;
import jmp.spring.vo.AttachFileVo;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@RestController
@Log4j
public class AttachFileController {

    private static final String ATTACHES_DIR = "C:\\upload\\";
    
    @Autowired
    public AttachFileService service;

	@PostMapping("/ajaxFileUpload")
	public Map<String, String> upload(MultipartFile[] uploadFile,int attachNo) {
		Map<String, String> map = new HashMap<String, String>();
		
		
		// 파일리스트 번호가 없는 경우 신규 생성
		if(attachNo == 0) {
			attachNo = service.getAttachNo();
		}
		
		int res = 0;
		for(MultipartFile file : uploadFile) {

			log.info("=============="+file.getOriginalFilename());
			log.info("=============="+file.getSize());
			
			try {
				
				// 파일리스트 번호를 받아 옵니다! -> 파일리스트 번호가 0인 경우 서비스에서 시퀀스로 생성 하여 화면에 반환 합니다
				AttachFileVo vo = new AttachFileVo(attachNo, file.getOriginalFilename(), getFolder());
				File saveFile = new File(ATTACHES_DIR + vo.getSavePath());
								
				// 업로드한 파일 데이터를 서버에 파일로 저장한다.
		    	file.transferTo(saveFile);

		    	// Mime타입을 확인하여 이미지인 경우 썸네일을 생성한다   
		    	String contentType = Files.probeContentType(saveFile.toPath());
		    	
		    	if(contentType.startsWith("image")) {
		    		String thmbnail = ATTACHES_DIR + vo.getS_savePath() ;
		    		Thumbnails.of(saveFile).size(100, 100).toFile(thmbnail);
		    		vo.setImage("Y");
		    	}
		    	
		    	// 업로드 파일의 정보를 DB에 저장한다.
		    	if(service.insert(vo)>0) {
		    		res++;
		    	}
		    	

			} catch (Exception e) {
			    	log.error(e.getMessage());
			}

		}
		
		
		map.put("attachNo", attachNo+"");
		map.put("result", res+"건 등록되었습니다.");
		
		return map;
	}
	
	@GetMapping("/AttachFileList/{attachNo}")
	public List<AttachFileVo> getList(@PathVariable("attachNo") int attachNo){

		// 첨부파일 리스트를 조회
		List<AttachFileVo> list = service.getList(attachNo);
		
		return list;
		
	}
	
	@GetMapping("/AttachFileDelete/{uuid}/{attachNo}")
	public Map<String, Object> delete(@PathVariable("attachNo") int attachNo,
									@PathVariable("uuid") String uuid) {
		
		AttachFileVo vo = service.get(uuid);
		
		File file = new File(ATTACHES_DIR+vo.getSavePath());
		if(file.exists()) {
			file.delete();	
		}
		File s_file = new File(ATTACHES_DIR+vo.getS_savePath());
		if(s_file.exists()) {
			s_file.delete();	
		}
		
		int res = service.delete(uuid);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", res>0?"success":"fail");
		map.put("list", service.getList(attachNo));
		return map;
		
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
	@ResponseBody
	// Mime 타입을 다운로드 받을수 있는 형식으로 지정하여 브라우져에서 파일을 다운로드 합니다
	public ResponseEntity<byte[]> download(String fileName) throws IOException {
		
		log.info("download file : " + fileName);
		File file = new File("c:\\upload\\" + fileName);
				
		// 파일의 타입및 암호화된패턴등은 헤더에 지정
		HttpHeaders headers = new HttpHeaders();
//		InputStream in = null;
		try {
				
			// 파일이 존재하면 파일을 읽어들여 브라우져에 전달
			if(file.exists()) {
				// Mime 타입을 다운받을수 있는 타입으로 지정
			headers.add("contentType",MediaType.APPLICATION_OCTET_STREAM.toString());			// 다운로드시 저장되는 이름을 지정 (한글이 깨지는것을 막기위해 인코딩 처리가 필요합니다 )
			// 컨텐츠에 대한 추가 설명 및 파일 이름
			headers.add("Content-Disposition", "attachment; filename=\"" 
												+ new String(fileName.getBytes("UTF-8"),"ISO-8859-1") + "\"");
						
//			in = new FileInputStream(file);
//			return new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} else {
			// 파일이 없는경우 상태코드 404 전달			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
					
		} catch (UnsupportedEncodingException e) {
			// 오류 발생시 상태코드 500 전달
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					
		} 
//		finally {
//			if(in!=null) {
//				in.close();
//			}
//		}


	}
	
	// 중복 방지용 업로드 날자를 폴더 이름으로 사용
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		String uploadPath = str.replace("-", File.separator)+File.separator;
		File saveFile = new File(ATTACHES_DIR+uploadPath);
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






