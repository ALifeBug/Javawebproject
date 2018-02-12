package com.bookstore.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Test;

import com.bookstore.dao.TradeItemDao;
import com.bookstore.dao.impl.TradeItemDaoImpl;
import com.bookstore.domain.TradeItem;

public class TradeItemDaoTest {

	TradeItemDao tradeItemDao = new TradeItemDaoImpl();
	@Test
	public void testBatchSave() {
		Collection<TradeItem> items = new ArrayList<>();
		items.add(new TradeItem(null, 10, 1, 1));
		items.add(new TradeItem(null, 10, 2, 1));
		items.add(new TradeItem(null, 10, 3, 1));
		tradeItemDao.batchSave(items);
	}

	@Test
	public void testGetTradeItemsWithTradeId(){
		Set<TradeItem> items = tradeItemDao.getTradeItemsWithTradeId(1);
		System.out.println(items);
	}
}
