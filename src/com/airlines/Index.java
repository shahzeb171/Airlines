package com.airlines;

import java.util.Set;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> set = (new CitiesDao()).cities();
		request.setAttribute("Set", set);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);	
	}
}
