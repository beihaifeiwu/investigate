/**
 * 
 */
package com.course.selection.domain;

import java.io.Serializable;
//import java.util.Set;

/**
 * @author JiHan
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 5415776578485276952L;

	/**
	 * 　　Id：主属性
　　		Account：账号
　　		Password：密码
　　		Role：role类
		Profile：profile类
	 */
	private int ID;
	private String Account;
	private String Password;
	private Role role;
	private Profile profile;
	//private Set<Course> course;
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
	public void setAccount(String Account){
		this.Account=Account;
	}
	public String getAccount(){
		return Account;
	}
	public void setPassword(String Password){
		this.Password=Password;
	}
	public String getPassword(){
		return Password;
	}
	public void setRole(Role role){
		this.role=role;
	}
	public Role getRole(){
		return role;
	}
	public void setProfile(Profile profile){
		this.profile=profile;
	}
	public Profile getProfile(){
		return profile;
	}
	
	/*public Set<Course> getCourse() {
		return course;
	}
	public void setCourse(Set<Course> course) {
		this.course = course;
	}*/
	
	
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
		User other = (User) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

}
