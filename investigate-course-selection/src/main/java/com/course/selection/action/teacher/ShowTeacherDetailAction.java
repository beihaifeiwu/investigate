package com.course.selection.action.teacher;

import com.course.selection.domain.Teacher;
import com.course.selection.service.TeacherService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowTeacherDetailAction extends ActionSupport {

	private static final long serialVersionUID = -2050501120921861604L;

	private Integer ID;
	
	private Teacher teacher;
	
	private String errorKey;
	
	private TeacherService teacherService;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String execute() throws Exception {
		
		Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
		setID(id);
		
		if(getID() == null){
			setErrorKey("admin.teacher.show.null");
			return ERROR;
		}
		teacher = getTeacherService().getTeacherByID(getID());
		
		return SUCCESS;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
}
