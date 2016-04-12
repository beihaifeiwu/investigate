package com.course.selection.action;

import com.course.selection.domain.User;
import com.course.selection.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyAdminAction extends ActionSupport {

	private static final long serialVersionUID = 2263371913528659356L;
	
	private String messageKey;
	
	private String errorKey;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String reNewPassword;
	
	private AdminService adminService;

	/**
	 * @return the messageKey
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * @param messageKey the messageKey to set
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	/**
	 * 准备修改管理员的控制器方法
	 * @return
	 * @throws Exception
	 */
	public String prepare() throws Exception{
		Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
		if(id == 0){
			setErrorKey("admin.password.modify.error");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String modifyPassword() throws Exception{
		Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
		User temp = getAdminService().getAdminByID(id);
		if(getOldPassword().equals(temp.getPassword()) 
				&& getNewPassword() != null && !getNewPassword().equals("")){
			temp.setPassword(getNewPassword());
		}else{
			setErrorKey("admin.password.modify.error");
		}
		getAdminService().modifyAdmin(temp);
		setMessageKey("admin.password.modify.success");
		return SUCCESS;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

}
