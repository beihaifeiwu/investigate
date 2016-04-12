package com.course.selection.action;

import com.course.selection.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * 课程删除动作Action
 * @author Administrator
 *
 */
public class DeleteTeacherAction extends ActionSupport {

	private static final long serialVersionUID = -8583627367341674609L;
	
	private List<Integer> tags;
	
	private String messageKey;
	
	private TeacherService teacherService;

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
				getTeacherService().deleteTeachers(tags);
				setMessageKey("admin.teacher.delete.success");
			} catch (Exception e) {
				setMessageKey("admin.teacher.delete.error");
				throw e;
			}
			
			
		}else{
			
			setMessageKey("admin.teacher.delete.null");

		}
		
		return SUCCESS;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
}
