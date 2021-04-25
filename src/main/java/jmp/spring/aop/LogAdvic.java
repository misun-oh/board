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
	
	//AspectJ ǥ�������� ����Ʈ�� ����
	@Around("execution(* jmp.spring.service.BoardService.*(..))")
	//Around�����̽��� JoinPoint�� ���� Ŭ������  ProceedingJoinPoint Ÿ���� �Ķ���͸� �ʼ������� ���� �մϴ�
	public Object logTime(ProceedingJoinPoint pjp) {
		
		// Ÿ�ٸ޼ҵ��� �������� ��� �����մϴ�
		Object result = null;
		
		long start = System.currentTimeMillis();
		
		// @before ó��
		log.info(pjp.getTarget());
		log.info(Arrays.toString(pjp.getArgs()));
		
		
		 try {
			// Ÿ�� ������ ����� ��ȯ �޽��ϴ�.
			result = pjp.proceed();
		} catch (Throwable e) {
			
			// @afterThrowing ó��
			e.printStackTrace();
		}
		
		// @after ó��
		long end = System.currentTimeMillis();
		log.info("����ð� : "+(end-start)/1000.0+"��");
		
		// Ÿ���� ���� ����� ��ȯ
		return result;
	}
}