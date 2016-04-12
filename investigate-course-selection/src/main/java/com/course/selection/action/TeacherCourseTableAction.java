package com.course.selection.action;

import com.course.selection.domain.Teacher;
import com.course.selection.service.CourseService;
import com.course.selection.service.TeacherService;
import com.course.selection.util.CourseTable;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 为教师生成课表的Action
 * @author Administrator
 *
 */
public class TeacherCourseTableAction extends ActionSupport {

	private static final long serialVersionUID = -3392063564511905402L;

	private CourseTable courseTable;
	
	private Teacher teacher;
	
	private CourseService courseService;
	
	private TeacherService teacherService;
	
	private Integer ID;
	
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
		
		teacher = getTeacherService().getTeacherByID(getID());
		if(teacher == null){
			setErrorKey("admin.teacher.coursetable.null");
			return ERROR;
		}
		CourseTable courseTable = getCourseService().generateCourseTableByTeacher(teacher);
		this.setCourseTable(courseTable);
		
		if(courseTable == null){
			setErrorKey("admin.teacher.coursetable.error");
			return ERROR;			
		}
		return SUCCESS;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
}
