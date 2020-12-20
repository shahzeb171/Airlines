package com.airlines;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.Set;

/**
 * Servlet Filter implementation class AddTimeTableFilter
 */
@WebFilter("/addTimeTable")
public class AddTimeTableFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AddTimeTableFilter() {
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
		try {
			String fid = request.getParameter("flights");
			String departure_city = request.getParameter("fromCity");
			String arrival_city = request.getParameter("toCity");
			String departure_time = request.getParameter("departureTime");
			String arrival_time = request.getParameter("arrivalTime");
			String price = request.getParameter("price");
			
			Set<String> setFlights = (new FlightsDao()).flights();
			Set<String> setCities = (new CitiesDao()).cities();
			if(setCities.contains(departure_city) && setCities.contains(arrival_city) && setFlights.contains(fid) && checkInt(price) && checkTime(departure_time) && checkTime(arrival_time)){
				chain.doFilter(request, response);
			}
			else{
				HttpServletResponse res = ((HttpServletResponse)response);
				HttpSession session = ((HttpServletRequest)request).getSession();
				session.setAttribute("failedTimeTable",true);
				res.sendRedirect("add");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public boolean checkInt(String price){
		try{
			@SuppressWarnings("unused")
			int x  = Integer.parseInt(price);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public boolean checkTime(String time){
		try{
		String arr[] = time.split(":");
		int hrs = Integer.parseInt(arr[0]);
		int mins = Integer.parseInt(arr[1]);
		if(hrs<0 || mins <0 || hrs>23 || mins>59)
			return false;
		return true;
		}
		catch(Exception e){
			System.out.print(e);
			return false;
		}
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
