package com.course.selection.action.student;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.Course;
import com.course.selection.domain.Student;
import com.course.selection.service.CourseService;
import com.course.selection.service.StudentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ScanSelectedCourseListAction extends ActionSupport {

	private static final long serialVersionUID = -8466300157993981394L;
	
	/**
	 * 封装分页查找得到的数据以及请求参数
	 */
	private PageQuery<Course> pageQuery;
	
	/**
	 * 调用的业务逻辑组件
	 */
	private CourseService courseService;
	
	private StudentService studentService;
	
	/**
	 * 是否为跳转
	 */
	private boolean isJump;
	
	private String action;
	
	/**
	 * 跳转到的指定页面
	 */
	private int gotoPage;

	public PageQuery<Course> getPageQuery() {
		return pageQuery;
	}

	public void setPageQuery(PageQuery<Course> pageQuery) {
		this.pageQuery = pageQuery;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@Override
	public String execute() throws Exception {
		
		Integer sid = (Integer) ActionContext.getContext().getSession().get("userID");
		Student stu = getStudentService().getStudentByID(sid);
		
		if(isJump()){
			
			PageQuery<Course> pq = pageQuery.getSpecificPage(getGotoPage());
			
			pageQuery = getCourseService().scanCourseByStudent(pq, stu);
			
		}else if(pageQuery == null || pageQuery.isFirst()){
			
			pageQuery = getCourseService().scanCourseByStudent(stu);
			
		}else{
			
			pageQuery = getCourseService().scanCourseByStudent(pageQuery, stu);
			
		}
		
		return SUCCESS;
	}

	public int getGotoPage() {
		return gotoPage;
	}

	public void setGotoPage(int gotoPage) {
		this.gotoPage = gotoPage;
	}

	public boolean isJump() {
		return isJump;
	}

	public void setJump(boolean isJump) {
		this.isJump = isJump;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

}
