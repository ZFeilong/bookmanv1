package cn.edu.nyist.bookmanv1.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class TimerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TimerFilter() {
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
		System.out.println("timer start++++++++++++++");
		HttpServletRequest req=(HttpServletRequest)request;
		long startTime=System.nanoTime();
		chain.doFilter(request, response);
		long endTime=System.nanoTime();
		System.out.println(req.getRequestURI()+"÷¥––"+(endTime-startTime)+"ƒ…√Î");
		System.out.println("timer end++++++++++++++");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
