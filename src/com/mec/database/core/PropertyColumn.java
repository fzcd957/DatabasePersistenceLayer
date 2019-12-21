package com.mec.database.core;
/**
 * 这个类用来处理数据库字段与类中成员对应问题
 * @author FZCD957
 */

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyColumn {
	Field field;  //类中的成员名
	String column;//表中的字段名
	Object value;//成员的值
	
	public PropertyColumn() {
		this.value = null;
	}

	public Field getField() {
		return field;
	}

	public void setField(ResultSet rs) {
		value = null;
		
		try {
			value = rs.getObject(column);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setField(Field field) {
		this.field = field;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return field.getName() + "->" + column;
	}
	
}
