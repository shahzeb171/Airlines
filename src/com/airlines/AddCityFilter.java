package com.airlines;

import java.io.IOException;
import java.util.Set;

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
 * Servlet Filter implementation class AddCityFilter
 */
@WebFilter("/addCity")
public class AddCityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AddCityFilter() {
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
		String name = request.getParameter("city");
		if( check(code,false) && check(name,true) ){
		
		String cid = name+"("+code+")";
		
		Set<String> setCities = (new CitiesDao()).cities();
		if(!setCities.contains(cid)){

			
			chain.doFilter(request, response);
				
			
		}else{
			session.setAttribute("sameCity",true);

			res.sendRedirect("add");
		}
		}
		else{
			session.setAttribute("incorrectCity",true);

			res.sendRedirect("add");
		}
		}
		
		catch(Exception e){
			System.out.println(e);
		}
	}
	public  boolean check(String s, boolean city){
		for(char c : s.toCharArray()){
			if((c!=32 && c<48 && city) || (c>57 && c<63) || (c>90 && c<97) || (c>122))
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
