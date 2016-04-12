/**
 * 
 */
package com.course.selection.dao;

import com.course.selection.domain.Course;
import com.course.selection.domain.Student;

import java.util.List;

public interface StudentDAO {

	Student get(Integer id);

	Integer save(Student student);

	void save(List<Student> students);

	void update(Student student);

	void delete(Student student);

	void delete(Integer id);

	List<Student> findByCourse(Course course);

	List<Student> findAll();

	PageQuery<Student> scanStudentByPage(PageQuery<Student> pq);

	PageQuery<Student> scanCourseStudentByPage(PageQuery<Student> pq, Course course);

	Student findStudentByAccount(String Account);

	PageQuery<Student> scanStudentByNameByPage(PageQuery<Student> pq, String name);

	PageQuery<Student> scanStudentByClassByPage(PageQuery<Student> pq, String clazz);

	PageQuery<Student> scanStudentByDepartmentByPage(PageQuery<Student> pq, String department);
}
