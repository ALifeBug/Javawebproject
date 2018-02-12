package com.bookstore.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.impl.BookDaoImpl;
import com.bookstore.db.JdbcUtils;
import com.bookstore.domain.Book;
import com.bookstore.domain.ShoppingCartItem;
import com.bookstore.web.ConnectionContext;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;

public class BookDaoTest {

	private BookDao bookDao = new BookDaoImpl();
	@Test
	public void testGetBook() {
		Connection connection = JdbcUtils.getConnection();
		ConnectionContext.GetInstance().bind(connection);
		
		Book book = bookDao.getBook(5);
		System.out.println(book);
	}
	
	@Test
	public void testGetPage(){
		CriteriaBook cb = new CriteriaBook(10, 100, 2);
		Page<Book> page = bookDao.getPage(cb);
		System.out.println("pageNo:"+page.getPageNo());
		System.out.println("totalPageNumber:"+page.getTotalPageNumber());
		System.out.println("list:"+page.getList());
		System.out.println("prevPage:"+page.getPrevPage());
		System.out.println("nextPage:"+page.getNextPage());
	}
	
	@Test
	public void testGetStoreNumber(){
		int storeNumber = bookDao.getStoreNumber(3);
		System.out.println(storeNumber);
	}
	
	@Test
	public void testBatchUpdateStoreNumberAndSalesAmount(){
		Collection<ShoppingCartItem> items = new ArrayList<>();
		Book book = bookDao.getBook(1);
		ShoppingCartItem sci = new ShoppingCartItem(book);
		sci.setQuantity(10);
		items.add(sci);
		
		book = bookDao.getBook(2);
		sci=new ShoppingCartItem(book);
		sci.setQuantity(2);
		items.add(sci);
		
		book = bookDao.getBook(3);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(2);
		items.add(sci);
		
		book = bookDao.getBook(4);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(4);
		items.add(sci);
		bookDao.batchUpdateStoreNumberAndSalesAmount(items);
	}
	
}
