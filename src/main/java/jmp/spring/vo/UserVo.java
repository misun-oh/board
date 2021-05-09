package jmp.spring.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserVo {
	public String id;
	public String pwd;
	public String name;
	public String email;
	public List<String> userRole;
	public String sessionkey;
	public Date sessionlimit;
	
	/**
	 * 권한이 있는지 체크 합니다.
	 * @param role_id
	 * @return
	 */
	public boolean hasRole(String role_id)
	{
		return userRole.contains(role_id);
	}

}

 