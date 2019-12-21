package com.mec.database.test;

import java.util.Map;

import com.mec.database.core.ClassPathXmlApplicationContext;
import com.mec.database.core.ClassTableDefinition;

public class Test1 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("/Studentinfo.xml");
		Map<String, ClassTableDefinition> map = cp.getClassTableFactory();
		
		for (String key : map.keySet()) {
			ClassTableDefinition ctd = map.get(key);
			System.out.println(ctd);
		}
	}
		
}
