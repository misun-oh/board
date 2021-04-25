package jmp.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvic {
	
	@Before( "execution(* jmp.spring.service.BoardService.*(..))")
	public void logBefore() {
		
		log.info("=================Aspect");
		
	}
	
	//AspectJ 표현식으로 포인트컷 지정
	@Around("execution(* jmp.spring.service.BoardService.*(..))")
	//Around어드바이스는 JoinPoint의 하위 클래스인  ProceedingJoinPoint 타입의 파라미터를 필수적으로 선언 합니다
	public Object logTime(ProceedingJoinPoint pjp) {
		
		// 타겟메소드의 실행결과를 담아 리턴합니다
		Object result = null;
		
		long start = System.currentTimeMillis();
		
		// @before 처리
		log.info(pjp.getTarget());
		log.info(Arrays.toString(pjp.getArgs()));
		
		
		 try {
			// 타겟 실행후 결과를 반환 받습니다.
			result = pjp.proceed();
		} catch (Throwable e) {
			
			// @afterThrowing 처리
			e.printStackTrace();
		}
		
		// @after 처리
		long end = System.currentTimeMillis();
		log.info("실행시간 : "+(end-start)/1000.0+"초");
		
		// 타겟의 실행 결과를 반환
		return result;
	}
}