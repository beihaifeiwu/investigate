/**
 * 
 */
package com.course.selection.domain;

import java.io.Serializable;

/**
 * @author JiHan
 *
 */
public class Role implements Serializable {
	private static final long serialVersionUID = -4999611257373809035L;
	/**
	 * 　　Id：主属性
　　		roleName：角色名称，有“管理员”、“教师”、“学生”
	 */
	private int ID;
	private String RoleName;
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
	public void setRoleName(String RoleName){
		this.RoleName=RoleName;
	}
	public String getRoleName(){
		return RoleName;
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	

}
