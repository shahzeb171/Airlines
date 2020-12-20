package com.airlines;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/signupUser")
public class SignupUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		UserDao ud = new UserDao(name,username,password);
		if(!ud.existUser() && ud.signup()){
			session.setAttribute("loggedinUser", true);
			session.setAttribute("username",username);
			session.setAttribute("loggedin",true);
			session.setAttribute("name",name);
			System.out.println("CREATED ACCOUNT SUCCESSFULLY");
			response.sendRedirect("index");
		}
		else{
			session.setAttribute("failedSignup", true);
			response.sendRedirect("signupUser.jsp");
		}
		
	}
}