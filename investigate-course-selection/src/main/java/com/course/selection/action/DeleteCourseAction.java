package com.course.selection.action;

import com.course.selection.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * 课程删除动作Action
 * @author Administrator
 *
 */
public class DeleteCourseAction extends ActionSupport {

	private static final long serialVersionUID = -8583627367341674609L;
	
	private List<Integer> tags;
	
	private CourseService courseService;
	
	private String messageKey;

	public List<Integer> getTags() {
		return tags;
	}

	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}

	@Override
	public String execute() throws Exception {

		if(tags != null){
			
			
			try {
				getCourseService().deleteCourse(tags);
				setMessageKey("admin.course.delete.success");
			} catch (Exception e) {
				setMessageKey("admin.course.delete.error");
				throw e;
			}
			
			
		}else{
			
			setMessageKey("admin.course.delete.null");

		}
		
		return SUCCESS;
	}

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
}
