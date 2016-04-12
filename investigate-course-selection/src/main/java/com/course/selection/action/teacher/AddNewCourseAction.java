package com.course.selection.action.teacher;

import com.course.selection.domain.ClassRoom;
import com.course.selection.domain.Course;
import com.course.selection.domain.Teacher;
import com.course.selection.service.ClassRoomService;
import com.course.selection.service.CourseService;
import com.course.selection.service.TeacherService;
import com.course.selection.util.TimePointBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddNewCourseAction extends ActionSupport {

	private static final long serialVersionUID = 4515896767698150337L;

	private String courseNumber;
	
	private String classNumber;
	
	private String courseName;
	
	private double credit;
	
	private String teacherAccount;
	
	private String attribute;
	
	private int capacity;
	
	private String roomName;
	
	private String timePeriod;
	
	private String messageKey;
	
	private ClassRoomService classRoomService;
	
	private CourseService courseService;
	
	private TeacherService teacherService;
	
	public String prepare() throws Exception {
		
		Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
		
		Teacher teacher = getTeacherService().getTeacherByID(id);
		
		setTeacherAccount(teacher.getAccount());
		
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {

		if(getCourseService().isExist(courseNumber, classNumber)){
			setMessageKey("admin.course.add.exist");
		}else{
			Course course = new Course();
			
			course.setCourseName(courseName);
			course.setCourseNumber(courseNumber);
			course.setClassNumber(classNumber);
			course.setCredit(credit);
			course.setAttribute(attribute);
			course.setCapacity(capacity);
			
			if(roomName != null && !roomName.equals("")){
				ClassRoom classRoom = getClassRoomService().getClassRoomByName(roomName);
				course.setClassroom(classRoom);
			}
			
			if(timePeriod != null && !timePeriod.equals("")){
				TimePointBuilder tpb = new TimePointBuilder();
				String[] ky = timePeriod.split("-");
				for(String str : ky){
					String[] p = str.split(",");
					if(p.length == 2){
						tpb.addPeriod(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
					}
				}
				tpb.addWeekRange(new Integer[]{1,16});
				course.setTimepoint(tpb.parseToTimePoint());
			}
			
			if(!teacherAccount.trim().equals("")){
				Integer id = (Integer) ActionContext.getContext().getSession().get("userID");
				Teacher teacher = getTeacherService().getTeacherByID(id);
				course.setTeacher(teacher);
			}
			
			getCourseService().createCourse(course);
			setMessageKey("admin.course.add.success");
		}
		return SUCCESS;
	}

	/**
	 * @return the courseNumber
	 */
	public String getCourseNumber() {
		return courseNumber;
	}

	/**
	 * @param courseNumber the courseNumber to set
	 */
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	/**
	 * @return the classNumber
	 */
	public String getClassNumber() {
		return classNumber;
	}

	/**
	 * @param classNumber the classNumber to set
	 */
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the credit
	 */
	public double getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * @return the teacherAccount
	 */
	public String getTeacherAccount() {
		return teacherAccount;
	}

	/**
	 * @param teacherAccount the teacherAccount to set
	 */
	public void setTeacherAccount(String teacherAccount) {
		this.teacherAccount = teacherAccount;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public ClassRoomService getClassRoomService() {
		return classRoomService;
	}

	public void setClassRoomService(ClassRoomService classRoomService) {
		this.classRoomService = classRoomService;
	}
}
