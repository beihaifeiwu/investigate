/**
 * 
 */
package com.course.selection.dao;

import java.util.List;

import com.course.selection.domain.ClassRoom;

/**
 * @author JiHan
 *
 */
public interface ClassRoomDAO {
	/**
	 * 根据标识属性加载ClassRoom实例
	 * @param id:需要加载的ClassRoom实例的标识属性值
	 * @return 指定标识属性对应的ClassRoom实例
	 */
	ClassRoom get(Integer id);
	
	/**
	 * 保存指定的ClassRoom实例
	 * @param 需要被保存的ClassRoom实例
	 * @return 被保存的ClassRoom实例的标识属性值
	 */
	Integer save(ClassRoom classRoom);
	/**
	 * 批量保存ClassRoom实例
	 * @param rooms
	 */
	void save(List<ClassRoom> rooms);
	/**
	 * 修改指定的ClassRoom实例
	 * @param 需要被修改的ClassRoom实例
	 */
	void updata(ClassRoom classRoom);
	/**
	 * 删除指定的ClassRoom实例
	 * @param ClassRoom 需要被删除的ClassRoom实例
	 */
	void delete(ClassRoom classRoom);
	/**
	 * 根据标识属性来删除ClassRoom实例
	 * @param id 需要被删除的ClassRoom实例的标识属性值
	 */
	void delete(Integer id);
	/**
	 * 根据标识属性来查找ClassRoom实例
	 * @param name 需要被查找的ClassRoom实例的标识属性值
	 * @return 指定标识属性对应的ClassRoom实例
	 */
	ClassRoom findbyName(String name);
	/**
	 * 查询全部的ClassRoom实例
	 * @return 数据库中所有ClassRoom实例
	 */
	List<ClassRoom> findAll();
	/**
	 * 查询所有教室的名字
	 * @return
	 */
	List<String> findAllName();
	/**
	 * 分页查询所有教室
	 * @param pq
	 * @return
	 */
	PageQuery<ClassRoom> scanClassRoomByPage(PageQuery<ClassRoom> pq);

}
