package com.course.selection.domain;

import java.io.Serializable;

/**
 * 课程状态类
 * @author Administrator
 *
 */
public class CourseState implements Serializable {

	private static final long serialVersionUID = -4526396427822746761L;
	
	private int ID;
	
	private int studentNumber;
	
	private CourseStateType stateType;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public CourseStateType getStateType() {
		return stateType;
	}

	public void setStateType(CourseStateType stateType) {
		this.stateType = stateType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseState other = (CourseState) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}
