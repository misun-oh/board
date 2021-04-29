package jmp.spring.vo;

import lombok.Data;

@Data
public class AttachFileDTO {
	
  // 첨부파일 이름
  private String fileName;

  // 업로드 경로
  private String uploadPath;
  
  // uuid 중복된 이름처리를 위한 랜덤값
  private String uuid;
  
  // 이미지 여부
  private boolean image;
  
}
