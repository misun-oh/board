package jmp.spring.controller;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		
		Map<String, Integer> map = new HashMap<>();
		
		
		map.put("포도", 1);
		map.put("감자", 1);
		map.put("감자", 5);
		map.put("사과", 1);
		
		map.remove("사과");
		
		System.out.println(map.values());
		System.out.println(map.get("포도"));
		map.clear();
		System.out.println(map.isEmpty());
		System.out.println(map.toString());
	}
}
