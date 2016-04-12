package com.course.selection.action.student;

import com.course.selection.domain.Course;
import com.course.selection.domain.Student;
import com.course.selection.service.CourseService;
import com.course.selection.service.StudentService;
import com.opensymphony.xwork2.ActionContext;
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
	
	private StudentService studentService;
	
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
			Integer sid = (Integer) ActionContext.getContext().getSession().get("userID");
			Student stu = getStudentService().getStudentByID(sid);
			
			try {
				
				for(Integer id : tags){
					Course course = getCourseService().getCourseByID(id);
					getStudentService().rmCourse(course, stu);
				}
				getStudentService().modifyStudent(stu);
				
				setMessageKey("student.course.delete.success");
			} catch (Exception e) {
				setMessageKey("student.course.delete.error");
				throw e;
			}
			
			
		}else{
			
			setMessageKey("student.course.delete.null");

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

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
}
