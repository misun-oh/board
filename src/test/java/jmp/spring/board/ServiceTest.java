package jmp.spring.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.mapper.BoardMapper;
import jmp.spring.service.BoardService;
import jmp.spring.vo.BoardVo;
import jmp.spring.vo.Criteria;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ServiceTest {
	
	@Autowired
	BoardService service;
	
	@Autowired
	BoardMapper mapper;
	
	@Test
	public void getTotal() {
		log.info("==============="+service.getTotal());
	}
	
	@Test
	public void getList() {
		Criteria cri = new Criteria();
		cri.setPageNo(5);
		List<BoardVo> list =service.getList(cri); 
				//mapper.getList(cri);
		
		log.info("page==========list"+list);
		
	}
	
	@Test
	public void deleteMapper() {
		int res = mapper.delete(3);
		
		System.out.println("============"+res);
	}
	
	@Test
	public void updateMapper() {
		BoardVo vo = new BoardVo();
		vo.setBno(189);
		vo.setContent("내용-update mapperTest");
		vo.setTitle("제목 -update mapperTest");
		vo.setWriter("작성자 -update mapperTest");
		
		int res = mapper.update(vo);
		
		log.info("=============res : "+res);
		
		
	}
	
	@Test
	public void getService() {
		BoardVo vo = service.get(189);
		log.info(vo);
	}
	
	@Test
	public void getMapper() {
		BoardVo vo = mapper.get(189);
		log.info(vo);
	}
	
	@Test
	public void mapper() {
		BoardVo vo = new BoardVo();
		vo.setContent("내용-mapperTest");
		vo.setTitle("제목 - mapperTest");
		vo.setWriter("작성자 - mapperTest");
		
		int res = mapper.insertBoard(vo);
		
		//log.info("mapper.insert.test=========== : "+res);
		log.info("service.insertBoard=============:"+service.insertBoard(vo));
	}
	
	
}
