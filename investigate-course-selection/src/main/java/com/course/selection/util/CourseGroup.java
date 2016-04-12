package com.course.selection.util;

import com.course.selection.domain.Course;

import java.util.*;

public class CourseGroup extends AbstractList<Course> {

	private List<Course> list = new ArrayList<>();
	/**
	 * 
	 */
	@Override
	public Course get(int arg0) {
		return list.get(arg0);
	}

	/**
	 * @return the list
	 */
	public List<Course> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Course> list) {
		this.list = list;
	}

	@Override
	public int size() {
		return list.size();
	}


	@Override
	public boolean add(Course e) {
		return list.add(e);
	}

	@Override
	public ListIterator<Course> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<Course> listIterator(int index) {
		return list.listIterator(index);
	}

	/*
	 * 有序返回所有元素
	 * @see java.util.AbstractList#iterator()
	 */
	@Override
	public Iterator<Course> iterator() {
		return list.iterator();
	}


	@Override
	public Course remove(int index) {
		return list.remove(index);
	}


	public String getDescription() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < list.size(); i++){
			Course course = list.get(i);
			sb.append(course.toString());
			if(i < list.size()-1){
				sb.append("    ");
			}
		}
		
		return sb.toString();
	}

	
}
