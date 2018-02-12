package com.bookstore.domain;

public class Account {

	private Integer account_id;
	private int balance;
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Account(Integer account_id, int balance) {
		super();
		this.account_id = account_id;
		this.balance = balance;
	}
	public Account() {
		super();
	}
	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", balance=" + balance + "]";
	}
	
	
}
