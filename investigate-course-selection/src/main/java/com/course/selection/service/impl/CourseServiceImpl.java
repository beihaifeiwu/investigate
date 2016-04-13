package com.course.selection.service.impl;

import com.course.selection.dao.*;
import com.course.selection.domain.*;
import com.course.selection.service.CourseService;
import com.course.selection.util.CourseTable;

import java.util.List;
import java.util.Set;

public class CourseServiceImpl implements CourseService {
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
	public Integer createCourse(Course course) {
		
		boolean isReady = true;
		
		//设置课程教室
		if(course.getClassroom() != null){
			String name = course.getClassroom().getRoomName();
			ClassRoom room = this.classRoomDAO.findbyName(name);
			if(room != null)
				course.setClassroom(room);
			else
				isReady = false;
		}else{
			isReady = false;
		}
		//设置任课教师
		if(course.getTeacher() != null){
			String account = course.getTeacher().getAccount();
			Teacher teacher = this.teacherDAO.findTeacherByAccount(account);
			if(teacher != null)
				course.setTeacher(teacher);
			else
				isReady = false;
		}else{
			isReady = false;
		}
		//设置课程状态
		CourseState cs = course.getCourseState();
		if(cs == null) cs = new CourseState();
		if(isReady){
			cs.setStateType(CourseStateType.INDATABASE);			
		}else{
			cs.setStateType(CourseStateType.CREATE);
		}
		cs.setStudentNumber(0);
		course.setCourseState(cs);
		
		return this.courseDAO.save(course);
	}
	@Override
	public void createCourse(List<Course> courses) {
    courses.forEach(this::createCourse);
	}
	@Override
	public PageQuery<Course> scanCourse() {
		return getCourseDAO().scanCourseByPage(null);
	}
	@Override
	public PageQuery<Course> scanCourse(PageQuery<Course> pq) {
		return getCourseDAO().scanCourseByPage(pq);
	}
	@Override
	public PageQuery<Course> scanNotDeletedCourse() {
		return getCourseDAO().scanCourseByNotThatType(null, CourseStateType.DELETED);
	}
	@Override
	public PageQuery<Course> scanNotDeletedCourse(PageQuery<Course> pq) {
		return getCourseDAO().scanCourseByNotThatType(pq, CourseStateType.DELETED);
	}
	@Override
	public PageQuery<Course> scanPickableCourse() {
		return getCourseDAO().scanCourseByType(null, CourseStateType.PICKABLE);
	}
	@Override
	public PageQuery<Course> scanPickableCourse(PageQuery<Course> pq) {
		return getCourseDAO().scanCourseByType(pq, CourseStateType.PICKABLE);
	}
	@Override
	public void removeAllDeletedCourse() {
		List<Course> list = getCourseDAO().findByType(CourseStateType.DELETED);
		for(Course course : list){
			getCourseDAO().removeFromDatabase(course);
		}
	}
	@Override
	public Course getCourseByID(Integer id) {
		return getCourseDAO().get(id);
	}
	@Override
	public void deleteCourse(Integer id) {
		Course course = getCourseByID(id);
		List<Student> students = getStudentDAO().findByCourse(course);
		for(Student student : students){
			student.getCourse().remove(course);
		}
		getCourseDAO().delete(id);
	}
	@Override
	public void deleteCourse(List<Integer> list) {

		for(Integer id : list){
			Course course = getCourseByID(id);
			List<Student> students = getStudentDAO().findByCourse(course);
			for(Student student : students){
				student.getCourse().remove(course);
			}
			getCourseDAO().delete(id);			
		}
	}
	@Override
	public boolean isExist(String courseNumber, String classNumber) {
		if(getCourseDAO().findByCourseNumberAndClassNumber(courseNumber, classNumber) != null){
			return true;
		}
		return false;
	}
	@Override
	public PageQuery<Course> scanUnReadyCourse() {
		return getCourseDAO().scanCourseByType(null, CourseStateType.CREATE);
	}
	@Override
	public PageQuery<Course> scanUnReadyCourse(PageQuery<Course> pq) {
		return getCourseDAO().scanCourseByType(pq, CourseStateType.CREATE);
	}
	@Override
	public void modifyCourse(Course course) {
		
		getCourseDAO().update(course);
	}
	@Override
	public PageQuery<Course> scanDeletedCourse() {
		return getCourseDAO().scanCourseByType(null, CourseStateType.DELETED);
	}
	@Override
	public PageQuery<Course> scanDeletedCourse(PageQuery<Course> pq) {
		return getCourseDAO().scanCourseByType(pq, CourseStateType.DELETED);
	}
	@Override
	public PageQuery<Course> scanCourseByCourseNumber(String courseNumber) {
		
		return getCourseDAO().scanCourseByCourseNum(null, courseNumber);
	}
	@Override
	public PageQuery<Course> scanCourseByCourseNumber(PageQuery<Course> pq,
			String courseNumber) {
		return getCourseDAO().scanCourseByCourseNum(null, courseNumber);
	}
	@Override
	public PageQuery<Course> scanCourseByCourseName(String courseName) {
		return getCourseDAO().scanCourseByCourseName(null, courseName);
	}
	@Override
	public PageQuery<Course> scanCourseByCourseName(PageQuery<Course> pq,
			String courseName) {
		return getCourseDAO().scanCourseByCourseName(pq, courseName);
	}
	@Override
	public PageQuery<Course> scanCourseByTeacher(Teacher teacher) {
		return getCourseDAO().scanTeacherCourseByPage(null, teacher);
	}
	@Override
	public PageQuery<Course> scanCourseByTeacher(PageQuery<Course> pq,
			Teacher teacher) {
		return getCourseDAO().scanTeacherCourseByPage(pq, teacher);
	}
	@Override
	public CourseTable generateCourseTableByClassRoom(ClassRoom classRoom) {
		
		List<Course> courses = getCourseDAO().findByClassRoom(classRoom);
		CourseTable courseTable = new CourseTable();
		for(Course course : courses){
			courseTable.addCourse(course);
		}
		return courseTable;
	}
	@Override
	public void openSelectCourse() {
		List<Course> courses = getCourseDAO().findByType(CourseStateType.INDATABASE);
		for(Course course : courses){
			course.getCourseState().setStateType(CourseStateType.PICKABLE);
		}
		for(Course course : courses){
			getCourseDAO().update(course);
		}
	}
	@Override
	public void closeSelectCourse() {
		List<Course> courses = getCourseDAO().findByType(CourseStateType.PICKABLE);
		for(Course course : courses){
			course.getCourseState().setStateType(CourseStateType.INDATABASE);
		}
		for(Course course : courses){
			getCourseDAO().update(course);
		}		
	}
	@Override
	public CourseTable generateCourseTableByStudent(Student student) {
		Set<Course> courses = student.getCourse();
		CourseTable courseTable = new CourseTable();
		for(Course course : courses){
			courseTable.addCourse(course);
		}
		return courseTable;
	}
	@Override
	public CourseTable generateCourseTableByTeacher(Teacher teacher) {
		List<Course> courses = getCourseDAO().findByTeacher(teacher);
		CourseTable courseTable = new CourseTable();
		for(Course course : courses){
			courseTable.addCourse(course);
		}
		return courseTable;
	}
	@Override
	public PageQuery<Course> scanCourseByStudent(Student student) {
		return getCourseDAO().scanCourseByStudent(null, student);
	}
	@Override
	public PageQuery<Course> scanCourseByStudent(PageQuery<Course> pq,
			Student student) {
		return getCourseDAO().scanCourseByStudent(pq, student);
	}
	@Override
	public PageQuery<Course> scanCourseByTeacherName(String teacherName) {
		return getCourseDAO().scanCourseByTeacherName(null, teacherName);
	}
	@Override
	public PageQuery<Course> scanCourseByTeacherName(PageQuery<Course> pq,
			String teacherName) {
		return getCourseDAO().scanCourseByTeacherName(pq, teacherName);
	}
}
