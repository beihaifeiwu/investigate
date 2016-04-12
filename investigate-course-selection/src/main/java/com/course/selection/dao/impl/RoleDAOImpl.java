/**
 * 
 */
package com.course.selection.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.course.selection.dao.RoleDAO;
import com.course.selection.domain.Role;

/**
 * @author Administrator
 *
 */
public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO {

	@Override
	public Role get(Serializable id) {
		return (Role) getHibernateTemplate().get(Role.class, id);
	}

	@Override
	public Integer save(Role role) {
		return (Integer) getHibernateTemplate().save(role);
	}

	@Override
	public void delete(Role role) {
		getHibernateTemplate().delete(role);
	}

	@Override
	public void modify(Role role) {
		getHibernateTemplate().update(role);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {
		return (List<Role>) getHibernateTemplate().find("from Role");
	}

	@Override
	public Role findByRoleName(String name) {
		String hql = "select r from Role r where r.RoleName = ?";
		Query query = this.getHibernateTemplate()
						  .getSessionFactory()
						  .getCurrentSession()
						  .createQuery(hql);
		query.setString(0, name);
		return (Role) query.uniqueResult();
	}

}
