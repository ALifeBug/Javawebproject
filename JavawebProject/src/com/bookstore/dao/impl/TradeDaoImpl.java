package com.bookstore.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import com.bookstore.dao.TradeDao;
import com.bookstore.domain.Trade;

public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao {

	@Override
	public long insertTrade(Trade trade) {
		String sql = "insert into trade (user_id,trade_time) values(?,?)";
		long tradeId = insert(sql, trade.getUser_id(),trade.getTrade_time());
		return tradeId;
	}

	@Override
	public Set<Trade> getTradeWithUserId(Integer usrId) {
		String sql = "select trade_id,user_id,trade_time from trade where user_id = ? order by trade_time desc";
		return new LinkedHashSet<>(queryForList(sql, usrId));
	}

}
