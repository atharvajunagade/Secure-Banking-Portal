package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AES.AESencrp;
import com.sendMail.SendMailBySite;
import com.util.DatabaseConnection;

/**
 * This Servlet class performs Approve or Reject operation on application by Admin 
 * @author Renuka Gore
 *
 */
@WebServlet("/ApproveServlet")
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			String AuserName=request.getParameter("aemail"); //Accessing the form parameters.
			System.out.println(AuserName);
			String RuserName=request.getParameter("remail");
			System.out.println("Rejected user:::::::::"+RuserName);
	 		String pwd=request.getParameter("pwd");
	 		System.out.println(pwd);
			String pin=request.getParameter("pin");
			System.out.println(pin);
			String uid=request.getParameter("uid");
	    	System.out.println("uid in approve:"+uid);
    	  	
        if(AuserName!=null)
        {
        	String msg="User Name :: "+AuserName+" ,Password :: "+pwd+" , And Pin :: "+pin;   //Generating the Query String       	
        	
        	DatabaseConnection db=new DatabaseConnection();
        	String urls="Ebanking/AccountDetails.jsp?uid=";
        	String c=urls+uid;
        
        	System.out.print("concatneted string="+c);
        	db.updateQuery("update user set urldetails='"+c+"'where uid='"+uid+"' ");
        	
        	SendMailBySite.sendMail(AuserName, msg);     //Sending the username,password and Pin number to user by mail.
        	response.sendRedirect("AdminApproval.jsp");
        	String st="approve";
        	db.updateQuery("update user set status='"+st+"' where uid='"+uid+"'");
        	
        	DatabaseConnection.closeConnection();
        }
        if(RuserName!=null)
        {	//This is for Rejection of the Application.
        	System.out.println("RuserName -> "+RuserName);
        	DatabaseConnection db=new DatabaseConnection();
        	String st1 = "reject";
        	String RejectUname = AESencrp.encrypt(RuserName).toString();
        	System.out.println("RuserName AES -> "+RejectUname);
        	System.out.println(db.updateQuery("update user set status='"+st1+"' where user.email='"+RejectUname+"'"));
        	
        	DatabaseConnection.closeConnection();
        	
        	//db.updateQuery("delete from user where email='"+RuserName+"'");
        	response.sendRedirect("AdminReject.jsp");
        	
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
