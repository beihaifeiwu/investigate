/**
 * 
 */
package com.course.selection.service.impl;

import com.course.selection.dao.*;
import com.course.selection.domain.Course;
import com.course.selection.domain.Role;
import com.course.selection.domain.Student;
import com.course.selection.domain.Teacher;
import com.course.selection.service.TeacherService;

import java.util.List;

/**
 * @author JiHan
 *
 */
public class TeacherServiceImpl implements TeacherService {
	private StudentDAO studentDAO;
	private CourseDAO courseDAO;
	private TeacherDAO teacherDAO;
	private ClassRoomDAO classRoomDAO;
	private UserDAO userDAO;
	private RoleDAO roleDAO;
	/* （非 Javadoc）
	 * @see com.course.selection.service.TeacherService#TeaCourses(com.course.selection.domain.Teacher)
	 */
	@Override
	public List<Course> TeaCourses(Teacher teacher) {
		List<Course> result=courseDAO.findByTeacher(teacher);
		return result;
	}

	/* （非 Javadoc）
	 * @see com.course.selection.service.TeacherService#CouStudents(com.course.selection.domain.Course)
	 */
	@Override
	public List<Student> CouStudents(Course course) {
		List<Student> result=studentDAO.findByCourse(course);
		return result;
	}

	@Override
	public PageQuery<Student> lookStudentsByCourse(Course course) {
		return this.studentDAO.scanCourseStudentByPage(null, course);
	}

	@Override
	public PageQuery<Student> lookStudentsByCourse(PageQuery<Student> pq,
			Course course) {
		return this.studentDAO.scanCourseStudentByPage(pq, course);
	}

	/**
	 * @return teacherDAO
	 */
	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	/**
	 * @param teacherDAO 要设置的 teacherDAO
	 */
	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	/**
	 * @return classRoomDAO
	 */
	public ClassRoomDAO getClassRoomDAO() {
		return classRoomDAO;
	}

	/**
	 * @param classRoomDAO 要设置的 classRoomDAO
	 */
	public void setClassRoomDAO(ClassRoomDAO classRoomDAO) {
		this.classRoomDAO = classRoomDAO;
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

	@Override
	public Integer createTeacher(Teacher teacher) {
		Role role = this.roleDAO.findByRoleName("Teacher");
		teacher.setRole(role);
		return this.teacherDAO.save(teacher);
	}

	@Override
	public void createTeachers(List<Teacher> teachers) {
		
		Role role = this.roleDAO.findByRoleName("Teacher");
		for(Teacher teacher : teachers){
			teacher.setRole(role);
		}
		this.teacherDAO.save(teachers);
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

	@Override
	public Teacher getTeacherByAccount(String account) {
		
		return getTeacherDAO().findTeacherByAccount(account);
	}

	@Override
	public PageQuery<Teacher> scanTeacherByPage() {
		
		return getTeacherDAO().scanTeacherByPage(null);
	}

	@Override
	public PageQuery<Teacher> scanTeacherByPage(PageQuery<Teacher> pq) {
		return getTeacherDAO().scanTeacherByPage(pq);
	}

	@Override
	public void deleteTeachers(List<Integer> ids) {
		for(Integer id: ids){
			getTeacherDAO().delete(id);
		}
	}

	@Override
	public Teacher getTeacherByID(Integer id) {
		
		return getTeacherDAO().get(id);
	}

	@Override
	public void modifyTeacher(Teacher teacher) {
		
		getTeacherDAO().updata(teacher);
		
	}

	@Override
	public Integer addTeacher(Teacher teacher) {
		return getTeacherDAO().save(teacher);
	}

}
