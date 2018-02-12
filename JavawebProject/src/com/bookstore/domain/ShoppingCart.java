package com.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	
	private Map<Integer, ShoppingCartItem> books = new HashMap<>();
	/**
	 * �޸�ָ�������������
	 */
	public void updateItemQuantity(Integer id,int quantity){
		ShoppingCartItem sci = books.get(id);
		if(sci!=null)
			sci.setQuantity(quantity);
	}
	
	/**
	 * �Ƴ�ָ���Ĺ�����
	 */
	public void removeItem(Integer id){
		books.remove(id);
	}
	
	/**
	 * ��չ��ﳵ
	 */
	public void clear(){books.clear();}
	
	/**
	 * �жϹ��ﳵ�Ƿ�Ϊ��
	 */
	public boolean isEmpty(){return books.isEmpty();}
	
	/**
	 * ��ȡ���ﳵ�е����е� ShoppingCartItem ��ɵļ���
	 * @return
	 */
	public Collection<ShoppingCartItem> getItems(){
		return books.values();
	}
	
	/**
	 * ��ȡ���ﳵ�����е���Ʒ���ܵ�Ǯ��
	 */
	public float getTotalMoney(){
		float total = 0;
		for(ShoppingCartItem sci :getItems()){
			total+=sci.getItemMoney();
		}
		return total;
	}
	
	/**
	 * ���ع��ﳵ����Ʒ��������
	 */
	public int getBookNumber(){
		int total = 0;
		for(ShoppingCartItem sci : books.values()){
			total+=sci.getQuantity();
		}
		return total;
	}
	
	/**
	 * ���鹺�ﳵ���Ƿ���ָ��id����Ʒ
	 */
	public boolean hasBook(Integer id){
		return books.containsKey(id);
	}
	
	/**
	 * ���ﳵ�����һ����Ʒ
	 */
	public void addBook(Book book){
		ShoppingCartItem sci = books.get(book.getBook_id());
		if(sci ==null){
			sci = new ShoppingCartItem(book);
			books.put(book.getBook_id(),sci);
		}else{
			sci.increment();
		}
	}
}
