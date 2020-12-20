package com.airlines;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airlines.UserAdminCheckerDao;
@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserAdminCheckerDao uacd =  new UserAdminCheckerDao("admin",username,password);
		HttpSession session = request.getSession();
		try {
			if(uacd.check()){
				session.setAttribute("loggedinAsAdmin", true);
				session.removeAttribute("failedLoginAdmin");
				session.setAttribute("usernameAdmin",username);
				session.setAttribute("loggedinAdmin",true);
				session.setAttribute("nameAdmin",uacd.getName());
				response.sendRedirect("index");
			}
			else{
				session.setAttribute("failedLoginAdmin", true);
				response.sendRedirect("loginAdmin.jsp");
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