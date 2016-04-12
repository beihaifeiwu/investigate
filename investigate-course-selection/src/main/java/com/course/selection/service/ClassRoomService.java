package com.course.selection.service;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.ClassRoom;
import com.course.selection.domain.Course;

import java.util.List;

public interface ClassRoomService {
	
	/**
	 * 删除教室
	 * @param id
	 */
	boolean deleteClassRoom(Integer id);
	/**
	 * 批量删除教室
	 * @param ids
	 */
	boolean deleteClassRoom(List<Integer> ids);
	/**
	 * 添加新教室
	 * @param room
	 * @return
	 */
	Integer addNewClassRoom(ClassRoom room);
	/**
	 * 教室名是否存在
	 * @param name
	 * @return
	 */
	boolean isClassRoomNameExist(String name);
	/**
	 * 修改教室
	 * @param room
	 */
	void modifyClassRoom(ClassRoom room);
	
	/**
	 * 查看教室是否为空
	 * @param classRoom
	 * @return
	 */
	boolean isClassRoomEmpty(ClassRoom classRoom);
	/**
	 * 通过教室名获得教室
	 * @param name
	 * @return
	 */
	ClassRoom getClassRoomByName(String name);

	/**
	 * 通过标识属性查找教室
	 * @param id
	 * @return
	 */
	ClassRoom getClassRoomByID(Integer id);
	/**
	 * 查看指定教室的课程列表
	 * @param classroom
	 * @return
	 */
	List<Course> lookClassRoomCourses(ClassRoom classroom);
	
	/**
	 * 查看所有教室
	 * @return
	 */
	List<ClassRoom> lookClassRoom();
	/**
	 * 分页查看所有教室，第一页
	 */
	PageQuery<ClassRoom> lookClassRoomByPage();
	/**
	 * 分页查看所有教室，其它页
	 */
	PageQuery<ClassRoom> lookClassRoomByPage(PageQuery<ClassRoom> pq);
	/**
	 * 创建一个教室
	 * @param room
	 * @return
	 */
	Integer createClassRoom(ClassRoom room);
	/**
	 * 批量创建教室
	 * @param rooms
	 */
	void createClassRooms(List<ClassRoom> rooms);

	/**
	 * 获取room教室的空闲时间段
	 * @param room
	 * @return 由空闲时间段组成的字符串
	 */
	List<String> getEmptyPeriod(ClassRoom room);
	/**
	 * 获取所有教室的教室名
	 * @return
	 */
	List<String> getClassRoomNames();
}
