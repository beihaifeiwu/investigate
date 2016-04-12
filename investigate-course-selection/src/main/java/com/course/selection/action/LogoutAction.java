package com.course.selection.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 退出登录的控制器
 * @author Administrator
 *
 */
public class LogoutAction extends ActionSupport {

	private static final long serialVersionUID = 7606796119677630083L;

	public LogoutAction() {
		
	}
	/**
	 * 实现注销逻辑的方法
	 */
	@Override
	public String execute() throws Exception {
		
		ActionContext ctx = ActionContext.getContext();
		ctx.getSession().remove("userID");
		ctx.getSession().remove("role");
		ctx.getSession().remove("username");
		
		return SUCCESS;
	}

}
