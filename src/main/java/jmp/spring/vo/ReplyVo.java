package jmp.spring.vo;

import lombok.Data;

@Data
public class ReplyVo {
	
	// 댓글번호
	int rno;
	// 게시글번호
	int bno;
	// 내용
	String reply;
	// 작성자
	String replyer;
	// 작성일시
	String replydate;
	// 수정일시
	String updatedate;

}
