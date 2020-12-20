package com.airlines;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Cancel
 */
@WebServlet("/cancel")
public class Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bsno = Integer.parseInt((String)request.getParameter("bsno"));
		HttpSession session = request.getSession();
		if(new CancelDao(bsno,(String)(session.getAttribute("username"))).cancelled()){
			session.setAttribute("cancelled",true);
		}
		else{
			session.setAttribute("cancelled",true);
		}
		response.sendRedirect("book");
	}


}
