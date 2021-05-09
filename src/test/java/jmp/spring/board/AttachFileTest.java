package jmp.spring.board;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.mapper.AttachFileMapper;
import jmp.spring.mapper.UserMapper;
import jmp.spring.vo.AttachFileVo;
import jmp.spring.vo.UserVo;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AttachFileTest {

		@Autowired
		
		public UserMapper mapper;
		@Test
		public void test() {
			
			Date limit = new Date();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 7);
			
			UserVo vo = new UserVo();
			vo.setId("4");
			vo.setSessionkey("sessionkey");
			vo.setSessionlimit(cal.getTime());
			
			mapper.updateSessionId(vo);
			//mapper.login(vo);
			
		}		
}

	

