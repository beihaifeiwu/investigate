package com.course.selection.action;

import com.course.selection.service.ClassRoomService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * 课程删除动作Action
 * @author Administrator
 *
 */
public class DeleteClassRoomAction extends ActionSupport {

	private static final long serialVersionUID = -8583627367341674609L;
	
	private List<Integer> tags;
	
	private String messageKey;
	
	private ClassRoomService classRoomService;

	public List<Integer> getTags() {
		return tags;
	}

	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}

	@Override
	public String execute() throws Exception {

		if(tags != null){
				if(getClassRoomService().deleteClassRoom(tags)){
					setMessageKey("admin.classroom.delete.success");					
				}else{
					setMessageKey("admin.classroom.delete.error");
				}
		}else{
			setMessageKey("admin.classroom.delete.null");
		}
		
		return SUCCESS;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public ClassRoomService getClassRoomService() {
		return classRoomService;
	}

	public void setClassRoomService(ClassRoomService classRoomService) {
		this.classRoomService = classRoomService;
	}
}
