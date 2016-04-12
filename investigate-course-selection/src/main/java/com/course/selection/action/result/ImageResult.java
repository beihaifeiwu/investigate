package com.course.selection.action.result;

import com.course.selection.action.ExtractImageAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
 
public class ImageResult implements Result {

	private static final long serialVersionUID = -8877224637402808288L;

	public void execute(ActionInvocation ai) throws Exception {
      
    	ExtractImageAction action = (ExtractImageAction)ai.getAction();
      
       HttpServletResponse response = ServletActionContext.getResponse();
       response.setHeader("Cash", "no cash");
       response.setContentType(action.getContentType());
       response.setContentLength(action.getContentLength());
       response.getOutputStream().write(action.getImageBytes());
       response.getOutputStream().flush();
    }
}