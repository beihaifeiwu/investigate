package com.course.selection.service;

import java.util.List;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.ClassRoom;
import com.course.selection.domain.Course;
import com.course.selection.domain.Student;
import com.course.selection.domain.Teacher;
import com.course.selection.util.CourseTable;

public interface CourseService {
	
	/**
	 * �����γ�
	 * @param course
	 * @return
	 */
	Integer createCourse(Course course);
	/**
	 * �����γ�
	 * @param course
	 * @return
	 */
	void createCourse(List<Course> courses);
	/**
	 * �޸Ŀγ�
	 * @param course
	 */
	void modifyCourse(Course course);

	/**
	 * ��ҳ��ѯ���пγ� ��һҳ
	 * @return
	 */
	PageQuery<Course> scanCourse();

	/**
	 * ��ҳ��ѯ���пγ� ����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanCourse(PageQuery<Course> pq);

	/**
	 * ���γ̺ŷ�ҳ��ѯ���пγ� ��һҳ
	 * @return
	 */
	PageQuery<Course> scanCourseByCourseNumber(String courseNumber);

	/**
	 * ���γ̺ŷ�ҳ��ѯ���пγ� ����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanCourseByCourseNumber(PageQuery<Course> pq, String courseNumber);

	/**
	 * ���γ�����ҳ��ѯ���пγ� ��һҳ
	 * @return
	 */
	PageQuery<Course> scanCourseByCourseName(String courseName);

	/**
	 * ��������ʦ��ҳ��ѯ���пγ� ����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanCourseByCourseName(PageQuery<Course> pq, String courseName);
	
	/**
	 * ��������ʦ��ҳ��ѯ���пγ� ��һҳ
	 * @return
	 */
	PageQuery<Course> scanCourseByTeacher(Teacher teacher);

	/**
	 *  ��������ʦ��ҳ��ѯ���пγ� ����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanCourseByTeacher(PageQuery<Course> pq, Teacher teacher);
	
	/**
	 * ��������ʦ��ҳ��ѯ���пγ� ��һҳ
	 * @return
	 */
	PageQuery<Course> scanCourseByTeacherName(String teacherName);

	/**
	 *  ��������ʦ��ҳ��ѯ���пγ� ����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanCourseByTeacherName(PageQuery<Course> pq, String teacherName);
	/**
	 * ��ѡ��ѧ����ҳ��ѯ���пγ� ��һҳ
	 * @return
	 */
	PageQuery<Course> scanCourseByStudent(Student student);

	/**
	 * ��ѡ��ѧ����ҳ��ѯ���пγ� ����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanCourseByStudent(PageQuery<Course> pq, Student student);
	/**
	 * ��ҳ��ѯ����δɾ���Ŀγ̣���һҳ
	 * @return
	 */
	PageQuery<Course> scanNotDeletedCourse();
	
	/**
	 * ��ҳ��ѯ����δɾ���Ŀγ̣�����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanNotDeletedCourse(PageQuery<Course> pq);
	/**
	 * ��ҳ��ѯ������ɾ���Ŀγ̣���һҳ
	 * @return
	 */
	PageQuery<Course> scanDeletedCourse();
	
	/**
	 * ��ҳ��ѯ������ɾ���Ŀγ̣�����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanDeletedCourse(PageQuery<Course> pq);

	/**
	 * ��ҳ��ѯ���п�ѡ�ɿγ̣���һҳ
	 * @return
	 */
	PageQuery<Course> scanPickableCourse();

	/**
	 * ��ҳ��ѯ���п�ѡ�Ŀγ̣�����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanPickableCourse(PageQuery<Course> pq);
	
	/**
	 * ��ҳ��ѯ�����Ѵ�����δ׼���õĿγ̣���һҳ
	 * @return
	 */
	PageQuery<Course> scanUnReadyCourse();
	
	/**
	 * ��ҳ��ѯ�����Ѵ�����δ׼���õĿγ̣�����ҳ
	 * @param pq
	 * @return
	 */
	PageQuery<Course> scanUnReadyCourse(PageQuery<Course> pq);
	
	/**
	 * ���ɽ��ҵĿα�
	 * @param classRoom
	 * @return
	 */
	CourseTable generateCourseTableByClassRoom(ClassRoom classRoom);
	
	/**
	 * ����ѧ���Ŀα�
	 * @param student
	 * @return
	 */
	CourseTable generateCourseTableByStudent(Student student);

	/**
	 * ���ɽ�ʦ�α�
	 * @param teacher
	 * @return
	 */
	CourseTable generateCourseTableByTeacher(Teacher teacher);
	/**
	 * ɾ���γ̣����γ�״̬��עΪDELETED
	 */
	void deleteCourse(Integer id);
	void deleteCourse(List<Integer> list);
	/**
	 * ɾ������״̬ΪDELETED�Ŀγ�
	 */
	void removeAllDeletedCourse();
	
	/**
	 * ͨ����ʶ���Ի��Courseʵ��
	 */
	Course getCourseByID(Integer id);
	/**
	 * �жϿγ��Ƿ����
	 * @param courseNumber
	 * @param classNumber
	 * @return
	 */
	boolean isExist(String courseNumber, String classNumber);
	/**
	 * ����ѡ��
	 */
	void openSelectCourse();
	/**
	 * ����ѡ��
	 */
	void closeSelectCourse();
}
