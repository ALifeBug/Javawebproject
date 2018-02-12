package com.bookstore.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import com.bookstore.dao.AccountDao;
import com.bookstore.dao.impl.AccountDaoImpl;
import com.bookstore.domain.Account;

public class AccountDaoTest {

	AccountDao accountDao = new AccountDaoImpl();
	@Test
	public void testGet() {
		Account account = accountDao.get(2);
		System.out.println(account);
	}

	@Test
	public void testUpdateBalance(){
		accountDao.updateBalance(1, 100);
	}
}
