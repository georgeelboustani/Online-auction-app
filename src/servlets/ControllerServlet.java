package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.ServiceLocatorException;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doGet invoked");
		
		try {
			Connection con = DBConnectionFactory.getConnection();
			logger.info("DB CATALOG = " + con.getCatalog());
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession(true);
		
		if(request.getParameter("action") != null){
			String action = request.getParameter("action");
			if(action.equals("login")){
				logger.info("Doing Login");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	        	dispatcher.forward(request, response);
			}
		}else if(request.getParameter("callback") != null){
			//callback for ajax
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doPost invoked");
		
		HttpSession session = request.getSession(true);
       
		if(request.getParameter("action") != null){
			//callback for url-parsing
		}else if(request.getParameter("callback") != null){
			if(request.getParameter("callback").contains("")){
				
			}
		}
	}

}
