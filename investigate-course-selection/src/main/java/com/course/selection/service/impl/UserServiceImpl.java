/**
 * 
 */
package com.course.selection.service.impl;

import com.course.selection.dao.*;
import com.course.selection.domain.Role;
import com.course.selection.domain.User;
import com.course.selection.exception.RoleNotExistException;
import com.course.selection.exception.UserExistException;
import com.course.selection.service.UserService;

import java.util.List;

/**
 * @author JiHan
 *
 */
public class UserServiceImpl implements UserService {

	private StudentDAO studentDAO;

	private CourseDAO courseDAO;

	private TeacherDAO teacherDAO;

	private ClassRoomDAO classRoomDAO;
	
	private UserDAO userDAO;

	private RoleDAO roleDAO;

	@Override
	public User logIn(String account, String password) {
		List<User> users = this.userDAO.findByNameAndPasswd(account, password);
		for(User user : users){
			if(user.getAccount().equals(account)&&user.getPassword().equals(password))
				return user;
		}
		return null;
	}

	/**
	 * @return the studentDAO
	 */
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	/**
	 * @param studentDAO the studentDAO to set
	 */
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	/**
	 * @return the courseDAO
	 */
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	/**
	 * @param courseDAO the courseDAO to set
	 */
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	/**
	 * @return the teacherDAO
	 */
	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	/**
	 * @param teacherDAO the teacherDAO to set
	 */
	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	/**
	 * @return the classRoomDAO
	 */
	public ClassRoomDAO getClassRoomDAO() {
		return classRoomDAO;
	}

	/**
	 * @param classRoomDAO the classRoomDAO to set
	 */
	public void setClassRoomDAO(ClassRoomDAO classRoomDAO) {
		this.classRoomDAO = classRoomDAO;
	}

	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return the roleDAO
	 */
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	/**
	 * @param roleDAO the roleDAO to set
	 */
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public Integer createUser(User user) throws RoleNotExistException, UserExistException {
		if(user == null) return null;
		//检查用户的角色，默认为管理员
		if(user.getRole() == null){
			Role role = this.getRoleDAO().findByRoleName("Admin");
			user.setRole(role);
		}else{
			Role role = this.getRoleDAO().findByRoleName(user.getRole().getRoleName());
			if(role == null) throw new RoleNotExistException();
			user.setRole(role);
		}
		//检测用户是否已存在
		if(isUserExist(user)) throw new UserExistException();
		return getUserDAO().save(user);
	}

	@Override
	public Boolean isUserExist(User user) {
		List<User> users = this.getUserDAO()
							   .findByNameAndPasswd(user.getAccount(), user.getPassword());
		if(users != null && !users.isEmpty()) return true;
		return false;
	}

	@Override
	public User getUserByID(Integer id) {
		
		return getUserDAO().get(id);
	}

}
