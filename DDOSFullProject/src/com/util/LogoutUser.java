package com.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This Servlet class is for User Logout
 * 
 *
 */
@WebServlet("/LogoutUser")
public class LogoutUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
	      if (session != null) 
	      {				
	    	  
	    	  HttpServletRequest requestHttp = (HttpServletRequest) request;
				 
				String urlString = requestHttp.getRequestURL().toString();
				
				String ipAddress = null;
				 
				// get IP Address of client from request
				if(urlString.contains("localhost"))
					ipAddress = request.getLocalAddr();
				
				else
					ipAddress = request.getRemoteAddr();
	    	  
	    	  //Invalidating the session for Logout
	            session.removeAttribute("username");
	            session.removeAttribute("password");
	            session.removeAttribute("accountno");
	            session.invalidate();
	            String query = "update proxydetect set ipsuspiciouscount = '"+0+"', malicious = 'no' where ipaddress='"+ipAddress+"'";
	    		
	            DatabaseConnection db=new DatabaseConnection();
	    		int updated = db.updateQuery(query);
	      }
	      request.getRequestDispatcher("Login.jsp?msg=You are logout successfully...").forward(request,response);
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
