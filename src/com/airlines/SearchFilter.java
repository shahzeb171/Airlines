package com.airlines;

import java.util.Set;
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
 * Servlet Filter implementation class SearchFilter
 */
@WebFilter("/search")
public class SearchFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = ((HttpServletRequest)request).getSession();
		Set<String> set = (new CitiesDao()).cities();
		String toCity = request.getParameter("toCity"),fromCity=request.getParameter("fromCity");
		if(toCity.equals(fromCity)){
			session.setAttribute("sameCity", true);
			res.sendRedirect("index");
		}
		else if(!set.contains(toCity) || !set.contains(fromCity)){
			session.setAttribute("noCity", true);
			res.sendRedirect("index");
		}
		else
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
