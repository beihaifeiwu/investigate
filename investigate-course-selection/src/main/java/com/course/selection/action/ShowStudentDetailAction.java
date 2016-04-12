package com.course.selection.action;

import com.course.selection.domain.Student;
import com.course.selection.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;

public class ShowStudentDetailAction extends ActionSupport {

	private static final long serialVersionUID = -2050501120921861604L;

	private Integer ID;
	
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

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	@Override
	public String execute() throws Exception {
		
		if(getID() == null){
			setErrorKey("admin.student.show.null");
			return ERROR;
		}
		student = getStudentService().getStudentByID(getID());
		
		return SUCCESS;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

}
