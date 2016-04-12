package com.course.selection.action;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.Teacher;
import com.course.selection.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;

public class ScanTeacherListAction extends ActionSupport {

	private static final long serialVersionUID = -7635577824068366763L;
	/**
	 * 封装分页查找得到的数据以及请求参数
	 */
	private PageQuery<Teacher> pageQuery;
	
	/**
	 * 调用的业务逻辑组件
	 */
	private TeacherService teacherService;
	
	/**
	 * 是否为跳转
	 */
	private boolean isJump;

	/**
	 * 跳转到的指定页面
	 */
	private int gotoPage;
	
	private String action;

	/**
	 * @return the pageQuery
	 */
	public PageQuery<Teacher> getPageQuery() {
		return pageQuery;
	}

	/**
	 * @param pageQuery the pageQuery to set
	 */
	public void setPageQuery(PageQuery<Teacher> pageQuery) {
		this.pageQuery = pageQuery;
	}

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

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}


	@Override
	public String execute() throws Exception {
		if(isJump()){
			
			PageQuery<Teacher> pq = pageQuery.getSpecificPage(getGotoPage());
			
			pageQuery = getTeacherService().scanTeacherByPage(pq);
			
		}else if(pageQuery == null || pageQuery.isFirst()){
			
			pageQuery = getTeacherService().scanTeacherByPage();
			
		}else{
			
			pageQuery = getTeacherService().scanTeacherByPage(pageQuery);
			
		}
		
		return SUCCESS;
	}

}
