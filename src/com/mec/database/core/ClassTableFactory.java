package com.mec.database.core;

import java.util.HashMap;
import java.util.Map;
/**
 * 这个类用来产生factoryMap 
 * 键为类名，值为ClassTableDefinition
 * @author FZCD957
 */
public class ClassTableFactory {
	static final Map<String, ClassTableDefinition> factoryMap = new HashMap<>();
	
	public ClassTableFactory() {
	}
	
	public void addClassTableDefinition(String Classname,ClassTableDefinition ctd) {
		if(factoryMap.containsKey(Classname)) {
			return;
		}
		factoryMap.put(Classname, ctd);
	}
	
	//提供给外部一个调用factoryMap的方法
	public Map<String, ClassTableDefinition> getfactoryMap(){
		return factoryMap;
	}
	
}
