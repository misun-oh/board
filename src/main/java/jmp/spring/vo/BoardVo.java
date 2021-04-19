package jmp.spring.vo;

import lombok.Data;

@Data
public class BoardVo {
	
	// 번호
	int bno;
	// 제목
	String title;
	// 내용
	String content;
	// 작성자
	String writer;
	// 작성일시
	String regdate;
	// 수정일시
	String updatedate;

}
