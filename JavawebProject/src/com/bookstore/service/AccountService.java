package com.bookstore.service;

import com.bookstore.dao.AccountDao;
import com.bookstore.dao.impl.AccountDaoImpl;
import com.bookstore.domain.Account;

public class AccountService {

	private AccountDao accountDao = new AccountDaoImpl();
	public Account getAccount(int accountId){
		return accountDao.get(accountId);
	}
}
