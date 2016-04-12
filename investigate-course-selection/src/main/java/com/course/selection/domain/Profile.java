/**
 * 
 */
package com.course.selection.domain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author JiHan
 *
 */
public class Profile implements Serializable {


	private static final long serialVersionUID = 1906418570931838500L;
	/**
	 * 　　Name：姓名
　　		phoneNumber：手机号码
　　		Email：电子邮箱
　　		Birthplace：籍贯
　　		BirthDay：出生年月日
		Photo：照片
	 */
	private int ID;
	private String Name;
	private String PhoneNumber;
	private String Email;
	private String department;
	private String BirthPlace;
	private Date BirthTime;
	private BufferedImage Photo;
	private byte[] imageData;

	public void setID(int ID){
		this.ID=ID;
	}
	public int getID(){
		return ID;
	}
	public void setName(String Name){
		this.Name=Name;
	}
	public String getName(){
		return Name;
	}
	public void setPhoneNumber(String PhoneNumber){
		this.PhoneNumber=PhoneNumber;
	}
	public String getPhoneNumber(){
		return PhoneNumber;
	}
	public void setEmail(String Email){
		this.Email=Email;
	}
	public String getEmail(){
		return Email;
	}
	public void setBirthPlace(String BirthPlace){
		this.BirthPlace=BirthPlace;
	}
	public String getBirthPlace(){
		return BirthPlace;
	}
	public void setBirthTime(Date BirthTime){
		this.BirthTime=BirthTime;
	}
	public Date getBirthTime(){
		return BirthTime;
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
		Profile other = (Profile) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	public BufferedImage getPhoto() {
		return Photo;
	}
	public void setPhoto(BufferedImage photo) throws IOException {
		Photo = photo;
		if(photo != null){
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(photo, "jpg", bos);
			bos.close();
			this.imageData = bos.toByteArray();
		}
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) throws IOException {
		this.imageData = imageData;
		if(imageData != null){
			ByteArrayInputStream bis = new ByteArrayInputStream(getImageData());
			BufferedImage image = ImageIO.read(bis);
			this.Photo = image;
		}
	}

}
