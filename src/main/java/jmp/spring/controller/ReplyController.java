package jmp.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jmp.spring.service.BoardService;
import jmp.spring.service.ReplyService;
import jmp.spring.vo.BoardVo;
import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;


@RestController
@Log4j
public class ReplyController {

	@Autowired
	ReplyService service;
	@Autowired
	BoardService boardService;
	
	@GetMapping("/board/restList")
	public List<BoardVo> getList(@RequestBody Criteria cri) {
		return boardService.getList(cri);
		
	}
	
	@GetMapping("/board/restGet")
	public BoardVo getBoard() {
		return boardService.get(222);
	}
	
	@PostMapping("/reply/insert")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody ReplyVo vo) {
		int res = service.insert(vo);
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		
		if(res>0) {
			resMap.put("result", "success");
		}else {
			resMap.put("result", "fail");
		}
		return resMap;
	}
	
	@GetMapping("/reply/pages/{bno}/{page}")
	public Map<String, Object> getList(@PathVariable("bno") int bno,
								@PathVariable("page") int page){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Criteria cri = new Criteria(page,10);
		map.put("result", service.getList(cri, bno));
	
		return map; 
		
	}
	
}
