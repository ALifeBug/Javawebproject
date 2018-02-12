package com.bookstore.domain;


public class TradeItem {

	private Book book;
	private int quantity;
	private Integer book_id;
	private Integer trade_id;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public Integer getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(Integer trade_id) {
		this.trade_id = trade_id;
	}
	public TradeItem(Book book, int quantity, Integer book_id, Integer trade_id) {
		super();
		this.book = book;
		this.quantity = quantity;
		this.book_id = book_id;
		this.trade_id = trade_id;
	}
	public TradeItem() {
		super();
	}
	@Override
	public String toString() {
		return "TradeItem [book=" + book + ", quantity=" + quantity + ", book_id=" + book_id + ", trade_id=" + trade_id
				+ "]";
	}
	
}
