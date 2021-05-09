package jmp.spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.WebUtils;

import jmp.spring.interceptor.SessionNames;
import jmp.spring.service.UserService;
import jmp.spring.vo.UserVo;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserController {
	
	@Autowired
	private UserService service;
	 
	
	@GetMapping("/login")
	public void login() throws Exception {
		log.info("login GET.....");
		
	}
	
	@PostMapping("/loginAction")
	public String loginAction(UserVo user, Model model) {
		log.info("loginAction" + user);
		
		user = service.login(user);
		if(user != null) {
			model.addAttribute("user",user);
			model.addAttribute("loginResult","Login success!!");
			
			return "/loginAction";
		//	return "/board/list";
		}else {
			model.addAttribute("msg","Login Fail!!");
			return "/login";
		}
		
		
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		// 세션 제거
		session.removeAttribute(SessionNames.LOGIN);
		session.invalidate();
		
		// 쿠키 제거
		Cookie loginCookie = WebUtils.getCookie(req, SessionNames.LOGIN_COOKIE);
		if(loginCookie != null){
			// 시간 0초로 변경
			loginCookie.setMaxAge(0);
			res.addCookie(loginCookie);
			
		}
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/userCreate")
	public String userCreate(UserVo vo) {
		String returnPage="/login";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		vo.setPwd(encoder.encode(vo.getPwd()));
		
		int res = service.userInsert(vo);
		
		
		return returnPage;
		
	}

	
	@GetMapping("/userRegister")
	public void userRegister() {
	}
}
