package com.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bookstore.dao.impl.BaseDao;
import com.bookstore.domain.Account;

public class BaseDaoTest {

	private BaseDao<Account> baseDao =  new BaseDao<>();
	@Test
	public void testInsert() {
		String sql = "insert into account(account_id,balance) values(?,?)";
		baseDao.insert(sql,3,2000);
	}

}
