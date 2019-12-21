package com.mec.database.core;

import java.lang.reflect.Field;
import java.util.Map;

import org.w3c.dom.Element;

import com.mec.util.XMLParser;
/**
 * 采用XML配置方式配置类表，类表中的成员对应关系；
 * @author FZCD957
 */
public class ClassPathXmlApplicationContext extends ClassPathApplicationContext{

	private ClassTableDefinition ctd1;
	private Field[] field1;
	public ClassPathXmlApplicationContext(String packageName) {
		ClassTableFactory ctf = new ClassTableFactory();
		new XMLParser() {
			
			@Override
			public void dealElement(Element element, int index) {
				String classname = element.getAttribute("class");
				String tablename = element.getAttribute("table");
				Class<?> klass;
				try {
					klass = Class.forName(classname);
					ClassTableDefinition ctd = new ClassTableDefinition();
					ctd1 = ctd;
					field1 = klass.getDeclaredFields();
					scanField(klass, ctd);
					set(ctd,klass,tablename);
					ctf.addClassTableDefinition(klass.getName(), ctd1);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}			
				new XMLParser() {
				
					@Override
					public void dealElement(Element element, int index) {
						String id = element.getAttribute("id");
						String column = element.getAttribute("column");
						PropertyColumn pc = ctd1.getPropertyColumn(field1[index]);
						if(id != "") {
							try {
								setID(ctd1, pc);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						set(pc, field1[index], column);
					}

						
				}.dealElementInTag(element, "column");
			}
		}.dealElementInTag(XMLParser.getDocument(packageName), "mapping");
	}
	
	public Map<String, ClassTableDefinition> getClassTableFactory() {
		return new ClassTableFactory().getfactoryMap();
	}	
}
