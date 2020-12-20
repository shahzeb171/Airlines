package com.airlines;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CancelBookingFilter
 */
@WebFilter("/cancel")
public class CancelBookingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CancelBookingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @throws ServletException 
	 * @throws IOException 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException  {
		String s = request.getParameter("bsno");
		HttpSession session = ((HttpServletRequest) request).getSession();
		HttpServletResponse httpResponse = ((HttpServletResponse) response);
			System.out.println("bsno "+s+" check "+isInteger(s)+" "+session.getAttribute("username"));
			if( isInteger(s) && session.getAttribute("username") !=null && (new MaxSnoDao(Integer.parseInt(s) )).checkBookings((String)session.getAttribute("username"))) {
				
				if(session.getAttribute("loggedin")!=null && (Boolean)session.getAttribute("loggedin"))
				chain.doFilter(request, response);
				else
					httpResponse.sendRedirect("loginUser.jsp");
					
			}
			else{
				session.setAttribute("aFiltered",true);
				httpResponse.sendRedirect("index");
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
