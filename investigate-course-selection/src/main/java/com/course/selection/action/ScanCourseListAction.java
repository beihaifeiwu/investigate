package com.course.selection.action;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.Course;
import com.course.selection.service.CourseService;
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
		
		if(isJump()){
			
			PageQuery<Course> pq = pageQuery.getSpecificPage(getGotoPage());
			
			pageQuery = getCourseService().scanCourse(pq);
			
		}else if(pageQuery == null || pageQuery.isFirst()){
			
			pageQuery = getCourseService().scanCourse();
			
		}else{
			
			pageQuery = getCourseService().scanCourse(pageQuery);
			
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
}
