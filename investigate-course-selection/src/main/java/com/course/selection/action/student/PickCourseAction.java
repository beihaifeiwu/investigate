package com.course.selection.action.student;

import com.course.selection.domain.Course;
import com.course.selection.domain.Student;
import com.course.selection.service.CourseService;
import com.course.selection.service.StudentService;
import com.course.selection.util.CourseTable;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;

import java.util.List;
import java.util.Set;

/**
 * 课程删除动作Action
 * @author Administrator
 *
 */
public class PickCourseAction extends ActionSupport {

	private static final long serialVersionUID = -8583627367341674609L;
	
	private List<Integer> tags;
	
	private CourseService courseService;
	
	private StudentService studentService;
	
	/**
	 * @return the studentService
	 */
	@JSON(serialize=false)
	public StudentService getStudentService() {
		return studentService;
	}

	/**
	 * @param studentService the studentService to set
	 */
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	private String result;

	@JSON(serialize=false)
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
				Set<Course> courses = stu.getCourse();
				for(Integer id : tags){
					boolean conflict = false;
					Course temp = getCourseService().getCourseByID(id);
					if(courses.contains(temp)){
						setResult("exist");
						return SUCCESS;
					}
					for(Course course : courses){
						if(!CourseTable.isConflict(temp, course)){
							conflict = true;
							break;
						}
					}
					if(!conflict){
						if(!getStudentService().addCourse(temp, stu)){
							setResult("full");
							return SUCCESS;
						}
					}else{
						setResult("conflict");
						return SUCCESS;
					}
				}
				getStudentService().modifyStudent(stu);
				setResult("selected");
				
			} catch (Exception e) {
				setResult("error");
			}
			
		}else{
			setResult("null");
		}
		return SUCCESS;
	}
	@JSON(serialize=false)
	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
