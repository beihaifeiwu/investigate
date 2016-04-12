/**
 * 
 */
package com.course.selection.domain;

import java.io.Serializable;

/**
 * @author JiHan
 *
 */
public class Course implements Serializable {
	private static final long serialVersionUID = -9149654796329357137L;
	/**
	 * 　　Id：主属性
　　		courseName：课程名
　　		courseNumber：课程号，和课程名是一一对应关系
　　		classNumber：课序号，课程号相同的课程以课序号区分
		attribute: 课程属性，必修或选修
		department：课程院系
　　		Capacity：课容量
　　		Credit：该课程的学分
　　		classRoom：classRoom类，course类与classRoom类是多对一的依赖关系，一个course			  
 				       拥有一个classRoom，一个classroom拥有多个course
　　		Timepoint：timepoint类，与course类是一对一的聚合关系
		Teacher：user类，course类与user类是多对一的联系关系
	 */
	private int ID;
	private String CourseName;
	private String CourseNumber;
	private String ClassNumber;
	private String attribute;
	private String department;
	private int Capacity;
	private double Credit;
	private Teacher teacher;
	private ClassRoom classroom;
	private TimePoint timepoint;
	private CourseState courseState;
	public void setID(int ID){
		this.ID=ID;
	}
	public int getID(){
		return ID;
	}
	/**
	 * @return courseName
	 */
	public String getCourseName() {
		return CourseName;
	}
	/**
	 * @param courseName 要设置的 courseName
	 */
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	/**
	 * @return capacity
	 */
	public int getCapacity() {
		return Capacity;
	}
	/**
	 * @param capacity 要设置的 capacity
	 */
	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	/**
	 * @return credit
	 */
	public double getCredit() {
		return Credit;
	}
	/**
	 * @param credit 要设置的 credit
	 */
	public void setCredit(double credit) {
		Credit = credit;
	}
	/**
	 * @return teacher
	 */
	
	/**
	 * @return classroom
	 */
	public ClassRoom getClassroom() {
		return classroom;
	}
	/**
	 * @param classroom 要设置的 classroom
	 */
	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}
	/**
	 * @return timepoint
	 */
	public TimePoint getTimepoint() {
		return timepoint;
	}
	/**
	 * @param timepoint 要设置的 timepoint
	 */
	public void setTimepoint(TimePoint timepoint) {
		this.timepoint = timepoint;
	}
	/**
	 * @return teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}
	/**
	 * @param teacher 要设置的 teacher
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	
	/* （非 Javadoc）
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	public CourseState getCourseState() {
		return courseState;
	}
	public void setCourseState(CourseState courseState) {
		this.courseState = courseState;
	}
	public String getCourseNumber() {
		return CourseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		CourseNumber = courseNumber;
	}
	public String getClassNumber() {
		return ClassNumber;
	}
	public void setClassNumber(String classNumber) {
		ClassNumber = classNumber;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return this.getCourseName() + " " + getTimepoint().getWeeksDescription() 
				+" " + getClassroom().getRoomName() + " （" + getCourseNumber() + "-" + getClassNumber() + "）";
	}
}
