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
		// �α��� id/pw�� �´� ������ ��ȸ
		UserVo userVo = mapper.login(vo);
		if(userVo != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(vo.getPwd(), userVo.getPwd())) {
				// ������ ������ ��ȸ
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
