package com.mec.database.test;

import java.util.Map;

import com.mec.database.core.ClassPathAnnotationApplicationContext;
import com.mec.database.core.ClassTableDefinition;

public class Test {
	public static void main(String[] args) {
		ClassPathAnnotationApplicationContext cpaac = new ClassPathAnnotationApplicationContext("com.mec.database.model");
			Map<String, ClassTableDefinition> map = cpaac.getClassTableFactory();
			
			for (String key : map.keySet()) {
				ClassTableDefinition ctd = map.get(key);
				System.out.println(ctd);
			}
	}
}
