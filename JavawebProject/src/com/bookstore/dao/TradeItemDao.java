package com.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.bookstore.domain.TradeItem;

public interface TradeItemDao {
	void batchSave(Collection<TradeItem> items);
	Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);
}
