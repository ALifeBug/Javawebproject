package com.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.domain.User;
import com.bookstore.service.UserService;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService = new UserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		User user = userService.getUserWithTrades(username);
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request, response);
	}

}
