package com.mec.database.core;

import java.lang.reflect.Field;
import java.util.Map;

import com.mec.database.annotation.Column;
import com.mec.database.annotation.ID;
import com.mec.database.annotation.Table;
import com.mec.util.PackageScanner;

/**
 * 采用注解方式配置类表，类表中的成员对应关系；
 * @author FZCD957
 */
public class ClassPathAnnotationApplicationContext extends ClassPathApplicationContext {
	
	public ClassPathAnnotationApplicationContext(String packageName) {
		ClassTableFactory classTableFactory = new ClassTableFactory();
		new PackageScanner() {
			
			@Override
			public void dealClass(Class<?> klass) {
				if(klass.isPrimitive()
				   ||klass.isInterface()
				   ||klass.isArray()
				   ||klass.isEnum()
				   ||!klass.isAnnotationPresent(Table.class)) {
					
				return;
				}
				Table table = klass.getAnnotation(Table.class);
				String tableName  = table.value();	
				ClassTableDefinition ctd= new ClassTableDefinition();
				
				//给ClassTableDefinition中的klass和Table赋值
				set(ctd, klass, tableName);
				
				//给ClassTableDefinition中的properties、以及properties中的PropertyColumn中的各成员赋值
				scanField(klass, ctd);
				try {
					//通过@ID给id赋值，@Column的value()给column赋值
					scanFields(klass.getDeclaredFields(),ctd);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//以类名为键，ClassTableDefinition为值放入factorymap
				classTableFactory.addClassTableDefinition(klass.getName(), ctd);
			}
		}.scannerPackage(packageName);
	}
	//通过@ID给id赋值，@Column的value()给column赋值
	private void scanFields(Field[] fields, ClassTableDefinition ctd)
			throws Exception {
		for (Field field : fields) {
			PropertyColumn pc = ctd.getPropertyColumn(field);
			if (field.isAnnotationPresent(ID.class)) {
				setID(ctd, pc);
			}
			
			if (!field.isAnnotationPresent(Column.class)) {
				continue;
			}
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			set(pc, field, columnName);
		}
	}
	
	//提供一个getClassTableFactory方法供外部使用
	public Map<String, ClassTableDefinition> getClassTableFactory() {
		return new ClassTableFactory().getfactoryMap();
	}
}
