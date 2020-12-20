package com.airlines;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/addTimeTable")

public class AddTimeTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String fid = request.getParameter("flights");
		String departure_city = request.getParameter("fromCity");
		String arrival_city = request.getParameter("toCity");
		String departure_time = request.getParameter("departureTime");
		String arrival_time = request.getParameter("arrivalTime");
		int price = Integer.parseInt(request.getParameter("price"));
	
		fid = fid.substring(fid.indexOf("(")+1,fid.length()-1);
		departure_city = departure_city.substring(departure_city.indexOf("(")+1,departure_city.length()-1);
		arrival_city = arrival_city.substring(arrival_city.indexOf("(")+1,arrival_city.length()-1);
		if((new AddTimeTableDao(fid,departure_city,arrival_city,departure_time,arrival_time,price)).added()){
			session.setAttribute("timeTableAdded", true);
		}
		else{
			session.setAttribute("timeTableAdded", false);
		}
		response.sendRedirect("add");
	}
}
