package hu.csdivad.book.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import hu.csdivad.book.bean.Book;

public class BookServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Book book = new Book();
		book.setTitle("Cím asd");
		book.setAuthor("Szerző qwe");
		book.setDescription("Leírás valami");
		book.setPubYear(1234);
		req.setAttribute("book", book);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/book/bookasd.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getParameterMap());
		Book book = new Book();
		try {
			BeanUtils.populate(book, req.getParameterMap());
		} catch (Exception e) {
			throw new RuntimeException();
		}
		req.setAttribute("book", book);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/book/bookasd.jsp");
		rd.forward(req, resp);
	}
	
	
	
}
