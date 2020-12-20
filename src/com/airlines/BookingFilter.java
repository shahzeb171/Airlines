package com.airlines;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class BookingFilter
 */
@WebFilter("/book")
public class BookingFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String s = request.getParameter("sno");
		HttpSession session = ((HttpServletRequest) request).getSession();
		HttpServletResponse httpResponse = ((HttpServletResponse) response);
		try {
			if((s==null && session.getAttribute("cancelled")!=null) || isInteger(s)&&(new MaxSnoDao(Integer.parseInt(s))).checkTimeTable()) {
				
				if(session.getAttribute("loggedin")!=null && (Boolean)session.getAttribute("loggedin"))
				chain.doFilter(request, response);
				else
					httpResponse.sendRedirect("loginUser.jsp");
					
			}
			else{
				session.setAttribute("aFiltered",true);
				httpResponse.sendRedirect("index");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	public boolean isInteger(String s){
		try{
			@SuppressWarnings("unused")
			int x = Integer.parseInt(s);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
