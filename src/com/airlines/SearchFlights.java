package com.airlines; 

import java.util.List;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class SearchFlights
 */
@WebServlet("/search")
public class SearchFlights extends HttpServlet {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String toCity=request.getParameter("toCity").toString(),fromCity=request.getParameter("fromCity").toString();
			String toCityCode = toCity.substring(toCity.indexOf('(')+1,toCity.indexOf(')'));
			String fromCityCode = fromCity.substring(fromCity.indexOf('(')+1,fromCity.indexOf(')'));
			List<Time_Table> list = (new TimeTableDao(toCityCode,fromCityCode)).TimeTableFetcher();
				request.setAttribute("List", list);
				RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
				rd.forward(request, response);
			
		}
}