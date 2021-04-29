package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmp.spring.mapper.ReplyMapper;
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
	@Transactional
	public int insert(ReplyVo vo) {
		// TODO Auto-generated method stub
		int res = mapper.insertReply(vo);
		mapper.updateReplyCnt(vo.getBno());
		return res;
	}

	@Override
	public ReplyVo get(int rno) {
		// TODO Auto-generated method stub
		return mapper.get(rno);
	}

	@Override
	public int delete(int rno) {
		// TODO Auto-generated method stub
		int res = mapper.delete(rno);
		int bno = mapper.getBno(rno);
		mapper.updateReplyCnt(bno);

		return res;
	}

	@Override
	@Transactional
	public int update(ReplyVo vo) {
		// TODO Auto-generated method stub
		int res = mapper.update(vo);

		return res; 
	}

	@Override
	public int getTotal(int bno) {
		// TODO Auto-generated method stub
		return mapper.getTotal(bno);
	}



}
