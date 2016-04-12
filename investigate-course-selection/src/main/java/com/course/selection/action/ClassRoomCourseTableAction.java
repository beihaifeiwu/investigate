package com.course.selection.action;

import com.course.selection.domain.ClassRoom;
import com.course.selection.service.ClassRoomService;
import com.course.selection.service.CourseService;
import com.course.selection.util.CourseTable;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 为教室生成课表的Action
 * @author Administrator
 *
 */
public class ClassRoomCourseTableAction extends ActionSupport {

	private static final long serialVersionUID = -3392063564511905402L;

	private CourseTable courseTable;
	
	private int roomID;
	
	private ClassRoom classRoom;
	
	private CourseService courseService;
	
	private ClassRoomService classRoomService;
	
	private String errorKey;

	/**
	 * @return the courseTable
	 */
	public CourseTable getCourseTable() {
		return courseTable;
	}

	/**
	 * @param courseTable the courseTable to set
	 */
	public void setCourseTable(CourseTable courseTable) {
		this.courseTable = courseTable;
	}

	/**
	 * @return the roomID
	 */
	public int getRoomID() {
		return roomID;
	}

	/**
	 * @param roomID the roomID to set
	 */
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	/**
	 * @return the courseService
	 */
	public CourseService getCourseService() {
		return courseService;
	}

	/**
	 * @param courseService the courseService to set
	 */
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	@Override
	public String execute() throws Exception {
		
		classRoom = getClassRoomService().getClassRoomByID(getRoomID());
		
		if(classRoom == null){
			setErrorKey("admin.classroom.coursetable.null");
			return ERROR;
		}
		CourseTable courseTable = getCourseService().generateCourseTableByClassRoom(classRoom);
		this.setCourseTable(courseTable);
		
		if(courseTable == null){
			setErrorKey("admin.classroom.coursetable.error");
			return ERROR;			
		}
		return SUCCESS;
	}

	public ClassRoomService getClassRoomService() {
		return classRoomService;
	}

	public void setClassRoomService(ClassRoomService classRoomService) {
		this.classRoomService = classRoomService;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}
}
