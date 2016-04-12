/**
 * 
 */
package com.course.selection.dao;

import com.course.selection.domain.Teacher;

import java.util.List;

public interface TeacherDAO {

	Teacher get(Integer id);

	Integer save(Teacher teacher);

	void save(List<Teacher> teachers);

	void updata(Teacher teacher);

	void delete(Teacher teacher);

	void delete(Integer id);

	List<Teacher> findAll();

	PageQuery<Teacher> scanTeacherByPage(PageQuery<Teacher> pq);

	Teacher findTeacherByAccount(String account);

	List<Teacher> findAllTeacherByName(String name);

	PageQuery<Teacher> scanTeacherByNameByPage(PageQuery<Teacher> pq, String name);

	PageQuery<Teacher> scanTeacherByDepartmentPage(PageQuery<Teacher> pq, String department);
}
