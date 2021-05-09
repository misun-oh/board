package jmp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.UserMapper;
import jmp.spring.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserMapper mapper;
	
	@Override
	public UserVo login(UserVo vo) {
		// 로그인 id/pw에 맞는 유저를 조회
		UserVo userVo = mapper.login(vo);
		if(userVo != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(vo.getPwd(), userVo.getPwd())) {
				// 유저의 권한을 조회
				userVo.setUserRole(mapper.getUserRole(vo));
				System.out.println(vo.getPwd()+"/"+userVo.getPwd());
			} else {
				userVo = null;
			}

		}
		return userVo;
	}

	@Override
	public int userInsert(UserVo vo) {
		// TODO Auto-generated method stub
		return mapper.userInsert(vo);
	}
	

	@Override
	public int updateSessionId(UserVo vo) {
		// TODO Auto-generated method stub
		return mapper.updateSessionId(vo);
	}

	@Override
	public UserVo getAutoLogin(String sessionId) {
		// TODO Auto-generated method stub
		return mapper.getAutoLogin(sessionId);
	}
}
