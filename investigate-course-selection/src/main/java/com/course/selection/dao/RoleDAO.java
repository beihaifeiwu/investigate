package com.course.selection.dao;

import com.course.selection.domain.Role;

import java.io.Serializable;
import java.util.List;

/**
 * 角色对于数据库的访问层接口
 * @author Administrator
 *
 */
public interface RoleDAO {
	/**
	 * 通过标识属性查找Role实例
	 * @param id
	 * @return
	 */
	Role get(Serializable id);
	
	/**
	 * 存储Role的实例到数据库
	 * @param role
	 * @return
	 */
	Integer save(Role role);

	/**
	 * 将Role实例从数据库中删除
	 * @param role
	 */
	void delete(Role role);
	
	/**
	 * 修改数据库中的Role实例
	 * @param role
	 */
	void modify(Role role);
	
	/**
	 * 查询所有角色对象
	 * @return
	 */
	List<Role> findAll();
	/**
	 * 通过角色名称查找角色对象
	 * @param name
	 * @return
	 */
	Role findByRoleName(String name);
}
