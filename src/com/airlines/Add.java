package com.airlines;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add
 */
@WebServlet("/add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Set<String> set = (new CitiesDao()).cities();
			Set<String> setF = (new FlightsDao()).flights();
			Set<Bookings> setB = (new BookingDao(0,"")).historyOfAll();
			request.setAttribute("Set", set);
			request.setAttribute("BookingsLength", setB.size());
			request.setAttribute("SetFlight", setF);
			request.setAttribute("SetBooking", setB);
			RequestDispatcher rd = request.getRequestDispatcher("add.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
