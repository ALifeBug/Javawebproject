package com.bookstore.dao;

import java.util.List;

public interface Dao<T> {
	//返回插入后的记录的ID
	long insert(String sql,Object...args);
	void update(String sql,Object...args);
	T query(String sql,Object...args);
	List<T> queryForList(String sql,Object...args);
	<V> V getSingleVal(String sql,Object...args);
	void batch(String sql,Object[]...params);
}
