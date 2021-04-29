package jmp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jmp.spring.service.BoardService;
import jmp.spring.vo.BoardVo;
import jmp.spring.vo.Criteria;
import jmp.spring.vo.PageNavi;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class BoardController {

	@Autowired
	BoardService service;
	
	@GetMapping("/board/attach")
	public void attach() {
		
	}
	
	@GetMapping("/board/reply")
	public void reply() {
		
	}
	
	@GetMapping("/board/delete")
	public String delete(Criteria cri, BoardVo vo, RedirectAttributes rttr) {
		
		int res = service.delete(vo.getBno());
		String resMsg = "";
		// 삭제 성공 -> 리스트
		if(res>0) {
			resMsg = vo.getBno()+"번 게시글이 삭제 되었습니다.";
			rttr.addFlashAttribute("resMsg", resMsg);
			rttr.addAttribute("pageNo", cri.getPageNo());
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
			return "redirect:/board/list";
		} else {
		// 삭제 실패 -> 상세화면
			resMsg = "게시글 삭제 처리에 실패 했습니다.";
			rttr.addAttribute("pageNo", cri.getPageNo());
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
			return "redirect:/board/get";
		}
		
		
	}
	
	@PostMapping("/board/edit")
	public String editExe(Criteria cri, BoardVo vo, RedirectAttributes rttr) {
		
		int res = service.update(vo);
		String resMsg = "";
		
		if(res>0) {
			resMsg = "수정 되었습니다.";
		} else {
			resMsg = "수정 작업 실패 했습니다. 관리자에게 문의해주세요.";
		}
		
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("pageNo", cri.getPageNo());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("resMsg",resMsg);
		return "redirect:/board/get";
		
	}
	
	@GetMapping("/board/get")
	public String get(Criteria cri, BoardVo vo,Model model) {
		log.info("============"+vo.getBno());
		// 상세정보 조회
		vo = service.get(vo.getBno());
		log.info("============"+vo.getBno());
		//모델에 담아서 화면에 전달
		model.addAttribute("vo", vo);
		log.info("============"+vo);
		//리턴이 없으므로 /board/get(URL)로 페이지 연결
		
		return "/board/get_b";
	}
	
	@GetMapping("/board/edit")
	public String edit(Criteria cri, BoardVo vo,Model model) {
		log.info("============"+vo.getBno());
		// 상세정보 조회
		vo = service.get(vo.getBno());
		log.info("============"+vo.getBno());
		//모델에 담아서 화면에 전달
		model.addAttribute("vo", vo);
		log.info("============"+vo);
		//리턴이 없으므로 /board/get(URL)로 페이지 연결
		
		return "/board/edit_b";
	}
	
	//1. 등록페이지로 이동
	@GetMapping("/board/register")
	public String insert() {
		return "/board/register_b";
	}
	
	@PostMapping("/board/register")
	public String insertExe(BoardVo vo, RedirectAttributes rttr) {
		System.out.println("===========vo : "+vo);
		int res = service.insertBoard(vo);
		System.out.println("===========vo : "+vo);
		
		rttr.addFlashAttribute("resMsg",vo.getBno()+"번 게시글이 작성 되었습니다.");
		
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/board/list")
	public String getList(Criteria cri, Model model) {
		
		
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageNavi",new PageNavi(cri, service.getTotal(cri)));
		log.info("getList()================");
		
		return "/board/list_b";
	}
	
}
