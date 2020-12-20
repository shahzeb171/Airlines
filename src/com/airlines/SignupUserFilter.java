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
 * Servlet Filter implementation class SignupUserFilter
 */
@WebFilter("/signupUser")
public class SignupUserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SignupUserFilter() {
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
		HttpServletResponse res = (HttpServletResponse) response;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		if(check(username) && check(password) && check(name))
			chain.doFilter(request, response);
		else{

			HttpSession session = ((HttpServletRequest)request).getSession();
			session.setAttribute("aFiltered",true);
			res.sendRedirect("signupUser.jsp");
		}
			
	}
		public  boolean check(String s){
			for(char c : s.toCharArray()){
				if(c<48 || (c>57 && c<63) || (c>90 && c<97) || (c>122))
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
