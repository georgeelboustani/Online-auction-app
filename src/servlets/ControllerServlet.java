package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webactions.WebAction;
import webactions.WebActionFactory;
import exceptions.ServiceLocatorException;
import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;
import jdbc.DBConnectionFactory;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name="ControllerServlet",urlPatterns={"/ControllerServlet","/","/home"})
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(this.getClass().getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("ControllerServlet: doGet() invoked");
		
		
		if(request.getParameter("action") != null){
			// TODO - what should the default be => 
			// index.jsp with a jsp guard that redirects to login.jsp
			String forwardPage = "index.jsp";
			String action = request.getParameter("action");
			
			if(action.equals("login")){
				forwardPage = "login.jsp";
			}else if(action.equals("home")){
				forwardPage = "index.jsp";
			}
			
			// TODO - why when i uncomment the forward, shit breaks??
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		}else if(request.getParameter("callback") != null){
			//callback for ajax
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("ControllerServlet: doPost() invoked");
		
		HttpSession session = request.getSession(true);
       
		if(request.getParameter("action") != null){
			//callback for url-parsing
		}else if(request.getParameter("callback") != null){
			if(request.getParameter("callback").contains("login")){
				
				WebAction webAction = WebActionFactory.getAction(action);
				if (webAction != null) {
//					forwardPage = webAction.execute(request, response, logger);
				}
			}
		}
	}
	
	private static void printUserSession(){
		
	}

}
