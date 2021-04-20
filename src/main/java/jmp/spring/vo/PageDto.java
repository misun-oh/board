package jmp.spring.vo;

import lombok.Data;

@Data
public class PageDto {
	
	int startNo;
	int endNo;
	boolean prev, next;
	
	
	Criteria cri;
	int total;
	

	
	public PageDto(Criteria cri, int total) {
	
		this.cri = cri;
		this.total = total;
		
		this.endNo = (int)( Math.ceil(cri.pageNo/10.0) * 10);
		this.startNo = this.endNo - 9;
		
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		endNo = endNo>realEnd ? realEnd : endNo;
		
		prev = startNo>1 ? true: false;
		next = endNo==realEnd ? false: true;
	}
	
	
}
