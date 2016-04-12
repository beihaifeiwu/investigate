package com.course.selection.action;

import com.course.selection.domain.Course;
import com.course.selection.domain.CourseState;
import com.course.selection.domain.CourseStateType;
import com.course.selection.exception.CourseIDIllegalException;
import com.course.selection.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyCourseAction extends ActionSupport {

	private static final long serialVersionUID = 4515896767698150337L;
	
	private Integer courseID;

	private String courseNumber;
	
	private String classNumber;
	
	private String courseName;
	
	private double credit;
	
	private String teacherAccount;
	
	private String attribute;
	
	private int capacity;
	
	private String messageKey;
	
	private Course course;
	
	private CourseService courseService;
	
	@Override
	public String execute() throws Exception {
		
		if(getCourseID() <= 0 || getCourseService().getCourseByID(getCourseID()) == null){
			setMessageKey("admin.course.modify.error");
			throw new CourseIDIllegalException();
		}

		course = getCourseService().getCourseByID(getCourseID());
		
		if((!course.getCourseNumber().equals(courseNumber) ||
				!course.getClassNumber().equals(classNumber) )&&
				getCourseService().isExist(courseNumber, classNumber)){
			setMessageKey("admin.course.modify.exist");
		}else{
			//判断课程状态
			boolean isDeleted = false;
			CourseState state = course.getCourseState();
			switch(state.getStateType()){
			case CREATE:
				state.setStateType(CourseStateType.MODIFIED);
				break;
			case DELETED:
				isDeleted = true;
				break;
			case FULL:
				if(course.getCapacity() < capacity){
					state.setStateType(CourseStateType.PICKABLE);
				}
				break;
			case INDATABASE:
				state.setStateType(CourseStateType.MODIFIED);
				break;
			case MODIFIED:
				state.setStateType(CourseStateType.MODIFIED);
				break;
			case PICKABLE:
				if(state.getStudentNumber() <= capacity){
					state.setStateType(CourseStateType.FULL);
				}
				break;
			}
			if(!isDeleted){
				course.setCourseName(courseName);
				course.setCourseNumber(courseNumber);
				course.setClassNumber(classNumber);
				course.setCredit(credit);
				course.setAttribute(attribute);
				course.setCapacity(capacity);
				
				getCourseService().modifyCourse(course);
				setMessageKey("admin.course.modify.success");
			}else{
				setMessageKey("admin.course.modify.limit");
			}
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

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
