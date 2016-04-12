/**
 * 
 */
package com.course.selection.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.course.selection.dao.PageQuery;
import com.course.selection.dao.StudentDAO;
import com.course.selection.domain.Course;
import com.course.selection.domain.Student;

/**
 * @author JiHan
 *
 */
public class StudentDAOImpl extends HibernateDaoSupport implements StudentDAO {

	/* （非 Javadoc）
	 * @see com.course.selection.dao.StudentDAO#get(java.lang.Integer)
	 */
	@Override
	public Student get(Integer id) {
		return (Student) this.getHibernateTemplate().get(Student.class, id);
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.StudentDAO#save(com.course.selection.domain.Student)
	 */
	@Override
	public Integer save(Student student) {
		return (Integer) this.getHibernateTemplate().save(student);
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.StudentDAO#updata(com.course.selection.domain.Student)
	 */
	@Override
	public void update(Student student) {
		this.getHibernateTemplate().update(student);
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.StudentDAO#delete(com.course.selection.domain.Student)
	 */
	@Override
	public void delete(Student student) {
		this.getHibernateTemplate().delete(student);

	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.StudentDAO#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		Student s= (Student) this.getHibernateTemplate().load(Student.class, id);
		this.getHibernateTemplate().delete(s);
		

	}


	/* （非 Javadoc）
	 * @see com.course.selection.dao.StudentDAO#find(com.course.selection.domain.Course)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findByCourse(Course course) {
		String hql = "select s from Student s where ? in elements(s.course)";
		Query  q = this.getHibernateTemplate()
					   .getSessionFactory()
					   .getCurrentSession().createQuery(hql);
		q.setParameter(0, course);
		return q.list();
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.StudentDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findAll() {
		return (List<Student>) this.getHibernateTemplate().find("from Student");
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Student> scanStudentByPage(PageQuery<Student> pq) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(s) from Student s")
							       .uniqueResult();
		pq.setTotalNum(total);
		
		Criteria criteria = session.createCriteria(Student.class);
		criteria.setFirstResult(pq.getCurrentNum());
		criteria.setMaxResults(pq.getPageContentNum());
		pq.setDatas(criteria.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Student> scanCourseStudentByPage(PageQuery<Student> pq,Course course) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(s) from Student s where ? in elements(s.course)")
				  .setParameter(0, course)
				  .uniqueResult();
		pq.setTotalNum(total);
		
		String hql = "select s from Student s where ? in elements(s.course)";
		Query query = session.createQuery(hql);
		query.setParameter(0, course);
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@Override
	public Student findStudentByAccount(String Account) {
		String hql = "select s from Student s where s.Account = ?";
		Query query = this.getHibernateTemplate()
						  .getSessionFactory()
						  .getCurrentSession().createQuery(hql);
		query.setParameter(0, Account);
		return (Student) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Student> scanStudentByNameByPage(PageQuery<Student> pq,
			String name) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(s) from Student s where s.profile.Name like ?")
				  .setString(0, name+"%")
				  .uniqueResult();
		pq.setTotalNum(total);
		
		String hql = "select s from Student s where s.profile.Name like ?";
		Query query = session.createQuery(hql);
		query.setString(0, name+"%");
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Student> scanStudentByClassByPage(PageQuery<Student> pq,
			String clazz) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(s) from Student s where s.profile.Clazz = ?")
				  .setParameter(0, clazz)
				  .uniqueResult();
		pq.setTotalNum(total);
		
		String hql = "select s from Student s where s.profile.Clazz = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, clazz);
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Student> scanStudentByDepartmentByPage(
			PageQuery<Student> pq, String department) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(s) from Student s where s.profile.department = ?")
				  .setParameter(0, department)
				  .uniqueResult();
		pq.setTotalNum(total);
		
		String hql = "select s from Student s where s.profile.department = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, department);
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@Override
	public void save(List<Student> students) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		for(int i = 0; i < students.size(); i++){
			session.persist(students.get(i));
			if(i % 20 == 0){
				session.flush();
				session.clear();
			}
		}

		
	}

}
