/**
 * 
 */
package com.course.selection.service;

import java.util.List;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.Course;
import com.course.selection.domain.Student;

/**
 * ѧ��ҵ��ӿ�
 * @author JiHan
 *
 */
public interface StudentService {
	/**
	 * �޸�ѧ��ʵ����Ϣ
	 * @param student
	 */
	void modifyStudent(Student student);
	/**
	 * ����ɾ��ѧ��ʵ��
	 * @param list
	 */
	void deleteStudent(List<Integer> list);
	/**
	 * ����һ��ѧ��ʵ��
	 * @param student
	 * @return
	 */
	Integer createStudent(Student student);
	/**
	 * ��������ѧ��ʵ��
	 * @param student
	 */
	void createStudent(List<Student> students);
	/**
	 * ͨ��ѧ�Ų���ѧ��
	 * @param account
	 * @return
	 */
	Student getStudentByAccount(String account);
	/**
	 * ͨ����ʶ���Բ���ѧ��
	 * @param id
	 * @return
	 */
	Student getStudentByID(Integer id);
	/**
	 * ��ҳ���ѧ������һҳ
	 * @return
	 */
	PageQuery<Student> scanStudent();
	/**
	 * ��ҳ���ѧ��������ҳ 
	 * @param pq
	 * @return
	 */
	PageQuery<Student> scanStudent(PageQuery<Student> pq);
	
	/**
	 * ��ҳ����γ�course��ѧ������һҳ
	 * @return
	 */
	PageQuery<Student> scanStudentByCourse(Course course);
	/**
	 * ��ҳ����γ�course��ѧ��������ҳ 
	 * @param pq
	 * @return
	 */
	PageQuery<Student> scanStudentByCourse(PageQuery<Student> pq, Course course);
	
	/**
	 * ��ҳ�鿴���еĿγ�,����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> lookCourseAll(PageQuery<Course> pq);
	/**
	 * ��ҳ�鿴���еĿγ�,��һҳ
	 * @return
	 */
	PageQuery<Course> lookCourseAll();
	
	/**
	 * ͨ���γ������ҿγ�
	 * @param coursename
	 * @return
	 */
	List<Course> searchCourseByCourseName(String coursename);
	
	/**
	 * ͨ���γ̺Ų�ѯ�γ�
	 * @param coursenumber
	 * @return
	 */
	List<Course> searchCourseByCourseNmber(String coursenumber);
	
	/**
	 * ͨ����ʦ������ѯ�γ�
	 * @param teachername
	 * @return
	 */
	List<Course> searchCourseByTeacherName(String teachername);
	
	/**
	 * ѡ��γ�
	 * @param course
	 * @param student
	 * @return ѡ�γɹ�����true��ѡ��ʧ�ܷ���false
	 */
	boolean addCourse(Course course, Student student);
	

	
	/**
	 * ɾ����ѡ�γ�
	 * @param course
	 */
	void rmCourse(Course course, Student student);
}
