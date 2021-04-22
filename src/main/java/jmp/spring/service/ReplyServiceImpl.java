package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.BoardMapper;
import jmp.spring.mapper.ReplyMapper;
import jmp.spring.vo.BoardVo;
import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper mapper;
	
	@Override
	public List<ReplyVo> getList(Criteria cri, int bno) {
		// TODO Auto-generated method stub
		return mapper.getList(cri, bno);
	}

	@Override
	public int insert(ReplyVo vo) {
		// TODO Auto-generated method stub
		return mapper.insertReply(vo);
	}

	@Override
	public ReplyVo get(int rno) {
		// TODO Auto-generated method stub
		return mapper.get(rno);
	}

	@Override
	public int delete(int rno) {
		// TODO Auto-generated method stub
		return mapper.delete(rno);
	}

	@Override
	public int update(ReplyVo vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}



}
