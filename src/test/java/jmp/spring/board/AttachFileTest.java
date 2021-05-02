package jmp.spring.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.mapper.AttachFileMapper;
import jmp.spring.vo.AttachFileVo;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AttachFileTest {

		@Autowired
		public AttachFileMapper mapper;
		
		@Test
		public void test() {
			AttachFileVo vo = new AttachFileVo();
			vo.setBno(222);
			vo.setFileName("fileName");
			vo.setUploadPath("filePath");
			vo.setImage("N");
			vo.setUuid("uuid");
			
			mapper.insert(vo);
		}
		
		@Test
		public void testUpdate() {
			int[] no = {10,11,3};
			mapper.updateBno(111, no);
		}
		
		@Test
		public void testGet() {
			
			AttachFileVo vo= mapper.get(11);
			log.info(vo);
		}
		
		@Test
		public void testGetList() {
			List<AttachFileVo> list= mapper.getList(222);
			log.info(list);
		}
		
		@Test
		public void testDelete() {
			mapper.delete(11);
		}
}

	

