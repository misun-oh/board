package jmp.spring.vo;

import java.util.UUID;

import lombok.Data;

@Data
public class AttachFileVo {

  public AttachFileVo() {
		
  }
  
  public AttachFileVo(int attachNo, String originalFilename, String uploadPath) {
	  	
	  	// 소프트웨어 구축에 쓰이는 식별자 표준
		// UUID 표준에 따라 이름을 부여하면 고유성을 완벽하게 보장할 수는 없지만 실제 사용 상에서 중복될 가능성이 거의 없다고 인정!
		// 파일이름 뒤에 uuid를 붙여줍시다
		UUID uuid = UUID.randomUUID();
		this.uuid = uuid.toString();
		
		this.attachNo = attachNo;
		this.fileName = originalFilename;
		this.uploadPath = uploadPath;
		this.image="N";
		
		// DB저장하지 않음 - 파일생성시 전체경로및 화면에서 사용
		this.savePath = uploadPath+uuid+"_"+originalFilename; 
		this.s_savePath = uploadPath+"s_"+uuid+"_"+originalFilename;
		
	}

  // 춤부파일 번호
  private int attachNo;
  
  // 첨부파일 이름
  private String fileName;

  // 업로드 경로
  private String uploadPath;
  
  // uuid 중복된 이름처리를 위한 랜덤값
  private String uuid;
  
  // 이미지 여부 (Y,N)
  private String image;
  
  // 게시글 번호
  private int bno;
  
  // 썸네일 경로
  private String s_savePath;
  
  // 파일 경로
  private String savePath;
  
}
