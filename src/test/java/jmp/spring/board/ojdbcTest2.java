package jmp.spring.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;

import jmp.spring.mapper.BoardMapper;
import jmp.spring.service.BoardService;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ojdbcTest2 {
	
	@Autowired
	BoardService service;
	
	@Autowired
	HikariDataSource datasource; 
	
	@Autowired
	BoardMapper mapper;
	
	/**
	 * 
	 * ?��?��?�� : 
	 * ?��?��?�� : 
	 * 반환�? : 
	 * 
	 */
	@Test
	public void service() {
		//log.info("service==============="+service.getList());
	}
	
	@Test
	public void mapper() {
		//log.info("==============="+mapper.getList());
	}
	
	@Test
	public void hikaricpTest() {
		try {
			log.info(datasource);
			Connection conn = datasource.getConnection();
			
			log.info("============= : "+conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void ojdbcTest() {
		try {
			Connection conn = DriverManager.getConnection
					  ("jdbc:oracle:thin:@localhost:1521:xe", "spring", "spring");
			log.info(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
