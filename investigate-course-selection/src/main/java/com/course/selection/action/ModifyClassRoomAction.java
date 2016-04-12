package com.course.selection.action;

import com.course.selection.domain.ClassRoom;
import com.course.selection.service.ClassRoomService;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyClassRoomAction extends ActionSupport {

	private static final long serialVersionUID = 2263371913528659356L;
	
	private int roomID;
	
	private int capacity;
	
	private String roomName;
	
	private String messageKey;
	
	private String errorKey;
	
	private ClassRoom classRoom;
	
	private ClassRoomService classRoomService;

	/**
	 * @return the roomID
	 */
	public int getRoomID() {
		return roomID;
	}

	/**
	 * @param roomID the roomID to set
	 */
	public void setRoomID(int roomID) {
		this.roomID = roomID;
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
	 * @return the classRoom
	 */
	public ClassRoom getClassRoom() {
		return classRoom;
	}

	/**
	 * @param classRoom the classRoom to set
	 */
	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}
	/**
	 * 修改教室的控制器方法
	 * @return
	 * @throws Exception
	 */
	public String modify() throws Exception{
		classRoom = getClassRoomService().getClassRoomByID(getRoomID());
		
		if(!classRoom.getRoomName().equals(getRoomName())){
			if(getClassRoomService().isClassRoomNameExist(getRoomName())){
				setErrorKey("admin.classroom.modify.exist");
				return ERROR;
			}
		}
		
		classRoom.setRoomName(getRoomName());
		classRoom.setCapacity(getCapacity());
		
		getClassRoomService().modifyClassRoom(classRoom);
		setMessageKey("admin.classroom.modify.success");
		
		return SUCCESS;
	}
	/**
	 * 准备修改教室的控制器方法
	 * @return
	 * @throws Exception
	 */
	public String prepare() throws Exception{
		
		if(getRoomID() == 0){
			setErrorKey("admin.classroom.modify.error");
			return ERROR;
		}
		ClassRoom room = getClassRoomService().getClassRoomByID(getRoomID());
		setClassRoom(room);
		
		return SUCCESS;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public ClassRoomService getClassRoomService() {
		return classRoomService;
	}

	public void setClassRoomService(ClassRoomService classRoomService) {
		this.classRoomService = classRoomService;
	}

}
