package com.course.selection.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1240724054350602732L;
	
	/**
	 * 封装上传文件域的属性
	 */
	private File upload;
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
	
	/**
	 * 下一个Action
	 */
	private String nextAction;
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
	 * 返回绝对路径
	 * @return the savePath
	 */
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	/**
	 * @param savePath the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	/**
	 * 获取上传文件的绝对路径
	 * @return
	 */
	public String getRealPath(){
		return this.realPath;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		
		if(getUpload() == null){
			addActionError(getText("upload.file.is.null"));
			return ERROR;
		}

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
		return SUCCESS;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public void setRealPath(String realPath) throws UnsupportedEncodingException {
	
		this.realPath = URLEncoder.encode(realPath,"UTF-8");
	}

}
