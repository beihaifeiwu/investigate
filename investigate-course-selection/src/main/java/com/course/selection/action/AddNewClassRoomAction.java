package com.course.selection.action;

import com.course.selection.domain.ClassRoom;
import com.course.selection.service.ClassRoomService;
import com.opensymphony.xwork2.ActionSupport;

public class AddNewClassRoomAction extends ActionSupport {

	private static final long serialVersionUID = 6963090711286298954L;

	private String roomName;
	
	private int capacity;
	
	private ClassRoomService classRoomService;
	
	private String messageKey;

	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the classRoomService
	 */
	public ClassRoomService getClassRoomService() {
		return classRoomService;
	}

	/**
	 * @param classRoomService the classRoomService to set
	 */
	public void setClassRoomService(ClassRoomService classRoomService) {
		this.classRoomService = classRoomService;
	}

	@Override
	public String execute() throws Exception {
		
		if(getClassRoomService().isClassRoomNameExist(getRoomName())){
			setMessageKey("admin.classroom.add.exist");
		}else{
			ClassRoom room = new ClassRoom();
			room.setCapacity(getCapacity());
			room.setRoomName(getRoomName());
			getClassRoomService().addNewClassRoom(room);
			setMessageKey("admin.classroom.add.success");
		}

		return SUCCESS;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

}
