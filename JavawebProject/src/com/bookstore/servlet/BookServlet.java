package com.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.domain.Account;
import com.bookstore.domain.Book;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.ShoppingCartItem;
import com.bookstore.domain.User;
import com.bookstore.service.AccountService;
import com.bookstore.service.BookService;
import com.bookstore.service.UserService;
import com.bookstore.web.BookStoreWebUtils;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	private UserService userService = new UserService();
	private AccountService accountService = new AccountService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this,request,response);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	protected void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		request.getRequestDispatcher("/WEB-INF/pages/"+page+".jsp").forward(request, response);
	}
	
	protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNo =1;
		float minPrice = 0;
		float maxPrice = Integer.MAX_VALUE;
	   if(request.getParameter("pageNo")!=null){
		   pageNo = Integer.parseInt(request.getParameter("pageNo"));
	   }
	   if(request.getParameter("minPrice")!=null){
		   minPrice =Float.parseFloat(request.getParameter("minPrice"));
	   }
	   if(request.getParameter("maxPrice")!=null){
		   maxPrice = Float.parseFloat(request.getParameter("maxPrice"));
	   }
		CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);
		Page<Book> page = bookService.getPage(criteriaBook);
		request.setAttribute("bookpage", page);
		request.setAttribute("cb", criteriaBook);
		request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request, response);
	}
	
	protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = -1;
		Book book = null;
		id = Integer.parseInt(idStr);
		if(id>0)
			book = bookService.getBook(id);
		if(book==null){
			response.sendRedirect(request.getContextPath() + "/error.jsp");
			return;
		}
		request.setAttribute("book", book);
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
	}
	
	protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id=-1;
		boolean flag = false;
		id=Integer.parseInt(idStr);
		if(id>0){
			ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
			flag = bookService.addToCart(id, sc);
		}
		if(flag){
			getBooks(request, response);
			return;
		}
	}
	
	protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id =-1;
		id=Integer.parseInt(idStr);
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		bookService.removeItemFromShoppingCart(sc, id);
		if(sc.isEmpty()){
			request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
	}
	
	protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		bookService.clearShoppingCart(sc);
		request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request, response);
	}
	
	protected void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 简单验证: 验证表单域的值是否符合基本的规范: 是否为空, 是否可以转为 int 类型, 是否是一个 email. 不需要进行查询
		//数据库或调用任何的业务方法.
		String username = request.getParameter("username");
		String accountId = request.getParameter("accountId");
		
		StringBuffer errors = validateFormField(username, accountId);
		
		//表单验证通过。 
		if(errors.toString().equals("")){
			errors = validateUser(username, accountId);
			
			//用户名和账号验证通过
			if(errors.toString().equals("")){
				errors = validateBookStoreNumber(request);
				
				//库存验证通过
				if(errors.toString().equals("")){
					errors = validateBalance(request, accountId);
				}
			}
		}
		
		if(!errors.toString().equals("")){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request, response);
			return;
		}
		
		//验证通过执行具体的逻辑操作
		bookService.cash(BookStoreWebUtils.getShoppingCart(request), username, accountId); 
		response.sendRedirect(request.getContextPath() + "/success.jsp");
	}

	private StringBuffer validateBalance(HttpServletRequest request, String accountId) {
		StringBuffer errors = new StringBuffer();
		ShoppingCart cart = BookStoreWebUtils.getShoppingCart(request);
		Account account = accountService.getAccount(Integer.parseInt(accountId));
		if(cart.getTotalMoney()>account.getBalance()){
			errors.append("余额不足");
		}
		return errors;
	}

	private StringBuffer validateBookStoreNumber(HttpServletRequest request) {
		StringBuffer errors = new StringBuffer();
		ShoppingCart cart = BookStoreWebUtils.getShoppingCart(request);
		for(ShoppingCartItem sci:cart.getItems()){
			int quantity = sci.getQuantity();
			int storeNumber = bookService.getBook(sci.getBook().getBook_id()).getStore_number();
			if(quantity>storeNumber){
				errors.append(sci.getBook().getTitle()+"库存不足<br>");
			}
		}
		return errors;
	}

	private StringBuffer validateUser(String username, String accountId) {
		boolean flag = false;
		User user = userService.getUserByUserName(username);
		StringBuffer error = new StringBuffer();
		if(user==null){
			error.append("用户不存在");
			return error;
		}else{
			int accountId2=user.getAccount_id();
			if(accountId.trim().equals(accountId2+"")){
				flag = true;
			}
		}
		if(!flag){
			error.append("用户名和账号不匹配");
		}
		return error;
	}

	private StringBuffer validateFormField(String username, String accountId) {
StringBuffer errors = new StringBuffer("");
		
		if(username == null || username.trim().equals("")){
			errors.append("用户名不能为空<br>");
		}
		
		if(accountId == null || accountId.trim().equals("")){
			errors.append("账号不能为空");			
		}
		
		return errors;
	}
	
}
