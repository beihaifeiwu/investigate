package com.course.selection.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.course.selection.dao.RoleDAO;
import com.course.selection.domain.Role;

public class RoleDaoTest {
	
	RoleDAO dao = null;

	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = (RoleDAO) context.getBean("roleDao");
	}

	@Test
	public void test() {
		Role role = new Role();
		role.setRoleNmae("Admin");
		dao.save(role);
		role = new Role();
		role.setRoleNmae("Student");
		dao.save(role);
		role = new Role();
		role.setRoleNmae("Teacher");
		dao.save(role);
	}

}
