package com.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bookstore.dao.BookDao;
import com.bookstore.domain.Book;
import com.bookstore.domain.ShoppingCartItem;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	
	@Override
	public Book getBook(int id) {
		String sql = "select book_id,author,title,price,publishing_date,"+
	"sales_amount,store_number,remark from book where book_id=?";
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		Page<Book> page = new Page<>(cb.getPageNo());
		page.setTotalItemNumber(getTotalBookNumber(cb));
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));
		return page;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "select count(book_id) from book where price >=? and price <=?";
		return getSingleVal(sql,cb.getMinPrice(),cb.getMaxPrice());
	}

	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "select book_id,author,title,price,publishing_date,"+
				"sales_amount,store_number,remark from book where price >=? and price <=? limit ?,?";
		return queryForList(sql, cb.getMinPrice(),cb.getMaxPrice(),(cb.getPageNo()-1)*pageSize,pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {
		String sql = "select store_number from book where book_id =?";
		return getSingleVal(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
		String sql = "update book set sales_amount=sales_amount+?,store_number=store_number-? where book_id=?";
		Object [][]params = new Object[items.size()][3];
		List<ShoppingCartItem> scis = new ArrayList<>(items);
		for(int i=0;i<items.size();i++){
			params[i][0]=scis.get(i).getQuantity();
			params[i][1]=scis.get(i).getQuantity();
			params[i][2]=scis.get(i).getBook().getBook_id();
		}
		batch(sql, params);
	}

}
