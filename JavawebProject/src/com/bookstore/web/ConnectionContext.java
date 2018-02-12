package com.bookstore.web;

import java.sql.Connection;


public class ConnectionContext {
	private ConnectionContext(){}
	private static ConnectionContext instance = new ConnectionContext();
	public static ConnectionContext GetInstance(){ return instance;}
	private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
	public void bind(Connection connection){
		connectionThreadLocal.set(connection);
	}
	public Connection get(){
		return connectionThreadLocal.get();
	}
	public void remove(){
		connectionThreadLocal.remove();
	}
	
}
