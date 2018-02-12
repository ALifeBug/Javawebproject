package com.bookstore.dao;

import com.bookstore.domain.Account;

public interface AccountDao {
	Account get(Integer accountId);
	void updateBalance(Integer accountId,float amount);
}
