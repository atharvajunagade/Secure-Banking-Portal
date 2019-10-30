package com.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.AES.AESencrp;
import com.dao.CrossSiteScript;
import com.util.DatabaseConnection;

/**
 * This Servlet class is use to update user profile. 
 * @author Renuka Gore
 *
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fullname=AESencrp.encrypt(request.getParameter("fullname")); 		//Accessing form parameters with decryption.
		boolean aa=CrossSiteScript.escapeHtml(request.getParameter("fullname"));
		System.out.println("New Name:"+AESencrp.decrypt("fullname"));
		
		String contact=AESencrp.encrypt(request.getParameter("contact"));
		boolean bb=CrossSiteScript.escapeHtml(request.getParameter("contact"));
		System.out.println("New Number:"+contact);
		
		String country=AESencrp.encrypt(request.getParameter("country"));
		boolean cc=CrossSiteScript.escapeHtml(request.getParameter("country"));
		System.out.println("New Country:"+country);
		
		String email=AESencrp.encrypt(request.getParameter("email"));
		boolean dd=CrossSiteScript.escapeHtml(request.getParameter("email"));
		System.out.println("New Email:"+email);
		
		if((aa==true)|| (bb==true) || (cc==true) || (dd==true))
		{
			HttpServletRequest requestHttp = (HttpServletRequest) request;
			 
			String urlString = requestHttp.getRequestURL().toString();
			
			String ipAddress = null;
			 
			// get IP Address of client from request
			if(urlString.contains("localhost"))
				ipAddress = request.getLocalAddr();
			
			else
				ipAddress = request.getRemoteAddr();
			
			
			DatabaseConnection db=new DatabaseConnection();
			String str="Cross Site Scripting Attack";
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	//This is for current date and time.
			Calendar calobj = Calendar.getInstance();
			String dt=dateFormat.format(calobj.getTime());
			db.updateQuery("insert into admin(logs,time,ipaddress)values('"+str+"','"+dt+"','"+ipAddress+"')");
			response.sendRedirect("UpdateProfile.jsp");
		}
		else
		{
			DatabaseConnection dbs=new DatabaseConnection();
			int uid=Integer.parseInt(request.getSession().getAttribute("uid").toString()); //uid stores the user id
			int a=dbs.updateQuery("update user set fullname='"+fullname+"',contact='"+contact+"',country='"+country+"',email='"+email+"' where uid='"+uid+"' ");
			response.sendRedirect("UpdateProfileMessage.jsp");
			System.out.println(a);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
