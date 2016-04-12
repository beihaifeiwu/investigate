package com.course.selection.action;

import com.course.selection.domain.User;
import com.course.selection.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class ExtractImageAction extends ActionSupport {

	private static final long serialVersionUID = 3042719103601894545L;

	private Integer userID;
	
	private BufferedImage image;
	
	private UserService userService;
	
	private byte[] bytes = null;
	
    private String contentType = "image/jpeg";
    
    public int getContentLength(){
    	return bytes.length;
    }

    public byte[] getImageBytes(){
    	return bytes;
    }
	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public String execute() throws Exception {
		
		if (getUserID() != null) {
			User user = getUserService().getUserByID(getUserID());
			if (user.getProfile() != null) {
				setImage(user.getProfile().getPhoto());
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				try {
					javax.imageio.ImageIO.write(image, "jpg", bos);
					this.bytes = bos.toByteArray();
				} catch (Exception ex) {
				} finally {
					try {
						bos.close();
					} catch (Exception ex1) {
					}
				}
			}
		}
		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
