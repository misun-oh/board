package jmp.spring.vo;

import lombok.Data;

@Data
public class BoardVo {
	
	// 踰덊샇
	int bno;
	// �젣紐�
	String title;
	// �궡�슜
	String content;
	// �옉�꽦�옄
	String writer;
	// �옉�꽦�씪�떆
	String regdate;
	// �닔�젙�씪�떆
	String updatedate;

	String replycnt;
}
