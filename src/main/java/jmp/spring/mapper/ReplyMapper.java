package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;

public interface ReplyMapper {
	
	public List<ReplyVo> getList(@Param("cri") Criteria cri, @Param("bno") int bno);
	
	public int insertReply(ReplyVo vo);
		
	public int delete(int rno);
	
	public int update(ReplyVo vo);
	
	public ReplyVo get(int rno);
}
