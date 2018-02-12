package com.bookstore.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Set;
import org.junit.Test;

import com.bookstore.dao.TradeDao;
import com.bookstore.dao.impl.TradeDaoImpl;
import com.bookstore.domain.Trade;

public class TradeDaoTest {

	private TradeDao tradeDao = new TradeDaoImpl();
	@Test
	public void testInsert() {
		Trade trade = new Trade();
		trade.setUser_id(1);
		trade.setTrade_time(new Date(new java.util.Date().getTime()));
		tradeDao.insertTrade(trade);
	}
	
	@Test
	public void testGetTradeWithUserId(){
		Set<Trade> trades = tradeDao.getTradeWithUserId(1);
		System.out.println(trades);
	}
}
