package com.course.selection.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.course.selection.dao.PageQuery;
import com.course.selection.dao.UserDAO;
import com.course.selection.domain.User;

/**
 * 对于User数据库访问层的实现类
 * @author Administrator
 *
 */
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	@Override
	public User get(Integer id) {
		return (User) this.getHibernateTemplate().get(User.class, id);
	}

	@Override
	public Integer save(User user) {
		Integer in = (Integer) this.getHibernateTemplate().save(user);
		return in;
	}

	@Override
	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}

	@Override
	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
	}

	@Override
	public List<User> findByNameAndPasswd(String name, String passwd) {
		Criteria criteria = this.getHibernateTemplate()
				 				.getSessionFactory()
				 				.getCurrentSession()
				 				.createCriteria(User.class);
		criteria.add(Restrictions.eq("Account", name))
				.add(Restrictions.eq("Password", passwd));
		@SuppressWarnings("unchecked")
		List<User> list = criteria.list();
		return list;
	}

	@Override
	public PageQuery<User> scanUserByPage(PageQuery<User> pq) {
		
		if(pq == null) pq = new PageQuery<>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Long total = (Long) session.createQuery("select count(u) from User u")
				  				   .uniqueResult();
		pq.setTotalNum(total);
		
		Criteria criteria = session.createCriteria(User.class);
		@SuppressWarnings("unchecked")
		List<User> list = criteria.setFirstResult(pq.getCurrentNum())
									.setMaxResults(pq.getPageContentNum()).list();
		pq.setDatas(list);
		return pq;
	}

	@Override
	public void delete(Integer id) {
		
		User user = (User) this.getHibernateTemplate().load(User.class, id);
		this.getHibernateTemplate().delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		return (List<User>)this.getHibernateTemplate().find("from User");
	}

}
