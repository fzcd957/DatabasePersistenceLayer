package com.mec.database.core;
/**
 * 这个类用来处理类表对应问题
 * @author 余生
 */

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassTableDefinition {
	String table;
	Class<?> klass;
	Map<String, PropertyColumn> properties;  //键为该成员的名字
	PropertyColumn id;		//主键
	
	public ClassTableDefinition() {
		this.properties = new HashMap<>();
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public Class<?> getKlass() {
		return klass;
	}

	public void setKlass(Class<?> klass) {
		this.klass = klass;
	}

	public Map<String, PropertyColumn> getPropertypes() {
		return properties;
	}

	public void setPropertypes(Map<String, PropertyColumn> propertypes) {
		this.properties = propertypes;
	}

	public PropertyColumn getId() {
		return id;
	}

	public void setId(PropertyColumn id) {
		this.id = id;
	}

	//给ClassTableDefinition中的properties赋值
	//若值相同就返回
	//若不同用protertycolumn中的field作为键，用protertycolumn做值，给properties赋值
	public void addColumn(PropertyColumn pc) {
		if(properties.containsValue(pc)) {
			return;
		}
		properties.put(pc.getField().getName(), pc);
	}
	
	//通过成员名在properties中取出PropertyColumn
	PropertyColumn getPropertyColumn(Field field) {
		return properties.get(field.getName());
	}
	
	//重写equals方法是为了之后containskey()方法调用的是equals
	//如果不重写的话，默认比较的是首地址值，我们需要比较值是否相同
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((klass == null) ? 0 : klass.hashCode());
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassTableDefinition other = (ClassTableDefinition) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (klass == null) {
			if (other.klass != null)
				return false;
		} else if (!klass.equals(other.klass))
			return false;
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		} else if (!table.equals(other.table))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer("class:");
		res.append(klass.getName()).append('\n')
		.append("table:").append(table).append('\n')
		.append("fields:\n");
		
		for (String key : properties.keySet()) {
			PropertyColumn pc = properties.get(key);
			res.append('\t').append(pc).append('\n');
		}
		
		res.append("id:").append(id);
		
		return res.toString();
	}




}
