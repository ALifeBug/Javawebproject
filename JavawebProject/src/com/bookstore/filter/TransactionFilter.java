package com.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.db.JdbcUtils;
import com.bookstore.web.ConnectionContext;

public class TransactionFilter implements Filter{

	
	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			ConnectionContext.GetInstance().bind(connection);
			chain.doFilter(request, response);
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			HttpServletRequest resq = (HttpServletRequest)request;
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect(resq.getContextPath()+"/error.jsp");
		}finally{
			ConnectionContext.GetInstance().remove();
			JdbcUtils.release(connection);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
