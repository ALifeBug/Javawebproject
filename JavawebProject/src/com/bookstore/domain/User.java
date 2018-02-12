package com.bookstore.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class User {

	private Integer user_id;
	private String user_name;
	private int account_id;
	private Set<Trade> trades = new LinkedHashSet<>();
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public Set<Trade> getTrades() {
		return trades;
	}
	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}
	public User(Integer user_id, String user_name, int account_id, Set<Trade> trades) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.account_id = account_id;
		this.trades = trades;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", account_id=" + account_id + ", trades="
				+ trades + "]";
	}
	
	
}
