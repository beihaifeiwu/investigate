package com.course.selection.service.impl;

import com.course.selection.dao.*;
import com.course.selection.domain.*;
import com.course.selection.exception.RoleExistException;
import com.course.selection.service.AdminService;

import java.util.List;
/**
 * 管理员业务层实现类
 * @author Administrator
 *
 */
public class AdminServiceImpl implements AdminService {
	
	private StudentDAO studentDAO;
	private CourseDAO courseDAO;
	private TeacherDAO teacherDAO;
	private ClassRoomDAO classRoomDAO;
	private UserDAO userDAO;
	private RoleDAO roleDAO;
	
	@Override
	public PageQuery<Student> searchStudentByAll() {
		return this.studentDAO.scanStudentByPage(null);
	}

	@Override
	public PageQuery<Student> searchStudentByAll(PageQuery<Student> pq) {
		return this.studentDAO.scanStudentByPage(pq);
	}

	@Override
	public PageQuery<Student> searchStudentByName(String name) {
		return this.studentDAO.scanStudentByNameByPage(null, name);
	}

	@Override
	public PageQuery<Student> searchStudentByName(PageQuery<Student> pq,
			String name) {
		return this.studentDAO.scanStudentByNameByPage(pq, name);
	}

	@Override
	public PageQuery<Student> searchStudentByClass(String clazz) {
		return this.studentDAO.scanStudentByClassByPage(null, clazz);
	}

	@Override
	public PageQuery<Student> searchStudentByClass(PageQuery<Student> pq,
			String clazz) {
		return this.studentDAO.scanStudentByClassByPage(pq, clazz);
	}

	@Override
	public PageQuery<Student> searchStudentByDepartment(String department) {
		return this.studentDAO.scanStudentByDepartmentByPage(null, department);
	}

	@Override
	public PageQuery<Student> searchStudentByDepartment(PageQuery<Student> pq,
			String department) {
		return this.studentDAO.scanStudentByDepartmentByPage(pq, department);
	}

	@Override
	public void deleteCourse(Course course) {
		courseDAO.delete(course);
		course.getCourseState().setStateType(CourseStateType.DELETED);
		courseDAO.update(course);
	}

	@Override
	public void addCourse(Course course) {
		courseDAO.save(course);
		course.getCourseState().setStateType(CourseStateType.CREATE);
	}

	@Override
	public boolean addCourseToClassRoom(Course course, ClassRoom classroom) {
		List<Course> allCourses = this.courseDAO.findAll();
		for(Course c : allCourses){
			if((c.getClassroom().getRoomName()==classroom.getRoomName())
					&&(c.getTimepoint()==course.getTimepoint()))
				return false;
		}
		course.setClassroom(classroom);
		courseDAO.update(course);
		return true;
	}

	@Override
	public void alterCourse(Course course) {
		this.courseDAO.update(course);
		course.getCourseState().setStateType(CourseStateType.MODIFIED);
	}
	@Override
	public PageQuery<Teacher> searchTeacherByAll() {
		return this.teacherDAO.scanTeacherByPage(null);
	}

	@Override
	public PageQuery<Teacher> searchTeacherByAll(PageQuery<Teacher> pq) {
		return this.teacherDAO.scanTeacherByPage(pq);
	}

	@Override
	public PageQuery<Teacher> searchTeacherByName(String name) {
		return this.teacherDAO.scanTeacherByNameByPage(null, name);
	}

	@Override
	public PageQuery<Teacher> searchTeacherByName(PageQuery<Teacher> pq,
			String name) {
		return this.teacherDAO.scanTeacherByNameByPage(pq, name);
	}
	@Override
	public PageQuery<ClassRoom> searchClassRoomByAll() {
		return this.classRoomDAO.scanClassRoomByPage(null);
	}

	@Override
	public PageQuery<ClassRoom> searchClassRoomByAll(PageQuery<ClassRoom> pq) {
		return this.classRoomDAO.scanClassRoomByPage(pq);
	}

	/**
	 * @return userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO 要设置的 userDAO
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return roleDAO
	 */
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	/**
	 * @param roleDAO 要设置的 roleDAO
	 */
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
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

	@Override
	public Integer createRole(Role role) throws RoleExistException {
		
		Role temp = this.getRoleDAO().findByRoleName(role.getRoleName());
		
		if(temp != null) throw new RoleExistException();
		
		Integer id = this.getRoleDAO().save(role);
		
		return id;
	}

	@Override
	public void modifyAdmin(User user) {
		
		getUserDAO().update(user);
		
	}

	@Override
	public User getAdminByID(Integer id) {
		return getUserDAO().get(id);
	}

	
}
