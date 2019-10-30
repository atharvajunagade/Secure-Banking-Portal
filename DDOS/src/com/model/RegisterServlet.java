package com.model;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.AES.AESencrp;
import com.dao.CrossSiteScript;
import com.dao.Transaction;
import com.mysql.jdbc.PreparedStatement;
import com.password.Password;
import com.sendMail.SendMailBySite;
import com.util.DatabaseConnection;

/**
 * This Servlet class is use for User Registration. 
 * @author Renuka Gore
 *
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		
		String fullName = AESencrp.encrypt(request.getParameter("fullName")); 		//Accessing the form parameters.
		boolean a=CrossSiteScript.escapeHtml(request.getParameter("fullName")); 	//This is for Cross site script attack.
		
		String father = AESencrp.encrypt(request.getParameter("father"));
		boolean b=CrossSiteScript.escapeHtml(request.getParameter("father"));
		
		String dob = AESencrp.encrypt(request.getParameter("dob"));
		boolean c=CrossSiteScript.escapeHtml(request.getParameter("dob"));
		
		String pan = AESencrp.encrypt(request.getParameter("pan"));
		boolean d=CrossSiteScript.escapeHtml(request.getParameter("pan"));
		
		String aadhar = AESencrp.encrypt(request.getParameter("aadhar"));
		boolean e=CrossSiteScript.escapeHtml(request.getParameter("aadhar"));
		
		String email = (request.getParameter("email"));
		boolean f=CrossSiteScript.escapeHtml(request.getParameter("email"));
		
		String country = AESencrp.encrypt(request.getParameter("country"));
		boolean g=CrossSiteScript.escapeHtml(request.getParameter("country"));
		
		Double initBal=Double.parseDouble(request.getParameter("initBal"));
		boolean h=CrossSiteScript.escapeHtml(request.getParameter("initBal").toString());
		
		String contact = AESencrp.encrypt(request.getParameter("contact"));
		boolean i=CrossSiteScript.escapeHtml(request.getParameter("contact").toString());
		
		System.out.print(a+" "+b+" "+c+" "+d+" "+e+" "+f+" "+g+" "+h+" "+i);
		
		//This condition checks the cross site attack
		if((a==true)|| (b==true) || (c==true) || (d==true) || (e==true) || (f==true) || (g==true) || (h==true) || (i==true))
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
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //This is for current date and time.
			Calendar calobj = Calendar.getInstance();
			String dt=dateFormat.format(calobj.getTime());
			db.updateQuery("insert into admin(logs,time,ipaddress)values('"+str+"','"+dt+"','"+ipAddress+"')");
			response.sendRedirect("Registration.jsp");
		}
		else
		{
			
		String password = null;
		String pin = null;
		int acno = 10000;
		HttpSession session = request.getSession();
		String flag = "User alreadry exists";
		
		DatabaseConnection db = new DatabaseConnection();  
		ResultSet rs = db.selectQuery("select * from user where email='"
				+ AESencrp.encrypt(email) + "'");
		try {
			
			if ( !rs.next()) 
			{
				ResultSet rs2 = db.selectQuery("SELECT MAX(accountno)  FROM user;");	
				if (rs2.next()) 
				{
					if(rs2.getInt("MAX(accountno)")!=0){  // Generating the Account Number
					acno =  rs2.getInt("MAX(accountno)") ;
					System.out.println("acno1 :: "+acno);
					acno = acno + 1;
					System.out.println("acno :: "+acno);}
				}
				
				String pwd = Password.password(email);
				
			//	String emailMessage = "E-Banking Website Notification : <br/> <br/> Your Email : "+email + "<br/> <br/> Your Password : "+pwd;
					
			//	String result = SendMailBySite.sendMail(email, emailMessage);
				
				//System.out.println("Email "+result);
				
				password = AESencrp.encrypt(pwd);
					email=AESencrp.encrypt(email);
					pin = AESencrp.encrypt((int) ((Math.random() * 9000) + 1000)  +"");
					System.out.println("pin "+pin);
					db.updateQuery("insert user(fullname,father,email,country,contact,username,password,dob,pan,aadhar,accountno,pin,balance) values('"+ fullName+ "','"+ father+ "','"+ email+ "','"+ country+ "','"+ contact+ "','"+ email+ "','"+ password+ "','"+ dob+ "','"+ pan+ "','"+ aadhar+ "','" + acno + "','" +pin  + "','"+initBal+"')");
					flag = "You have successfully applied,Login details will be sent to your registrered email after verification";
					session.setAttribute("regStatus", flag);
			}
		} 
		
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
					session.setAttribute("regStatus", flag);
					response.sendRedirect("Login.jsp");
	}
	}
}
