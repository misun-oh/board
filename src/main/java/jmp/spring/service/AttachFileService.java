package jmp.spring.service;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpHeaders;

public class AttachFileService {

	public HttpHeaders setHttpHeaders(File file) {
		HttpHeaders headers = new HttpHeaders();
		
		try {
//			Resource resource = new FileSystemResource()
			headers.add("Content-Disposition","attachment; filename=" 
					+ new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return headers;
	}
}
