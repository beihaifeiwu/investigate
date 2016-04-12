package com.course.selection.domain;

public enum CourseStateType {
	CREATE("已创建"),INDATABASE("已存储"),MODIFIED("已修改"),PICKABLE("可选"),FULL("已满"),DELETED("已删除");
	
	private String value;
	
	private CourseStateType(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
