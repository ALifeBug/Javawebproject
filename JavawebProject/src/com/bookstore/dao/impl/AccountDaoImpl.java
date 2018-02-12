package com.bookstore.dao.impl;

import com.bookstore.dao.AccountDao;
import com.bookstore.domain.Account;

public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {


	@Override
	public Account get(Integer accountId) {
		String sql ="select account_id,balance from account where account_id=?";
		Account account = query(sql, accountId);
		return account;
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql = "update account set balance = balance-? where account_id = ?";
		update(sql, amount,accountId);
	}

}
