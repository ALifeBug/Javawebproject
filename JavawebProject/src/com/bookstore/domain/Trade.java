package com.bookstore.domain;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Trade {

	private Integer trade_id;
	private Date trade_time;
	private Integer user_id;
	private Set<TradeItem> items = new LinkedHashSet<>();
	public Integer getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(Integer trade_id) {
		this.trade_id = trade_id;
	}
	public Date getTrade_time() {
		return trade_time;
	}
	public void setTrade_time(Date trade_time) {
		this.trade_time = trade_time;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Set<TradeItem> getItems() {
		return items;
	}
	public void setItems(Set<TradeItem> items) {
		this.items = items;
	}
	public Trade(Integer trade_id, Date trade_time, Integer user_id, Set<TradeItem> items) {
		super();
		this.trade_id = trade_id;
		this.trade_time = trade_time;
		this.user_id = user_id;
		this.items = items;
	}
	public Trade() {
		super();
	}
	@Override
	public String toString() {
		return "Trade [trade_id=" + trade_id + ", trade_time=" + trade_time + ", user_id=" + user_id + ", items="
				+ items + "]";
	}
	
}
