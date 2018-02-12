package com.bookstore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.bookstore.dao.AccountDao;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.TradeDao;
import com.bookstore.dao.TradeItemDao;
import com.bookstore.dao.UserDao;
import com.bookstore.dao.impl.AccountDaoImpl;
import com.bookstore.dao.impl.BookDaoImpl;
import com.bookstore.dao.impl.TradeDaoImpl;
import com.bookstore.dao.impl.TradeItemDaoImpl;
import com.bookstore.dao.impl.UserDaoImpl;
import com.bookstore.domain.Book;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.ShoppingCartItem;
import com.bookstore.domain.Trade;
import com.bookstore.domain.TradeItem;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;
import com.sun.javafx.sg.prism.web.NGWebView;

public class BookService {

	private BookDao bookDao = new BookDaoImpl();
	private AccountDao accountDao = new AccountDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private TradeDao tradeDao = new TradeDaoImpl();
	private TradeItemDao tradeItemDao = new TradeItemDaoImpl();
	public Page<Book> getPage(CriteriaBook criteriaBook){
		return bookDao.getPage(criteriaBook);
	}
	public Book getBook(int id) {
		return bookDao.getBook(id);
	}
	public boolean addToCart(int id,ShoppingCart sc){
		Book book = bookDao.getBook(id);
		if(book!=null){
			sc.addBook(book);
			return true;
		}
		return false;
	}
	public void removeItemFromShoppingCart(ShoppingCart sc,int id){sc.removeItem(id);}
	public void clearShoppingCart(ShoppingCart sc){sc.clear();}
	public void updateItemQuantity(ShoppingCart sc,int id,int quantity){sc.updateItemQuantity(id, quantity);}
	
	public void cash(ShoppingCart shoppingCart,String username,String accountId){
		bookDao.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
		//int i=10/0;
		accountDao.updateBalance(Integer.parseInt(accountId),shoppingCart.getTotalMoney());
		Trade trade = new Trade();
		trade.setTrade_time(new Date(new java.util.Date().getTime()));
		trade.setUser_id(userDao.getUser(username).getUser_id());
		long tradeId=tradeDao.insertTrade(trade);	
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem sci:shoppingCart.getItems()){
			TradeItem tradeItem = new TradeItem();
			tradeItem.setBook_id(sci.getBook().getBook_id());
			tradeItem.setQuantity(sci.getQuantity());
			tradeItem.setTrade_id((int)tradeId);
			items.add(tradeItem);
		}
		tradeItemDao.batchSave(items);
		
		shoppingCart.clear();
	}
}
