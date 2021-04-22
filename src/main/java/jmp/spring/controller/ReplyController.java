package jmp.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@PostMapping("/reply/update")
	public Map<String, Object> update(@RequestBody ReplyVo vo){
		
		Map<String, Object> map = new HashMap<String, Object>();
		int res = service.update(vo);
		if(res>0) {
			map.put("result", "success");	
		}else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	@PostMapping("/reply/insert")
	public Map<String, Object> insert(@RequestBody ReplyVo vo) {
		
		int res = service.insert(vo);
		
		Map<String, Object> map = new HashMap();
		
		if (res>0)
			map.put("result", "success");
		else 
			map.put("result", "fail");
		
		return map;
	}
	
	
	@GetMapping("/reply/get/{rno}")
	public ReplyVo get(@PathVariable("rno") int rno) {
		return service.get(rno);
	}
	
	@PostMapping("/reply/delete/{rno}")
	public Map<String, Object> delete(@PathVariable("rno") int rno) {
		
		int res = service.delete(rno);
		
		Map<String, Object> map = new HashMap();
		
		if (res>0)
			map.put("result", "success");
		else 
			map.put("result", "fail");
		
		return map;
	}
}
