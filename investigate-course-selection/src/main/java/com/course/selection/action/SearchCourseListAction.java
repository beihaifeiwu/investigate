package com.course.selection.action;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.Course;
import com.course.selection.service.CourseService;
import com.course.selection.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;

public class SearchCourseListAction extends ActionSupport {

	private static final long serialVersionUID = 6341067979424312099L;
	
	private int method;
	
	private String content;
	
	private String action = "searchCourseByMethod";
	
	private String errorKey;
	
	/**
	 * 封装分页查找得到的数据以及请求参数
	 */
	private PageQuery<Course> pageQuery;
	
	/**
	 * 调用的业务逻辑组件
	 */
	private CourseService courseService;
	
	private TeacherService teacherService;
	
	/**
	 * 是否为跳转
	 */
	private boolean isJump;
	
	/**
	 * 跳转到的指定页面
	 */
	private int gotoPage;

	/**
	 * @return the pageQuery
	 */
	public PageQuery<Course> getPageQuery() {
		return pageQuery;
	}

	/**
	 * @param pageQuery the pageQuery to set
	 */
	public void setPageQuery(PageQuery<Course> pageQuery) {
		this.pageQuery = pageQuery;
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

	/**
	 * @return the isJump
	 */
	public boolean isJump() {
		return isJump;
	}

	/**
	 * @param isJump the isJump to set
	 */
	public void setJump(boolean isJump) {
		this.isJump = isJump;
	}

	/**
	 * @return the gotoPage
	 */
	public int getGotoPage() {
		return gotoPage;
	}

	/**
	 * @param gotoPage the gotoPage to set
	 */
	public void setGotoPage(int gotoPage) {
		this.gotoPage = gotoPage;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String execute() throws Exception {
		
		if(isJump()){
			
			PageQuery<Course> pq = pageQuery.getSpecificPage(getGotoPage());
			
			switch(method){
			case 1:
				pageQuery = getCourseService().scanCourseByCourseNumber(pq, content);
				break;
			case 2:
				pageQuery = getCourseService().scanCourseByCourseName(pq, content);
				break;
			case 3:
				pageQuery = getCourseService().scanCourseByTeacherName(pq,content);
				break;
			case 4:
			default:
				pageQuery = getCourseService().scanCourse(pq);
				break;
			}
			
		}else if(pageQuery == null || pageQuery.isFirst()){
			
			switch(method){
			case 1:
				pageQuery = getCourseService().scanCourseByCourseNumber(content);
				break;
			case 2:
				pageQuery = getCourseService().scanCourseByCourseName(content);
				break;
			case 3:
				pageQuery = getCourseService().scanCourseByTeacherName(content);
				break;
			case 4:
			default:
				pageQuery = getCourseService().scanCourse();
				break;
			}

			
		}else{
			
			switch(method){
			case 1:
				pageQuery = getCourseService().scanCourseByCourseNumber(pageQuery, content);
				break;
			case 2:
				pageQuery = getCourseService().scanCourseByCourseName(pageQuery, content);
				break;
			case 3:
				pageQuery = getCourseService().scanCourseByTeacherName(pageQuery,content);
				break;
			case 4:
			default:
				pageQuery = getCourseService().scanCourse(pageQuery);
				break;
			}
		}
		
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
}
