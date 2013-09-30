package servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import model.ForLogin;

import com.google.gson.Gson;

import webactions.WebAction;
import webactions.WebActionFactory;

=======
import mail.MailSenderServiceFactory;
/*import org.json.simple.JSONObject;
import org.json.JSONStringer;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
*/
import exceptions.ServiceLocatorException;
>>>>>>> 9d0946c4b467a4f1c9e3dccbdaac106dcf9e6f48

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(this.getClass().getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        
        try {
			MailSenderServiceFactory.init();
		} catch (ServiceLocatorException e) {
			// TODO - what happens if email service can't be initialised
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("ControllerServlet: doGet() invoked");
<<<<<<< HEAD
				
=======
		
>>>>>>> 9d0946c4b467a4f1c9e3dccbdaac106dcf9e6f48
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
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("ControllerServlet: doPost() invoked");
		HttpSession session = request.getSession(true);
		
<<<<<<< HEAD
		if(request.getParameter("ajax") != null){
			
			String forwardPage = null;
			WebAction webAction = WebActionFactory.getAction(request.getParameter("ajax"));
			if (webAction != null) {
				forwardPage = webAction.execute(request, response, logger);
=======
		if(session.getAttribute("user") == null){
//			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//			rd.forward(request, response);
		}
		
		if(request.getParameter("callback") != null){
			if(request.getParameter("callback").equals("login")){
				
				/*
				try{
					JSONObject dataObj = (JSONObject) new JSONParser().parse(request.getParameter("data"));
					String email = dataObj.get("signinEmail").toString();
					String password = dataObj.get("signinPassword").toString();
					String rememberMe = dataObj.get("signinRememberme").toString();
					System.out.println(email + ", " + password + ", " + rememberMe);
				}catch(ParseException pe){
					pe.printStackTrace();
				}
				
				WebAction webAction = WebActionFactory.getAction("");
				if (webAction != null) {
					forwardPage = webAction.execute(request, response, logger);
				}
				*/
>>>>>>> 9d0946c4b467a4f1c9e3dccbdaac106dcf9e6f48
			}
			
			System.out.println("Where i go: " + forwardPage);

		}else if(request.getParameter("action") != null){
			
		}
	}
}
