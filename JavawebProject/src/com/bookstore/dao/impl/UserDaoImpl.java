package com.bookstore.dao.impl;

import com.bookstore.dao.UserDao;
import com.bookstore.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {


	@Override
	public User getUser(String username) {
		String sql = "select * from user where user_name =?";
		return query(sql, username);
	}

}
