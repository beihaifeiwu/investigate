package com.course.selection.action.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AccessControlInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -9028430725547743361L;
	
	private String role;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String temp = (String) invocation.getInvocationContext().getSession().get("role");
		String result = null;
		//System.out.println("role = " + role + " now = " + temp);
		if(temp == null){ 							//用户未登录
			invocation.getInvocationContext().put("message", "您还未登录！");
			result = Action.ERROR;
		}else{
			if(role == null || temp.equals(role.trim())){	//用户获得执行权限
				result = invocation.invoke();
			}else{									//用户权限不够
				invocation.getInvocationContext().put("message", "您没有进行此操作的权限！");				
				result = Action.ERROR;
			}
		}
		
		return result;

	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
