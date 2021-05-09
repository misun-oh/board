package jmp.spring.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import jmp.spring.service.UserService;
import jmp.spring.vo.UserVo;
import lombok.extern.log4j.Log4j;

@Log4j
public class AuthInterceptor extends HandlerInterceptorAdapter{
	/**
	 * This implementation always returns {@code true}.
	 */
	@Autowired
	UserService userService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute(SessionNames.LOGIN);
		// 자동 로그인 처리
		Cookie loginCookie = WebUtils.getCookie(request, SessionNames.LOGIN_COOKIE);
		if(user==null && loginCookie != null) {
			user = userService.getAutoLogin(loginCookie.getValue());
			session.setAttribute(SessionNames.LOGIN, user);
			log.info("자동로그인============================================"+user);
		}

		log.info("AuthInterceptor============================================"+user);
		if(user == null) {
			  response.sendRedirect("/login");
			  return false;
		}
		
				
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}

}
