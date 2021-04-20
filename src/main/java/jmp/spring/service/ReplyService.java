package jmp.spring.service;

import java.util.List;

import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;

public interface ReplyService {

	public List<ReplyVo> getList(Criteria cri, int bno);
	
	public int insert(ReplyVo vo);
	
	public ReplyVo get(int bno);
}
