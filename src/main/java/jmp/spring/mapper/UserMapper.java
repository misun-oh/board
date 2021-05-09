package jmp.spring.mapper;

import java.util.List;

import jmp.spring.vo.RoleVo;
import jmp.spring.vo.UserVo;

public interface UserMapper {
	
	public UserVo login(UserVo vo);
	public List<String> getUserRole(UserVo vo);
	public int userInsert(UserVo vo);
	public int updateSessionId(UserVo vo);
	public UserVo getAutoLogin(String vo);
}
