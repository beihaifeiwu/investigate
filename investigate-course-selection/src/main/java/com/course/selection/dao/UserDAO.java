package com.course.selection.dao;

import com.course.selection.domain.User;

import java.util.List;

public interface UserDAO {

	User get(Integer id);

	Integer save(User user);

	void update(User user);

	void delete(User user);

	void delete(Integer id);

	List<User> findByNameAndPasswd(String name, String passwd);

	List<User> findAllUser();

	PageQuery<User> scanUserByPage(PageQuery<User> pq);
}
