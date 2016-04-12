package com.course.selection.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.course.selection.dao.CourseDAO;
import com.course.selection.dao.PageQuery;
import com.course.selection.domain.ClassRoom;
import com.course.selection.domain.Course;
import com.course.selection.domain.CourseStateType;
import com.course.selection.domain.Student;
import com.course.selection.domain.Teacher;

public class CourseDAOImpl extends HibernateDaoSupport implements CourseDAO {

	@Override
	public Integer save(Course course) {
		Integer id = (Integer) this.getHibernateTemplate().save(course);
		return id;
	}

	@Override
	public Course get(Integer id) {
		return (Course) this.getHibernateTemplate().get(Course.class, id);
	}

	@Override
	public void update(Course course) {
		this.getHibernateTemplate().update(course);
	}

	@Override
	public void delete(Course course) {
		if(course != null){
			course.getCourseState().setStateType(CourseStateType.DELETED);
			this.getHibernateTemplate().update(course);
		}
	}

	@Override
	public void delete(Integer id) {
		Course course = (Course) this.getHibernateTemplate().load(Course.class, id);
		course.getCourseState().setStateType(CourseStateType.DELETED);
		this.getHibernateTemplate().update(course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Course> scanCourseByPage(PageQuery<Course> pq) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(c) from Course c").uniqueResult();
		pq.setTotalNum(total);
		Criteria criteria = session.createCriteria(Course.class);
		criteria.setFirstResult(pq.getCurrentNum());
		criteria.setMaxResults(pq.getPageContentNum());
		pq.setDatas(criteria.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Course> scanTeacherCourseByPage(PageQuery<Course> pq,
			Teacher teacher) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(c) from Course c where c.teacher = ?")
									  .setParameter(0, teacher)
									  .uniqueResult();
		pq.setTotalNum(total);
		Criteria criteria = session.createCriteria(Course.class);
		
		criteria.add(Restrictions.eq("teacher", teacher));
		
		criteria.setFirstResult(pq.getCurrentNum());
		criteria.setMaxResults(pq.getPageContentNum());
		pq.setDatas(criteria.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Course> scanClassRoomCourseByPage(PageQuery<Course> pq,
			ClassRoom room) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(c) from Course c where c.classroom = ? and  c.courseState.stateType != ?")
				  .setParameter(0, room)
				  .setParameter(1, CourseStateType.DELETED)
				  .uniqueResult();
		pq.setTotalNum(total);
		
		String hql = "select c from Course c where c.classroom = ? and c.courseState.stateType != ?";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, room).setParameter(1, CourseStateType.DELETED);
		pq.setDatas(query.list());
		
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Course> scanCourseByCourseNum(PageQuery<Course> pq,
			String courseNumber) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(c) from Course c where c.CourseNumber = ?")
				  .setParameter(0, courseNumber)
				  .uniqueResult();
		pq.setTotalNum(total);
		Criteria criteria = session.createCriteria(Course.class);
		
		criteria.add(Restrictions.eq("CourseNumber", courseNumber));
		
		criteria.setFirstResult(pq.getCurrentNum());
		criteria.setMaxResults(pq.getPageContentNum());
		pq.setDatas(criteria.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Course> scanCourseByCourseName(PageQuery<Course> pq,
			String courseName) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(c) from Course c where c.CourseName like ?")
				  .setParameter(0, '%' + courseName + '%')
				  .uniqueResult();
		pq.setTotalNum(total);
		String hql = "select c from Course c where c.CourseName like ?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, '%' + courseName + '%');
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Course> scanCourseByType(PageQuery<Course> pq,
			CourseStateType type) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(c) from Course c where c.courseState.stateType = ?")
				  .setParameter(0, type)
				  .uniqueResult();
		pq.setTotalNum(total);
		String hql = "select c from Course c where c.courseState.stateType = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAll() {
		return (List<Course>) this.getHibernateTemplate().find("from Course");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByType(CourseStateType type) {
		String hql = "select c from Course c where c.courseState.stateType = ?";
		Query  q = this.getHibernateTemplate()
					   .getSessionFactory()
					   .getCurrentSession()
					   .createQuery(hql);
		q.setParameter(0, type);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByTeacher(Teacher teacher) {
		String hql = "select c from Course c where c.teacher = ?";
		Query  q = this.getHibernateTemplate()
				   .getSessionFactory()
				   .getCurrentSession().createQuery(hql);
		q.setParameter(0, teacher);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByClassRoom(ClassRoom room) {
		String hql = "select c from Course c where c.classroom = ? and c.courseState.stateType != ?";
		Query  q = this.getHibernateTemplate()
				   .getSessionFactory()
				   .getCurrentSession().createQuery(hql);
		q.setParameter(0, room);
		q.setParameter(1, CourseStateType.DELETED);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByCourseNumber(String coursenumber) {
		String hql="select c from Course c where c.CourseNumber=?";
		Query  q = this.getHibernateTemplate()
				   .getSessionFactory()
				   .getCurrentSession().createQuery(hql);
		q.setParameter(0, coursenumber);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByCourseName(String coursename) {
		String hql = "select c from Course as c where c.CourseName = ?";
		Query  q = this.getHibernateTemplate()
				   .getSessionFactory()
				   .getCurrentSession().createQuery(hql);
		q.setParameter(0, coursename);
		return q.list();
	}

	@Override
	public void save(List<Course> course) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		for(int i = 0; i < course.size(); i++){
			session.save(course.get(i));
			if(i % 20 == 0){
				session.flush();
				session.clear();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Course> scanCourseByNotThatType(PageQuery<Course> pq,
			CourseStateType type) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(c) from Course c where c.courseState.stateType != ?")
				  .setParameter(0, type)
				  .uniqueResult();
		pq.setTotalNum(total);
		String hql = "select c from Course c where c.courseState.stateType != ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@Override
	public Course findByCourseNumberAndClassNumber(String courseNumber,
			String classNumber) {
		String hql = "select c from Course c where c.CourseNumber = ? and c.ClassNumber = ?";
		Course course = (Course) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
							.createQuery(hql).setParameter(0, courseNumber)
							.setParameter(1, classNumber).uniqueResult();
		return course;
	}

	@Override
	public void removeFromDatabase(Integer id) {
		
		Course course = (Course) this.getHibernateTemplate().load(Course.class, id);
		
		this.getHibernateTemplate().delete(course);
		
	}

	@Override
	public void removeFromDatabase(Course course) {
		
		this.getHibernateTemplate().delete(course);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Course> scanCourseByStudent(PageQuery<Course> pq,
			Student student) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(c) from Student s inner join s.course c where s = ?")
				  .setParameter(0, student)
				  .uniqueResult();
		pq.setTotalNum(total);
		String hql = "select c from Student s inner join s.course c where s = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, student);
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Course> scanCourseByTeacherName(PageQuery<Course> pq,
			String teacherName) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(c) from Course c where c.teacher.profile.Name like ?")
				  .setParameter(0, '%' + teacherName + '%')
				  .uniqueResult();
		pq.setTotalNum(total);
		String hql = "select c from Course c where c.teacher.profile.Name like ?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, '%' + teacherName + '%');
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

}
