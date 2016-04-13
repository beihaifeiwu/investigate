package com.course.selection.test;

import com.course.selection.dao.PageQuery;
import com.course.selection.domain.Role;
import com.course.selection.domain.Teacher;
import com.course.selection.domain.User;
import com.course.selection.exception.RoleExistException;
import com.course.selection.exception.RoleNotExistException;
import com.course.selection.exception.UserExistException;
import com.course.selection.service.AdminService;
import com.course.selection.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdminServiceTest {

	AdminService as = null;
  UserService us = null;
	
	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		as = (AdminService) context.getBean("adminService");
		us = (UserService) context.getBean("userService");
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
  public void testCreateAdminAccount() throws RoleNotExistException, UserExistException {
    User user = new User();
    user.setAccount("admin");
    user.setPassword("admin");
    us.createUser(user);
  }

	@Test
	public void testCreateRole() throws RoleExistException{
		Role role = new Role();
		role.setRoleName("Admin");
		as.createRole(role);
		role = new Role();
		role.setRoleName("Student");
		as.createRole(role);		
		role = new Role();
		role.setRoleName("Teacher");
		as.createRole(role);
	}
}
