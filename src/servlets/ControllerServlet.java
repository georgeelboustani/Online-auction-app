package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.tools.JavaFileObject;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

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
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user") == null){
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
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
		System.out.println(request.getParameter("data"));
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("user") == null){
//			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//			rd.forward(request, response);
		}
		
		if(request.getParameter("callback") != null){
			if(request.getParameter("callback").equals("login")){
				
				try{
					JSONObject dataObj = (JSONObject) new JSONParser().parse(request.getParameter("data"));
					String email = dataObj.get("signinEmail").toString();
					String password = dataObj.get("signinPassword").toString();
					String rememberMe = dataObj.get("signinRememberme").toString;
					System.out.println(email + ", " + password + ", " + rememberMe);
					
				}catch(ParseException pe){
					pe.printStackTrace();
				}
				
				WebAction webAction = WebActionFactory.getAction("");
				if (webAction != null) {
					forwardPage = webAction.execute(request, response, logger);
				}
			}
		}
	}
}
