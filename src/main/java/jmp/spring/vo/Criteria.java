package jmp.spring.vo;

import lombok.Data;

@Data
public class Criteria {
	int pageNo;
	int amount;
	
	String type;
	String keyword;
	
	// 초기화
	public Criteria() {
		this.pageNo = 1;
		this.amount = 10;
	}
	
	
}
