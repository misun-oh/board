package jmp.spring.mapper;

import java.util.List;

import jmp.spring.vo.AttachFileVo;

public interface AttachFileMapper {

	public int getAttachNo();
	
	public int insert(AttachFileVo vo);

	public List<AttachFileVo> getList(int attachNo);
	
	public AttachFileVo get(String uuid);
		
	public int delete(int attachNo);
	
}
	
