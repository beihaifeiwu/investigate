package com.course.selection.action;

import com.course.selection.domain.Student;
import com.course.selection.service.CourseService;
import com.course.selection.service.StudentService;
import com.course.selection.util.CourseTable;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 为教室生成课表的Action
 * @author Administrator
 *
 */
public class StudentCourseTableAction extends ActionSupport {

	private static final long serialVersionUID = -3392063564511905402L;
	
	private Integer ID;

	private CourseTable courseTable;
	
	private Student student;
	
	private CourseService courseService;
	
	private StudentService studentService;
	
	private String errorKey;

	/**
	 * @return the courseTable
	 */
	public CourseTable getCourseTable() {
		return courseTable;
	}

	/**
	 * @param courseTable the courseTable to set
	 */
	public void setCourseTable(CourseTable courseTable) {
		this.courseTable = courseTable;
	}

	/**
	 * @return the courseService
	 */
	public CourseService getCourseService() {
		return courseService;
	}

	/**
	 * @param courseService the courseService to set
	 */
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	@Override
	public String execute() throws Exception {
		
		student = getStudentService().getStudentByID(getID());
		if(student == null){
			setErrorKey("admin.student.coursetable.null");
			return ERROR;
		}
		CourseTable courseTable = getCourseService().generateCourseTableByStudent(student);
		this.setCourseTable(courseTable);
		
		if(courseTable == null){
			setErrorKey("admin.student.coursetable.error");
			return ERROR;			
		}
		return SUCCESS;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
}
