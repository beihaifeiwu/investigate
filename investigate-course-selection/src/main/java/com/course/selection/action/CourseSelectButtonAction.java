package com.course.selection.action;

import org.apache.struts2.ServletActionContext;

import com.course.selection.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;

public class CourseSelectButtonAction extends ActionSupport {

	private static final long serialVersionUID = -290024252377552045L;
	
	private CourseService courseService;
	
	private String messageKey;
	
	private String errorKey;

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String open() throws Exception {
		
		try {
			getCourseService().openSelectCourse();
			ServletActionContext.getServletContext().setAttribute("courseSelect", true);
		} catch (Exception e) {
			setErrorKey("admin.courseselect.open.error");
			throw e;
		}
		setMessageKey("admin.courseselect.open.success");
		return SUCCESS;
	}
	public String close() throws Exception {
		
		try {
			getCourseService().closeSelectCourse();
			ServletActionContext.getServletContext().removeAttribute("courseSelect");
		} catch (Exception e) {
			setErrorKey("admin.courseselect.close.error");
			throw e;
		}
		setMessageKey("admin.courseselect.close.success");
		return SUCCESS;
	}
	public String remove() throws Exception {
		
		try {
			getCourseService().removeAllDeletedCourse();
		} catch (Exception e) {
			setErrorKey("admin.courseselect.remove.error");
			throw e;
		}
		setMessageKey("admin.courseselect.remove.success");
		return SUCCESS;
	}	

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

}
