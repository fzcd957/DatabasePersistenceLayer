package com.mec.database.core;

import java.lang.reflect.Field;

/**
 * 这个类是作为基类，供之后的注解或者XML配置两种不同的方法配置类表对应关系
 * 因为存在不同的配置，则给中propertyColumn各成员赋值的方法就不同
 * 那么首先给一个基类，赋值就是给用propertyColumn.getField().getName()做键，用对应的propertyColumn作值
 * 无论采用哪种方式，都先这样做，之后再用对应的取标签中的column赋值或者用@column注解赋值
 * @author FZCD957
 */
public class ClassPathApplicationContext {
	
	public ClassPathApplicationContext() {
	}

	//给ClassTableDefinition中的properties、以及properties中的PropertyColumn中的各成员赋值
	void scanField(Class<?> klass,ClassTableDefinition ctd) {
		Field[] fields = klass.getDeclaredFields();
		for(Field field : fields) {
			PropertyColumn pc = new PropertyColumn();
		set(pc,field,field.getName());
		ctd.addColumn(pc);
		}
	}
	
	//给ClassTableDefinition中的klass和Table赋值
	void set(ClassTableDefinition ctd, Class<?> klass, String table) {
		ctd.setKlass(klass);
		ctd.setTable(table);
	}
	//给ClassTableDefinition中的ID赋值
	void setID(ClassTableDefinition ctd, PropertyColumn id) 
			throws Exception {
		if (ctd.getId() != null) {
			throw new Exception("ID(" + id.getField().getName() + ")重复！");
		}
		ctd.setId(id);
	}
	
	//(调用set方法)给propertyColumn中的column和field赋值
	void set(PropertyColumn pc,Field field,String column) {
		pc.setField(field);
		pc.setColumn(column);
		
	}

}
