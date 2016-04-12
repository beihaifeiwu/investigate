package com.course.selection.action.teacher;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.Course;
import com.course.selection.domain.Teacher;
import com.course.selection.service.CourseService;
import com.course.selection.service.TeacherService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author Administrator
 *
 */
public class ScanCourseListAction extends ActionSupport {

	private static final long serialVersionUID = 7022976876095320500L;
	
	/**
	 * 封装分页查找得到的数据以及请求参数
	 */
	private PageQuery<Course> pageQuery;
	
	/**
	 * 调用的业务逻辑组件
	 */
	private CourseService courseService;
	
	private TeacherService teacherService;
	
	private String action;
	
	/**
	 * @return the teacherService
	 */
	public TeacherService getTeacherService() {
		return teacherService;
	}

	/**
	 * @param teacherService the teacherService to set
	 */
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	/**
	 * 是否为跳转
	 */
	private boolean isJump;
	
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
		
		Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
		Teacher teacher = getTeacherService().getTeacherByID(id);
		
		if(isJump()){
			
			PageQuery<Course> pq = pageQuery.getSpecificPage(getGotoPage());
			
			pageQuery = getCourseService().scanCourseByTeacher(pq,teacher);
			
		}else if(pageQuery == null || pageQuery.isFirst()){
			
			pageQuery = getCourseService().scanCourseByTeacher(teacher);
			
		}else{
			
			pageQuery = getCourseService().scanCourseByTeacher(pageQuery, teacher);
			
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
}
