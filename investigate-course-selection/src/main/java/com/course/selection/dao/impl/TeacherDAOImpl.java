/**
 * 
 */
package com.course.selection.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.course.selection.dao.PageQuery;
import com.course.selection.dao.TeacherDAO;
import com.course.selection.domain.Teacher;

/**
 * @author JiHan
 *
 */
public class TeacherDAOImpl extends HibernateDaoSupport implements TeacherDAO {

	/* （非 Javadoc）
	 * @see com.course.selection.dao.TeacherDAO#get(java.lang.Integer)
	 */
	@Override
	public Teacher get(Integer id) {
		return (Teacher) this.getHibernateTemplate().get(Teacher.class, id);
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.TeacherDAO#save(com.course.selection.domain.Teacher)
	 */
	@Override
	public Integer save(Teacher teacher) {
		return (Integer) this.getHibernateTemplate().save(teacher);
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.TeacherDAO#updata(com.course.selection.domain.Teacher)
	 */
	@Override
	public void updata(Teacher teacher) {
		this.getHibernateTemplate().update(teacher);

	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.TeacherDAO#delete(com.course.selection.domain.Teacher)
	 */
	@Override
	public void delete(Teacher teacher) {
		this.getHibernateTemplate().delete(teacher);

	}


	/* （非 Javadoc）
	 * @see com.course.selection.dao.TeacherDAO#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		this.getHibernateTemplate().delete(get(id));

	}
	

	/* （非 Javadoc）
	 * @see com.course.selection.dao.TeacherDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> findAll() {
		return (List<Teacher>) this.getHibernateTemplate().find("from Teacher");
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Teacher> scanTeacherByPage(PageQuery<Teacher> pq) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(t) from Teacher t").uniqueResult();
		pq.setTotalNum(total);
		String hql = "select t from Teacher t";
		Query query = session.createQuery(hql);
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@Override
	public Teacher findTeacherByAccount(String account) {
		String hql = "select t from Teacher t where t.Account = ?";
		Query query = this.getHibernateTemplate()
						  .getSessionFactory()
						  .getCurrentSession().createQuery(hql);
		query.setString(0, account);
		return (Teacher) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Teacher> scanTeacherByNameByPage(PageQuery<Teacher> pq,String name) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long)session.createQuery("select count(t) from Teacher t where t.profile.Name like ?")
				  .setString(0, name+"%")
				  .uniqueResult();
		pq.setTotalNum(total);
		
		String hql = "select t from Teacher t where t.profile.Name like ?";
		Query query = session.createQuery(hql);
		query.setString(0, name+"%");
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<Teacher> scanTeacherByDepartmentPage(PageQuery<Teacher> pq,String department) {
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(t) from Teacher t where t.profile.department = ?")
				  .setParameter(0, department)
				  .uniqueResult();
		pq.setTotalNum(total);
		
		String hql = "select t from Teacher t where t.profile.department = ?";
		Query query = session.createQuery(hql);
		query.setString(0, department);
		query.setFirstResult(pq.getCurrentNum());
		query.setMaxResults(pq.getPageContentNum());
		pq.setDatas(query.list());
		return pq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> findAllTeacherByName(String name) {
		String hql = "select t from Teacher t where t.profile.Name like ?";
		Query query = this.getHibernateTemplate()
				          .getSessionFactory()
				          .getCurrentSession().createQuery(hql);
		query.setString(0, name+"%");
		return query.list();
	}

	@Override
	public void save(List<Teacher> teachers) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		for(int i = 0; i < teachers.size(); i++){
			session.persist(teachers.get(i));
			if(i % 20 == 0){
				session.flush();
				session.clear();
			}
		}
	}

}
