package com.course.selection.action;

import com.course.selection.domain.Role;
import com.course.selection.domain.User;
import com.course.selection.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = -7706526671081509348L;
	
	private String username;
	
	private String passwd;
	
	private UserService userService;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		User user = this.getUserService().logIn(getUsername(), getPasswd());
		
		if(user != null){
			ctx.getSession().put("userID", user.getID());
			Role role = user.getRole();
			ctx.getSession().put("role", role.getRoleName());
			if(role.getRoleName().equals("Admin")){
				ctx.getSession().put("username", "Admin");
			}else{
				ctx.getSession().put("username", user.getProfile().getName());
			}
			return SUCCESS;
		}
		ctx.put("message", "用户不存在或密码错误");
		return INPUT;
	}



}
