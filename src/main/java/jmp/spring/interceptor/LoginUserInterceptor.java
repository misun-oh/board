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
			
			// Remeber Me 체크 -> 자동로그인 쿠키 생성
			if(!StringUtils.isEmpty(request.getParameter("useCookie"))) {
				int amount = 7;
				// 7이후
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, amount);
			
				// 자동 로그인 가능 기간 7일
	            userVo.setSessionlimit(cal.getTime());
	            userVo.setSessionkey(session.getId());
				// DB 세션정보 저장
				int res = userService.updateSessionId(userVo);
				if(res==0) {
					log.error("=========== 자동로그인 세션 등록 실패");
				}
				Cookie loginCookie = new Cookie(LOGIN_COOKIE, session.getId());
				// 경로
				loginCookie.setPath("/");
				// 시간 7일
				loginCookie.setMaxAge(amount*24*60*60);	
				response.addCookie(loginCookie);
			}
			
			
		}
	}
	
	
}
