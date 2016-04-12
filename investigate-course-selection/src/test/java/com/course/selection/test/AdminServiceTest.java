package com.course.selection.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.Role;
import com.course.selection.domain.Teacher;
import com.course.selection.exception.RoleExistException;
import com.course.selection.service.AdminService;

public class AdminServiceTest {

	AdminService as = null;
	
	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		as = (AdminService) context.getBean("adminService");
	}

	@Test
	public void testSearchTeacherByName() {
		PageQuery<Teacher> pq = as.searchTeacherByName("王");
		System.out.println("Total Num : " + pq.getTotalNum());
		for(Teacher t : pq.getDatas()){
			System.out.println(t.getID() + " "+ t.getAccount() + " " + t.getPassword()
						+ " " + t.getProfile().getName() + " " + t.getTitle() + " " + t.getProfile().getDepartment());
		}
	}

	@Test
	public void testCreateRole() throws RoleExistException{
		Role role = new Role();
		role.setRoleNmae("Admin");
		as.createRole(role);
		role = new Role();
		role.setRoleNmae("Student");
		as.createRole(role);		
		role = new Role();
		role.setRoleNmae("Teacher");
		as.createRole(role);
	}
}
