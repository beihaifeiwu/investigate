package com.course.selection.domain;

import java.io.Serializable;
/**
 * 
 * @author JiHan
 *
 */
public class ClassRoom implements Serializable{

	private static final long serialVersionUID = -1377082077076522885L;
	/**
	 * 　　Id：主属性
　　		roomName：教室名称，如：B-206
		Capacity：教室容量
	 */
	private int ID;
	private String RoomName;
	private int Capacity;
	/**
	 * 生成器与设置器
	 * @param ID
	 */
	public void setID(int ID){
		this.ID=ID;
	}
	public int getID(){
		return ID;
	}
	public void setRoomName(String RoomName){
		this.RoomName=RoomName;
	}
	public String getRoomName(){
		return RoomName;
	}
	public void setCapacity(int Capacity){
		this.Capacity=Capacity;
	}
	public int getCapacity(){
		return Capacity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassRoom other = (ClassRoom) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}
