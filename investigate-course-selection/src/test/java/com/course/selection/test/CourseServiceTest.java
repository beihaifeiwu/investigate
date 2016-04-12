package com.course.selection.test;

import com.course.selection.domain.Course;
import com.course.selection.service.CourseService;
import com.course.selection.util.ImportExcel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;

public class CourseServiceTest {

	CourseService cs = null;
	
	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		cs = (CourseService) context.getBean("courseService");
	}

	@Test
	public void test() {
		File file = new File("src/com/course/selection/test/����-���ݿ�.xls");
		System.out.println(file.getAbsolutePath().toString());
		System.out.println(file.exists());

		List<Course> courses = null;
		try {
			courses = ImportExcel.importCourseByExcel(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cs.createCourse(courses);
	}

}
