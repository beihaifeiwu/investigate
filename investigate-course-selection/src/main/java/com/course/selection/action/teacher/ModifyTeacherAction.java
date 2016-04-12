package com.course.selection.action.teacher;

import com.course.selection.domain.Profile;
import com.course.selection.domain.Teacher;
import com.course.selection.service.TeacherService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ModifyTeacherAction extends ActionSupport {

	private static final long serialVersionUID = 2263371913528659356L;
	
	private int ID;
	
	private String messageKey;
	
	private String errorKey;
		
	private Teacher teacher;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String reNewPassword;
	
	/**
	 * 封装上传文件类型的属性
	 */
	private String uploadContentType;
	/**
	 * 封装上传文件名的属性
	 */
	private String uploadFileName;

	/**
	 * 直接在struts.xml文件中配置的属性
	 */
	private String savePath;
	/**
	 * 绝对路径
	 */
	private String realPath;
	
	private TeacherService teacherService;
	
	/**
	 * 封装上传文件域的属性
	 */
	private File upload;
	/**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * @param upload the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return the uploadContentType
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * @param uploadContentType the uploadContentType to set
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 * @param savePath the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
	 * @return the realPath
	 */
	public String getRealPath() {
		return realPath;
	}

	/**
	 * @param realPath the realPath to set
	 */
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
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
	 * 修改教室的控制器方法
	 * @return
	 * @throws Exception
	 */
	public String modify() throws Exception{
		Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
		setID(id);

		Teacher temp = getTeacherService().getTeacherByID(getID());
		
		temp.setTitle(getTeacher().getTitle());
		
		Profile tempPro = temp.getProfile();
		Profile pro = getTeacher().getProfile();
		
		tempPro.setBirthPlace(pro.getBirthPlace());
		tempPro.setBirthTime(pro.getBirthTime());
		tempPro.setDepartment(pro.getDepartment());
		tempPro.setEmail(pro.getEmail());
		tempPro.setName(pro.getName());
		tempPro.setPhoneNumber(pro.getPhoneNumber());
		
		if(getUpload() != null){
			File dirPath = new File(getSavePath());
			if(!dirPath.exists()){
				dirPath.mkdirs();
			}
			
			File filePath = new File(dirPath, getUploadFileName());
			FileOutputStream fos = new FileOutputStream(filePath);
			
			FileInputStream fis = new FileInputStream(getUpload());
			
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer)) > 0){
				fos.write(buffer, 0, len);
			}
			
			fos.close();
			fis.close();
			this.setRealPath(filePath.getAbsolutePath());
			
			BufferedImage photo = ImageIO.read(filePath);
			tempPro.setPhoto(photo);
		}
		
		getTeacherService().modifyTeacher(temp);
		
		setMessageKey("admin.teacher.modify.success");
		
		return SUCCESS;
	}
	/**
	 * 准备修改教室的控制器方法
	 * @return
	 * @throws Exception
	 */
	public String prepare() throws Exception{
		
		Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
		setID(id);
		if(getID() == 0){
			setErrorKey("admin.teacher.modify.error");
			return ERROR;
		}

		Teacher teacher = getTeacherService().getTeacherByID(getID());
		
		setTeacher(teacher);
		return SUCCESS;
	}
	
	public String modifyPassword() throws Exception{
		Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
		setID(id);
		Teacher temp = getTeacherService().getTeacherByID(getID());
		if(getOldPassword().equals(temp.getPassword()) 
				&& getNewPassword() != null && !getNewPassword().equals("")){
			temp.setPassword(getNewPassword());
		}else{
			setErrorKey("teacher.password.modify.error");
		}
		getTeacherService().modifyTeacher(temp);
		setMessageKey("teacher.password.modify.success");
		return SUCCESS;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

}
