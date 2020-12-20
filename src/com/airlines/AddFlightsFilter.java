package com.airlines;

import java.util.Set;
import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AddFlightsFilter
 */
@WebFilter("/addFlights")
public class AddFlightsFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AddFlightsFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		HttpServletResponse res = (HttpServletResponse)response;
		try{
		
		
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		System.out.println(code+" "+name);
		
		if(check(code)&&check(name)){
			System.out.println(code+" "+name);
		String fid = name+"("+code+")";
		
		Set<String> setFlights = (new FlightsDao()).flights();
		if(!setFlights.contains(fid)){

			System.out.println("FLIGHT ADDED SUCCESSFULLY");
			chain.doFilter(request, response);
				
			
		}else{
			System.out.println("FLIGHT ADDED FAILED");
			session.setAttribute("sameFlight",true);
			res.sendRedirect("add");
		}
		}
		else{
			session.setAttribute("incorrectFlight",true);
			res.sendRedirect("add");
		}
		}
		
		catch(Exception e){
			session.setAttribute("errorFlight",true);
			res.sendRedirect("add");
		}
	}
	public  boolean check(String s){
		for(char c : s.toCharArray()){
			if((c!=32&&c<48) || (c>57 && c<63) || (c>90 && c<97) || (c>122))
				return false;
		}
		return true;
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
