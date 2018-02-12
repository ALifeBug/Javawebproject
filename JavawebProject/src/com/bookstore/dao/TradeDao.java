package com.bookstore.dao;

import java.util.Set;

import com.bookstore.domain.Trade;

public interface TradeDao {
	long insertTrade(Trade trade);
	Set<Trade> getTradeWithUserId(Integer usrId);
}
