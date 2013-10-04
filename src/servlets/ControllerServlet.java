package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auctions.AuctionMonitorPool;
import auctions.AuctionMonitorPoolFactory;

import com.google.gson.Gson;

import webactions.WebActionAjax;
import webactions.WebActionFactory;
import webactions.WebActionGP;
import mail.MailSenderServiceFactory;
import exceptions.ServiceLocatorException;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name="ControllerServlet",urlPatterns={"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final Logger logger = Logger.getLogger(this.getClass().getName());
	
	private AuctionMonitorPool auctionMonitor;
	private Thread monitorThread;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	
    	// start the auction monitor pools
		auctionMonitor = AuctionMonitorPoolFactory.getAuctionMonitorService();
		monitorThread = new Thread(auctionMonitor);
		monitorThread.setDaemon(true);
		monitorThread.start();
        
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
		HttpSession session = request.getSession(true);
		
		if(request.getParameter("action") != null){
			// TODO - what should the default be => 
			// index.jsp with a jsp guard that redirects to login.jsp
			String forwardPage = "controller?action=searchAuction";
			
			String action = request.getParameter("action");
			
			
			WebActionGP webAction = WebActionFactory.getGPAction(action);
			if (webAction != null) {
				forwardPage = webAction.executeAction(request, response, logger);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("ControllerServlet: doPost() invoked");
		
		PrintWriter out = response.getWriter();
        
		response.setContentType("application/json");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        
        if(request.getParameter("ajax") != null){
			
			Map<String, Object> returnData = null;
			WebActionAjax webActionAjax = WebActionFactory.getAjaxAction(request.getParameter("ajax"));
			if (webActionAjax != null) {
				returnData = webActionAjax.executeAjax(request, response, logger);
			}
			
			String jsonResponse = new Gson().toJson(returnData);

			//TODO: remove the below debug statement
			System.out.println(jsonResponse);
			
			out.write(jsonResponse);
			out.flush();
			out.close();
			    
		}else if(request.getParameter("action") != null){
			// TODO - what should the default be => 
			// index.jsp with a jsp guard that redirects to login.jsp
			String forwardPage = "index.jsp";
			
			String action = request.getParameter("action");
			WebActionGP webAction = WebActionFactory.getGPAction(action);
			if (webAction != null) {
				forwardPage = webAction.executeAction(request, response, logger);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		}
	}
}
