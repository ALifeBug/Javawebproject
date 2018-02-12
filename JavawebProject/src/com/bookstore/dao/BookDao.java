package com.bookstore.dao;

import java.util.Collection;
import java.util.List;

import com.bookstore.domain.Book;
import com.bookstore.domain.ShoppingCartItem;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;

public interface BookDao {
	Book getBook(int id);
	Page<Book> getPage(CriteriaBook cb);
	long getTotalBookNumber(CriteriaBook cb);
	List<Book> getPageList(CriteriaBook cb,int pageSize);
	int getStoreNumber(Integer id);
	void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);
}
