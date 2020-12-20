package com.airlines;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addFlights")


public class AddFlights extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		System.out.println(code+" "+name);
		if((new AddFlightDao(code,name)).added()){
			request.getSession().setAttribute("flightAdded", true);
			System.out.println("FLIGHT ADDED SUCCESSFULLY");
		}
		else{
			System.out.println("FLIGHT ADDED FAILED");
			request.getSession().setAttribute("flightAdded", false);
		}
		response.sendRedirect("add");
	}
}
