package com.airlines;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/book")
public class Booking extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if(request.getParameter("sno")!=null){
		int sno = Integer.parseInt((String)request.getParameter("sno"));
		BookingDao bd = new BookingDao(sno,username);
		if(bd.Booked()){
			session.setAttribute("Bookings", bd.history());
			session.setAttribute("BookingsLength", bd.history().size());
			RequestDispatcher rd = request.getRequestDispatcher("book.jsp");
			rd.forward(request, response);	
		}
		else
		response.sendRedirect("index");
		}
		else{
			BookingDao bd = new BookingDao(0,username);
				session.setAttribute("Bookings", bd.history());
				session.setAttribute("BookingsLength", bd.history().size());
				RequestDispatcher rd = request.getRequestDispatcher("book.jsp");
				rd.forward(request, response);	
		}
		
	}
}
