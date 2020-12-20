package com.airlines;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/addCity")
public class AddCity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String code = request.getParameter("code");
		String name = request.getParameter("city");
		if((new AddCityDao(code,name)).added()){
			session.setAttribute("cityAdded", true);
		}
		else{
			session.setAttribute("cityAdded", false);
		}
		response.sendRedirect("add");
	}

}
