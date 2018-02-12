package com.bookstore.service;

import java.util.Iterator;
import java.util.Set;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.TradeDao;
import com.bookstore.dao.TradeItemDao;
import com.bookstore.dao.UserDao;
import com.bookstore.dao.impl.BookDaoImpl;
import com.bookstore.dao.impl.TradeDaoImpl;
import com.bookstore.dao.impl.TradeItemDaoImpl;
import com.bookstore.dao.impl.UserDaoImpl;
import com.bookstore.domain.Trade;
import com.bookstore.domain.TradeItem;
import com.bookstore.domain.User;

public class UserService {

	private UserDao userDao = new UserDaoImpl();
	
	public User getUserByUserName(String username){
		return userDao.getUser(username);
	}

	private TradeDao tradeDao = new TradeDaoImpl();
	private TradeItemDao tradeItemDao = new TradeItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	
	public User getUserWithTrades(String username){
		User user = userDao.getUser(username);
		if(user==null)
			return null;
		int userId = user.getUser_id();
		Set<Trade> trades = tradeDao.getTradeWithUserId(userId);
		if(trades!=null){
			Iterator<Trade> tradeIt = trades.iterator();
			while(tradeIt.hasNext()){
				Trade trade = tradeIt.next();
				int tradeId = trade.getTrade_id();
				Set<TradeItem> items = tradeItemDao.getTradeItemsWithTradeId(tradeId);
				if(items.size()!=0){
					for(TradeItem item : items){
						item.setBook(bookDao.getBook(item.getBook_id()));
					}
					trade.setItems(items);
				}
			}
		}
		user.setTrades(trades);			
		return user;
	}
}
