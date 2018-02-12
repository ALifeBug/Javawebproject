package com.bookstore.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.bookstore.dao.UserDao;
import com.bookstore.dao.impl.UserDaoImpl;
import com.bookstore.domain.User;

public class UserDaoTest {

	UserDao userDao = new UserDaoImpl();
	@Test
	public void testGet() {
		User user = userDao.getUser("Nick");
		System.out.println(user);
	}

}
