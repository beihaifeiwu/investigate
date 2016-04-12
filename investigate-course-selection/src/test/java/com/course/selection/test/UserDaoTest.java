package com.course.selection.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.course.selection.domain.Role;
import com.course.selection.domain.User;
import com.course.selection.exception.RoleNotExistException;
import com.course.selection.exception.UserExistException;
import com.course.selection.service.UserService;

public class UserDaoTest {
	
	UserService us = null;
	
	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		us = (UserService) context.getBean("userService");
	}

	@Test
	public void test() {
		User user = new User();
		user.setAccount("admin");
		user.setPassword("123");
		Role role = new Role();
		role.setRoleNmae("Admin");
		user.setRole(role);
		try {
			us.createUser(user);
		} catch (RoleNotExistException e) {
			e.printStackTrace();
		} catch (UserExistException e) {
			e.printStackTrace();
		}
	}

}
