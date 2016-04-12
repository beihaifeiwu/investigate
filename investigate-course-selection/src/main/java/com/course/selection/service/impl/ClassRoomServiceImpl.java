package com.course.selection.service.impl;

import com.course.selection.dao.*;
import com.course.selection.domain.ClassRoom;
import com.course.selection.domain.Course;
import com.course.selection.service.ClassRoomService;
import com.course.selection.util.CourseTable;

import java.util.ArrayList;
import java.util.List;

public class ClassRoomServiceImpl implements ClassRoomService {
	private StudentDAO studentDAO;
	private CourseDAO courseDAO;
	private TeacherDAO teacherDAO;
	private ClassRoomDAO classRoomDAO;
	private UserDAO userDAO;
	private RoleDAO roleDAO;
	/**
	 * @return studentDAO
	 */
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	/**
	 * @param studentDAO 要设置的 studentDAO
	 */
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	/**
	 * @return courseDAO
	 */
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	/**
	 * @param courseDAO 要设置的 courseDAO
	 */
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
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
	public List<Course> lookClassRoomCourses(ClassRoom classroom) {
		return this.courseDAO.findByClassRoom(classroom);
	}

	@Override
	public List<ClassRoom> lookClassRoom() {
		return this.classRoomDAO.findAll();
	}

	@Override
	public PageQuery<ClassRoom> lookClassRoomByPage() {
		return this.classRoomDAO.scanClassRoomByPage(null);
	}

	@Override
	public PageQuery<ClassRoom> lookClassRoomByPage(PageQuery<ClassRoom> pq) {
		return this.classRoomDAO.scanClassRoomByPage(pq);
	}

	@Override
	public Integer createClassRoom(ClassRoom room) {
		return this.classRoomDAO.save(room);
	}

	@Override
	public void createClassRooms(List<ClassRoom> rooms) {
		this.classRoomDAO.save(rooms);
	}

	@Override
	public List<String> getEmptyPeriod(ClassRoom room) {
		
		List<String> res = new ArrayList<>();
		
		List<Course> list = this.getCourseDAO().findByClassRoom(room);
		
		if(list != null){
			CourseTable courseTable = new CourseTable();
			for(Course course : list){
				courseTable.addCourse(course);
			}
			for(int i = 1; i <= 7; i++){
				for(int j = 1; j <= 11; j++){
					if(courseTable.get(i + "," + j) == null){
						res.add(i + "," + j);
					}
				}
			}
		}
		
		return res;
	}

	@Override
	public boolean deleteClassRoom(Integer id) {
		ClassRoom classRoom = getClassRoomDAO().get(id);
		if(isClassRoomEmpty(classRoom)){
			getClassRoomDAO().delete(classRoom);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteClassRoom(List<Integer> ids) {
		for(Integer id : ids){
			ClassRoom classRoom = getClassRoomDAO().get(id);
			if(! isClassRoomEmpty(classRoom)){
				return false;
			}
		}
		for(Integer id : ids){
			getClassRoomDAO().delete(id);
		}
		return true;
	}

	@Override
	public boolean isClassRoomEmpty(ClassRoom classRoom) {
		List<Course> list = getCourseDAO().findByClassRoom(classRoom);

		if(list != null && list.size() > 0){

			return false;
		}
		return true;
	}

	@Override
	public ClassRoom getClassRoomByName(String name) {
		return getClassRoomDAO().findbyName(name);
	}

	@Override
	public ClassRoom getClassRoomByID(Integer id) {
		return getClassRoomDAO().get(id);
	}

	@Override
	public Integer addNewClassRoom(ClassRoom room) {
		return getClassRoomDAO().save(room);
	}

	@Override
	public void modifyClassRoom(ClassRoom room) {
		getClassRoomDAO().updata(room);
	}

	@Override
	public boolean isClassRoomNameExist(String name) {
		ClassRoom classRoom = getClassRoomDAO().findbyName(name);
		return classRoom != null;
	}

	@Override
	public List<String> getClassRoomNames() {
		
		return getClassRoomDAO().findAllName();
	}

}
