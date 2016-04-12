package com.course.selection.action.student;

import com.course.selection.domain.Student;
import com.course.selection.service.StudentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowStudentDetailAction extends ActionSupport {

	private static final long serialVersionUID = -2050501120921861604L;
	
	private Student student;
	
	private String errorKey;
	
	private StudentService studentService;

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return the studentService
	 */
	public StudentService getStudentService() {
		return studentService;
	}

	/**
	 * @param studentService the studentService to set
	 */
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public String execute() throws Exception {
		
		Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
		
		if(id == null){
			setErrorKey("admin.student.show.null");
			return ERROR;
		}
		student = getStudentService().getStudentByID(id);
		
		return SUCCESS;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

}
