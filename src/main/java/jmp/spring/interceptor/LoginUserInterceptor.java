package jmp.spring.interceptor;


import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jmp.spring.service.UserService;
import jmp.spring.vo.UserVo;
import lombok.extern.log4j.Log4j;
import oracle.sql.DATE;

@Log4j
public class LoginUserInterceptor extends HandlerInterceptorAdapter implements SessionNames{

	@Autowired
	UserService userService;

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		UserVo userVo = (UserVo)modelAndView.getModelMap().get("user");
		
		if(userVo != null) {
			session.setAttribute(LOGIN, userVo);
			
			// Remeber Me üũ -> �ڵ��α��� ��Ű ����
			if(!StringUtils.isEmpty(request.getParameter("useCookie"))) {
				int amount = 7;
				// 7����
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, amount);
			
				// �ڵ� �α��� ���� �Ⱓ 7��
	            userVo.setSessionlimit(cal.getTime());
	            userVo.setSessionkey(session.getId());
				// DB �������� ����
				int res = userService.updateSessionId(userVo);
				if(res==0) {
					log.error("=========== �ڵ��α��� ���� ��� ����");
				}
				Cookie loginCookie = new Cookie(LOGIN_COOKIE, session.getId());
				// ���
				loginCookie.setPath("/");
				// �ð� 7��
				loginCookie.setMaxAge(amount*24*60*60);	
				response.addCookie(loginCookie);
			}
			
			
		}
	}
	
	
}
