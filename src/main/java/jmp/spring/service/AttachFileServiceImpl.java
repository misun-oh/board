package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.AttachFileMapper;
import jmp.spring.vo.AttachFileVo;

@Service
public class AttachFileServiceImpl implements AttachFileService {

	@Autowired
	AttachFileMapper mapper;
	
	@Override
	public int insert(AttachFileVo vo) {
		return mapper.insert(vo);
	}

	@Override
	public List<AttachFileVo> getList(int attachNo) {
		return mapper.getList(attachNo);
	}

	@Override
	public AttachFileVo get(String uuid) {
		return mapper.get(uuid);
	}

	@Override
	public int delete(int attachNo) {
		return mapper.delete(attachNo);
	}

	@Override
	public int getAttachNo() {
		// TODO Auto-generated method stub
		return mapper.getAttachNo();
	}

}
