package com.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bookstore.dao.TradeItemDao;
import com.bookstore.domain.TradeItem;

public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {

	@Override
	public void batchSave(Collection<TradeItem> items) {
		String sql = "insert into trade_item (quantity,book_id,trade_id) values(?,?,?)";
		Object[][] params = new Object[items.size()][3];
		List<TradeItem> tradeItems = new ArrayList<>(items);
		for(int i=0;i<items.size();i++){
			params[i][0] = tradeItems.get(i).getQuantity();
			params[i][1] = tradeItems.get(i).getBook_id();
			params[i][2] = tradeItems.get(i).getTrade_id();
		}
		batch(sql, params);
	}

	@Override
	public Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId) {
		String sql = "select * from trade_item where trade_id =?";
		return new HashSet<>(queryForList(sql, tradeId));
	}

}
