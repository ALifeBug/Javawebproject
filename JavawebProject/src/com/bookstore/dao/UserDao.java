package com.bookstore.dao;

import com.bookstore.domain.User;

public interface UserDao {
	User getUser(String username);
}
