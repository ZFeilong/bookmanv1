package cn.edu.nyist.bookmanv1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//你的重复代码放置到这里
           if(req.getSession().getAttribute("loginSuccess")==null||!req.getSession().getAttribute("loginSuccess").equals("1")) {
			
			resp.sendRedirect("login.jsp");
			return;
           }else {//登录后要浏览器继续走
        	   chain.doFilter(request, response);
           }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
