package jmp.spring.service;

import jmp.spring.vo.UserVo;

public interface UserService {
	public UserVo login(UserVo vo) ;
	public int userInsert(UserVo vo) ;
	public int updateSessionId(UserVo vo);
	public UserVo getAutoLogin(String sessionid);
}
