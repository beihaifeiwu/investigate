package com.course.selection.action;

import com.course.selection.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * 课程删除动作Action
 * @author Administrator
 *
 */
public class DeleteStudentAction extends ActionSupport {

	private static final long serialVersionUID = -8583627367341674609L;
	
	private List<Integer> tags;
	
	private String messageKey;
	
	private StudentService studentService;

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
				getStudentService().deleteStudent(tags);
				setMessageKey("admin.student.delete.success");
			} catch (Exception e) {
				setMessageKey("admin.student.delete.error");
				throw e;
			}
			
			
		}else{
			
			setMessageKey("admin.student.delete.null");

		}
		
		return SUCCESS;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
}
