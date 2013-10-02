package servlets;

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

import exceptions.ServiceLocatorException;
import mail.MailSenderServiceFactory;

/**
 * Servlet Filter implementation class HeaderFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD,
		}
					, urlPatterns = { "*.jsp" })
public class HeaderFilter implements Filter {
	final Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		logger.info("HeaderFilter invoked");
		//Create cross site session hash to 
		chain.doFilter(request, response);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		
		if(req.getMethod().equalsIgnoreCase("GET")){ 
			if(!"/login.jsp".equals(req.getServletPath()) 
					&& !"/error.jsp".equals(req.getServletPath())){
				if(session.getAttribute("user_uid") == null){
					request.getRequestDispatcher("/login.jsp").forward(request,
							response);
				}
			}
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}
