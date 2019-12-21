package com.mec.database.model;

import com.mec.database.annotation.Column;
import com.mec.database.annotation.ID;
import com.mec.database.annotation.Table;
/**
 * 数据库与类的对应关系
 * @author FZCD957
 */
@Table(value = "student_info")
public class StudentinfoModel {
	@ID
	@Column(value = "stNumber")
	String studentId;
	@Column(value = "ID")
	String id;
	@Column(value = "Status")
	boolean status;
	String name;
	String sex;
	
	public StudentinfoModel() {
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}

