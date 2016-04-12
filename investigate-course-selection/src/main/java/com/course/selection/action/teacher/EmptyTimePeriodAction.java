package com.course.selection.action.teacher;

import com.course.selection.domain.ClassRoom;
import com.course.selection.service.ClassRoomService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;

import java.util.List;

public class EmptyTimePeriodAction extends ActionSupport {

	private static final long serialVersionUID = -4022265237015735539L;
	
	private String roomName;
	
	private List<String> list;
	
	private ClassRoomService classRoomService;

	@Override
	public String execute() throws Exception {
		if(roomName != null && !roomName.equals("")){
			ClassRoom cr = getClassRoomService().getClassRoomByName(roomName);
			list = getClassRoomService().getEmptyPeriod(cr);
		}
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	@JSON(serialize=false)
	public ClassRoomService getClassRoomService() {
		return classRoomService;
	}

	public void setClassRoomService(ClassRoomService classRoomService) {
		this.classRoomService = classRoomService;
	}

}
