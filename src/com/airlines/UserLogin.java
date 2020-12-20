package com.airlines;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/userLogin")
public class UserLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserAdminCheckerDao uacd =  new UserAdminCheckerDao("users",username,password);
		HttpSession session = request.getSession();
		try {
			if(uacd.check()){
				session.setAttribute("loggedinUser",true);
				session.removeAttribute("failedLogin");
				session.setAttribute("username",username);
				session.setAttribute("loggedin",true);
				session.setAttribute("name",uacd.getName());
				response.sendRedirect("index");
			}
			else{
				session.setAttribute("failedLogin", true);
				response.sendRedirect("loginUser.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}