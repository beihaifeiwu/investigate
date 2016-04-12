/**
 * 
 */
package com.course.selection.service;

import com.course.selection.domain.User;
import com.course.selection.exception.RoleNotExistException;
import com.course.selection.exception.UserExistException;

/**
 * @author JiHan
 *
 */
public interface UserService {
	/**
	 * ��½
	 * @param account
	 * @param password
	 * @return
	 */
	User logIn(String account, String password);
	/**
	 * �û��Ƿ��Ѵ���
	 * @param user
	 * @return
	 */
	Boolean isUserExist(User user);
	/**
	 * ����һ���û�
	 * @param user
	 * @return
	 * @throws RoleNotExistException 
	 * @throws UserExistException 
	 */
	Integer createUser(User user) throws RoleNotExistException, UserExistException;
	/**
	 * ���ݱ�ʶ���Ի�ȡ�û�ʵ��
	 * @param id
	 * @return
	 */
	User getUserByID(Integer id);
}
