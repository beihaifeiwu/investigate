/**
 * 
 */
package com.course.selection.service;

import java.util.List;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.Course;
import com.course.selection.domain.Student;
import com.course.selection.domain.Teacher;

/**
 * ��ʦҵ��ӿ�
 * @author JiHan
 *
 */
public interface TeacherService {

	/**
	 * �洢�µ�teacherʵ��
	 * @param teacher
	 * @return
	 */
	Integer addTeacher(Teacher teacher);
	/**
	 * ���ݱ�ʶ����ɾ����ʦʵ��
	 * @param ids
	 */
	void deleteTeachers(List<Integer> ids);
	
	/**
	 * ��ʦ�鿴�Լ����̵Ŀγ�
	 * @return �γ��б�
	 */
	List<Course> TeaCourses(Teacher teacher);
	
	/**
	 * ��ʦ�鿴������ָ���γ̵�ѧ��
	 * @param course ָ���Ŀγ�
	 * @return ѧ���б�
	 */
	List<Student> CouStudents(Course course);
	/**
	 * ��ҳ�鿴ѡָ���γ̵�ѧ���� ��һҳ
	 * @param course ָ���γ�
	 * @return ѧ���б�
	 */
	PageQuery<Student> lookStudentsByCourse(Course course);
	/**
	 * ��ҳ�鿴ѡָ���γ̵�ѧ��������ҳ
	 * @param course ָ���γ�
	 * @return ѧ���б�
	 */
	PageQuery<Student> lookStudentsByCourse(PageQuery<Student> pq, Course course);
	/**
	 * ��ҳ�鿴���н�ʦ����һҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Teacher> scanTeacherByPage();
	
	/**
	 * ��ҳ�鿴���н�ʦ������ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Teacher> scanTeacherByPage(PageQuery<Teacher> pq);
	/**
	 * ������ʦ��¼
	 * @param teacher
	 * @return
	 */
	Integer createTeacher(Teacher teacher);
	/**
	 * ����������ʦ��¼
	 * @param teacher
	 * @return
	 */
	void createTeachers(List<Teacher> teachers);
	/**
	 * ���ݽ�ְ���Ų��ҽ�ʦ
	 * @param account
	 * @return
	 */
	Teacher getTeacherByAccount(String account);
	/**
	 * ͨ����ʶ���Բ��ҽ�ʦ
	 * @param id
	 * @return
	 */
	Teacher getTeacherByID(Integer id);
	/**
	 * �޸Ľ�ʦʵ��
	 * @param teacher
	 */
	void modifyTeacher(Teacher teacher);
}
