package com.course.selection.test;

import com.course.selection.domain.ClassRoom;
import com.course.selection.domain.Course;
import com.course.selection.domain.Teacher;
import com.course.selection.util.ImportExcel;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ImportExcelTest {

	@Test
	public void testCourse() {
		File file = new File("src/com/course/selection/test/测试-数据库.xls");
		System.out.println(file.getAbsolutePath().toString());
		System.out.println(file.exists());

		List<Course> courses = null;
		try {
			courses = ImportExcel.importCourseByExcel(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Course course: courses){
			System.out.println(course.getCourseName());
		}
	}
	@Test
	public void testTeacher() {
		File file = new File("src/com/course/selection/test/Book1.xls");
		System.out.println(file.getAbsolutePath().toString());
		System.out.println(file.exists());

		List<Teacher> teachers = null;
		try {
			teachers = ImportExcel.importTeacherByExcel(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Teacher teacher: teachers){
			System.out.println(teacher.getProfile().getName());
		}
	}
	@Test
	public void testClassRoom(){
		File file = new File("src/com/course/selection/test/教室信息.xls");
		System.out.println(file.getAbsolutePath().toString());
		System.out.println(file.exists());

		List<ClassRoom> classRooms = null;
		try {
			classRooms = ImportExcel.importClassRoomByExcel(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(ClassRoom classRoom: classRooms){
			System.out.println(classRoom.getRoomName());
		}
		
	}

}
