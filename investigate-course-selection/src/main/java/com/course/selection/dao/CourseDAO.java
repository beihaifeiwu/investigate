package com.course.selection.dao;

import com.course.selection.domain.*;

import java.util.List;

public interface CourseDAO {

	Integer save(Course course);

	void save(List<Course> course);

	Course get(Integer id);

	void update(Course course);

	void delete(Course course);

	void delete(Integer id);

	void removeFromDatabase(Integer id);

	void removeFromDatabase(Course course);

	List<Course> findAll();

	PageQuery<Course> scanCourseByPage(PageQuery<Course> pg);

	List<Course> findByType(CourseStateType type);

	PageQuery<Course> scanCourseByType(PageQuery<Course> pg, CourseStateType type);

	PageQuery<Course> scanCourseByNotThatType(PageQuery<Course> pg, CourseStateType type);

	List<Course> findByTeacher(Teacher teacher);
	
	PageQuery<Course> scanTeacherCourseByPage(PageQuery<Course> pg, Teacher teacher);

	PageQuery<Course> scanCourseByTeacherName(PageQuery<Course> pq, String teacherName);

	List<Course> findByClassRoom(ClassRoom room);

	PageQuery<Course> scanClassRoomCourseByPage(PageQuery<Course> pg, ClassRoom room);
	
	List<Course> findByCourseNumber(String coursenumber);

	Course findByCourseNumberAndClassNumber(String courseNumber, String classNumber);
	
	PageQuery<Course> scanCourseByCourseNum(PageQuery<Course> pg, String courseNumber);

	List<Course> findByCourseName(String coursename);
	
	PageQuery<Course> scanCourseByCourseName(PageQuery<Course> pg, String courseName);

	PageQuery<Course> scanCourseByStudent(PageQuery<Course> pq, Student student);
	
}
