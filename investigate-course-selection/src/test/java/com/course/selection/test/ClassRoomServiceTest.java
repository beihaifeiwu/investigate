package com.course.selection.test;

import com.course.selection.domain.ClassRoom;
import com.course.selection.service.ClassRoomService;
import com.course.selection.util.ImportExcel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;

public class ClassRoomServiceTest {

	ClassRoomService crs = null;
	
	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		crs = (ClassRoomService) context.getBean("classRoomService");
	}

	@Test
	public void test() {
		File file = new File("src/com/course/selection/test/������Ϣ.xls");
		System.out.println(file.getAbsolutePath().toString());
		System.out.println(file.exists());

		List<ClassRoom> classRooms = null;
		try {
			classRooms = ImportExcel.importClassRoomByExcel(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		crs.createClassRooms(classRooms);
	}

}
