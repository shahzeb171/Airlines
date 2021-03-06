package com.airlines;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		

		if(session.getAttribute("loggedinAdmin")!=null && (Boolean)session.getAttribute("loggedinAdmin")){
		session.removeAttribute("failedAdminLogin");
		session.removeAttribute("usernameAdmin");
		session.removeAttribute("loggedinAdmin");
		session.setAttribute("loggedoutAdmin",true);
		session.removeAttribute("nameAdmin");
		}
		
		else{
		session.removeAttribute("loggedin");
		session.removeAttribute("failedLogin");
		session.removeAttribute("username");
		session.setAttribute("loggedoutUser",true);
		}
		response.sendRedirect("index");
	}
}