package com.course.selection.action;

import com.course.selection.domain.Course;
import com.course.selection.domain.CourseStateType;
import com.course.selection.exception.CourseIDIllegalException;
import com.course.selection.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;

public class JumpToCourseModifyAction extends ActionSupport {
	
	private static final long serialVersionUID = 1528246831439082695L;

	private int courseID;
	
	private String messageKey;
	
	private Course course;
	
	private CourseService courseService;

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	@Override
	public String execute() throws Exception {
		
		if(getCourseID() <= 0 || getCourseService().getCourseByID(getCourseID()) == null){
			setMessageKey("admin.course.modify.error");
			throw new CourseIDIllegalException();
		}
		
		course = getCourseService().getCourseByID(getCourseID());
		
		if(course.getCourseState().getStateType() == CourseStateType.DELETED){
			setMessageKey("admin.course.modify.limit");
			return ERROR;
		}
		
		return SUCCESS;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	
}
