package jmp.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jmp.spring.service.ReplyService;
import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;


@RestController
@Log4j
public class ReplyController {

	@Autowired
	ReplyService service;
	
	@GetMapping("/reply/list/{pageNo}/{bno}")
	public List<ReplyVo> getList(@PathVariable("pageNo") int pageNo,
			@PathVariable("bno") int bno) {
		Criteria cri = new Criteria(pageNo,10);
		
		return service.getList(cri, bno);
		
	}
	
	@PostMapping("/reply/insert")
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVo vo) {
		
		int res = service.insert(vo);
		if (res>0)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		else 
			return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
//	@GetMapping("/board/restGet")
//	public ResponseEntity<BoardVo> getBoard() {
//		
//		BoardVo vo = service.get(222);
//		return new ResponseEntity<BoardVo>(vo, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	@PostMapping("/reply/insert")
//	@ResponseBody
//	public Map<String, Object> insert(@RequestBody ReplyVo vo) {
//		int res = service.insert(vo);
//		HashMap<String, Object> resMap = new HashMap<String, Object>();
//		
//		if(res>0) {
//			resMap.put("result", "success");
//		}else {
//			resMap.put("result", "fail");
//		}
//		return resMap;
//	}
//	
//	@GetMapping("/reply/pages/{bno}/{page}")
//	public Map<String, Object> getList(@PathVariable("bno") int bno,
//								@PathVariable("page") int page){
//		
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		Criteria cri = new Criteria(page,10);
//		map.put("result", service.getList(cri, bno));
//	
//		return map; 
//		
//	}
	
}
