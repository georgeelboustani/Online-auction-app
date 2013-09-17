package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HeaderFilter implements Filter {
	final Logger logger = Logger.getLogger(this.getClass().getName());
	private ArrayList<String> urlList;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info("HeaderFilter invoked");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String url = req.getServletPath();
		boolean allowedRequest = false;
        
        if(urlList.contains(url)) {
            allowedRequest = true;
        }
             
        if (!allowedRequest) {
            HttpSession session = req.getSession(false);
            if (null == session) {
                res.sendRedirect("index.jsp");
            }
        }
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		String urls = fConfig.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");
        
        urlList = new ArrayList<String>();
        
        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
        }
	}
}
