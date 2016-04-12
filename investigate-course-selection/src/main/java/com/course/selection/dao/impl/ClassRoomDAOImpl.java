/**
 * 
 */
package com.course.selection.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.course.selection.dao.ClassRoomDAO;
import com.course.selection.dao.PageQuery;
import com.course.selection.domain.ClassRoom;

/**
 * @author JiHan
 *
 */
public class ClassRoomDAOImpl extends HibernateDaoSupport implements
		ClassRoomDAO {

	/* （非 Javadoc）
	 * @see com.course.selection.dao.ClassRoomDAO#get(java.lang.Integer)
	 */
	@Override
	public ClassRoom get(Integer id) {
		return (ClassRoom) this.getHibernateTemplate().get(ClassRoom.class, id);
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.ClassRoomDAO#save(com.course.selection.domain.ClassRoom)
	 */
	@Override
	public Integer save(ClassRoom classRoom) {
		return (Integer) this.getHibernateTemplate().save(classRoom);
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.ClassRoomDAO#updata(com.course.selection.domain.ClassRoom)
	 */
	@Override
	public void updata(ClassRoom classRoom) {
		this.getHibernateTemplate().update(classRoom);
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.ClassRoomDAO#delete(com.course.selection.domain.ClassRoom)
	 */
	@Override
	public void delete(ClassRoom classRoom) {
		this.getHibernateTemplate().delete(classRoom);

	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.ClassRoomDAO#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		this.getHibernateTemplate().delete(get(id));

	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.ClassRoomDAO#findbyName(java.lang.String)
	 */
	@Override
	public ClassRoom findbyName(String name) {
		String hql = "select c from ClassRoom c where c.RoomName = ?";
		Query query = this.getHibernateTemplate().getSessionFactory()
						  .getCurrentSession().createQuery(hql);
		query.setString(0, name);
		return (ClassRoom) query.uniqueResult();
	}

	/* （非 Javadoc）
	 * @see com.course.selection.dao.ClassRoomDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ClassRoom> findAll() {
		return (List<ClassRoom>) this.getHibernateTemplate().find("from ClassRoom");
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageQuery<ClassRoom> scanClassRoomByPage(PageQuery<ClassRoom> pq) {
		if(pq == null) pq = new PageQuery<>();
		Long total = (Long) this.getHibernateTemplate()
								.getSessionFactory()
								.getCurrentSession()
								.createQuery("select count(cr) from ClassRoom cr")
								.uniqueResult();
		pq.setTotalNum(total);
		Criteria criteria = this.getHibernateTemplate()
								.getSessionFactory()
								.getCurrentSession().createCriteria(ClassRoom.class);
		criteria.setFirstResult(pq.getCurrentNum());
		criteria.setMaxResults(pq.getPageContentNum());
		pq.setDatas(criteria.list());
		return pq;
	}

	@Override
	public void save(List<ClassRoom> rooms) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		for(int i = 0; i < rooms.size(); i++){
			session.persist(rooms.get(i));
			if(i % 20 == 0){
				session.flush();
				session.clear();
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllName() {
		String hql = "select c.RoomName from ClassRoom c";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return query.list();
	}

}
