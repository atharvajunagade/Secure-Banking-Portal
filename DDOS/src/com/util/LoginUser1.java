package com.util;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.AES.AESencrp;
import com.dao.CrossSiteScript;
import com.dao.SendSms;

/**
 * This Servlet class performs User Login function,SQL Injection,Brute Force
 * Attack detection and prevention on our system
 * 
 * @author Renuka Gore
 * 
 */

@WebServlet("/LoginUser1")
public class LoginUser1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUser1() {
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
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) Read inputs from User and check whether they are correct
	 *      or not and redirect to home page if correct
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("In Login User");
		HttpSession session = request.getSession();
		String username = null;
		String password = null;
		username = request.getParameter("username").toString();
		password = request.getParameter("password").toString();
		System.out.println("username :: " + username);
		System.out.println("password :: " + password);
		
		boolean usernamcheck=CrossSiteScript.escapeHtml(request.getParameter("username"));
		boolean passwordcheck=CrossSiteScript.escapeHtml(request.getParameter("password"));
		System.out.println("Cross Scripting check "+usernamcheck+"\t"+passwordcheck);

		DatabaseConnection db = new DatabaseConnection();
		if ((username.contains(" and ") || password.contains(" and ")) 			// This condition checks the sql injection parameters.
				||(username.contains(" or ") || password.contains(" or ")) 			// This condition checks the sql injection parameters.
				|| (username.contains(" update ") || password.contains(" update "))
				|| (username.contains(" delete ") || password.contains(" delete "))
				|| (username.contains(" insert ") || password.contains(" insert "))) 
		{
			
			HttpServletRequest requestHttp = (HttpServletRequest) request;
			 
			String urlString = requestHttp.getRequestURL().toString();
			
			String ipAddress = null;
			 
			// get IP Address of client from request
						if(urlString.contains("localhost"))
							ipAddress = request.getLocalAddr();
						
						else
							ipAddress = request.getRemoteAddr();
			
			System.out.println("Sql injection found");
			String str = "sql injection";
			System.out.println("str :: " + str);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // This gives the current Date and Time
			Calendar calobj = Calendar.getInstance();
			String dt = dateFormat.format(calobj.getTime());
			db.updateQuery("insert into ebank.admin(logs,time,ipAddress) values('" + str
					+ "','" + dt + "','"+ipAddress+"' )");
			response.sendRedirect("Login.jsp");
		}
		
		else 	if ( (usernamcheck ==true) || (passwordcheck==true) ) 
		{
			
			HttpServletRequest requestHttp = (HttpServletRequest) request;
			 
			String urlString = requestHttp.getRequestURL().toString();
			
			String ipAddress = null;
			 
			// get IP Address of client from request
						if(urlString.contains("localhost"))
							ipAddress = request.getLocalAddr();
						
						else
							ipAddress = request.getRemoteAddr();
			
			System.out.println("Corss Script injection found");
			String str = "cross script injection";
			System.out.println("str :: " + str);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // This gives the current Date and Time
			Calendar calobj = Calendar.getInstance();
			String dt = dateFormat.format(calobj.getTime());
			db.updateQuery("insert into ebank.admin(logs,time,ipAddress) values('" + str
					+ "','" + dt + "','"+ipAddress+"' )");
			response.sendRedirect("Login.jsp");
		}
		
		
		else if (username.equals("admin") & password.equals("admin")) 
		{ 		// This is for Admin Login
			System.out.println("In admin block");
			response.sendRedirect("Admin.jsp");
		}
		
		else if (!username.equals("admin")) 
		{  									// This is for User Login
			System.out.println("in else if !admin");
			ResultSet rs1 = db
					.selectQuery("select blocked from user where username='"
							+ username + "'");
			String dtime = "";
			try {
				if (rs1.next()) {
					dtime = rs1.getString("blocked");
				}
				DateFormat dateFormat1 = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Calendar calobj1 = Calendar.getInstance();
				String dt2 = dateFormat1.format(calobj1.getTime());
				if (!dtime.equals(dt2)) {
					username = AESencrp.encrypt(username);
					System.out.println("username :: " + username);
					password = AESencrp.encrypt(password);
					System.out.println("password :: " + password);
					ResultSet rs = db
							.selectQuery("select * from user where username='"
									+ username + "' and password='" + password
									+ "'");
					

					if (rs.next()) {
						String active = request.getParameter("remember"); 	// This is for Forgot Password
						if ("ON".equals(active)) {
							Cookie c = new Cookie("username",
									username.toString());
							c.setMaxAge(24 * 60 * 60);
							response.addCookie(c);						 	// response is an instance of type HttpServletReponse
							Cookie c1 = new Cookie("password",
									password.toString());
							c1.setMaxAge(24 * 60 * 60);
							response.addCookie(c1); 						// response is an instance of type HttpServletReponse
						}

						session.setAttribute("uid", rs.getInt("uid")); 		// Setting the values in session
						session.setAttribute("accountno",
								rs.getInt("accountno"));
						session.setAttribute("pin", rs.getString("pin"));
						session.setAttribute("username",
								rs.getString("username"));
						session.setAttribute("password",
								rs.getString("password"));
						session.setAttribute("contact", rs.getString("contact"));
						session.setAttribute("email", rs.getString("email"));
						System.out.println(session.getAttribute("uid")
								.toString());
						
						HttpSession s = request.getSession();
						String mob=(String)AESencrp.decrypt((String) s.getAttribute("contact"));
						int autono=SendSms.gen();
						System.out.println("Reg Mobile no:::::"+mob);
						String msg="You are successfully loged in"+autono;				
						SendSms.SendSmsToNumber("atulchikane", "8336320", mob, msg, "WEBSMS", "0");
						
						response.sendRedirect("Home1.jsp");
					} else {
						if (count == 5) 										// This is for Brute Force Attack
						{
							
							HttpServletRequest requestHttp = (HttpServletRequest) request;
							 
							String urlString = requestHttp.getRequestURL().toString();
							
							String ipAddress = null;
							 
							// get IP Address of client from request
							if(urlString.contains("localhost"))
								ipAddress = request.getLocalAddr();
							
							else
								ipAddress = request.getRemoteAddr();
							
							
							String str = "Brute Force Attack";
							DateFormat dateFormat = new SimpleDateFormat(
									"yyyy/MM/dd HH:mm:ss");
							Calendar calobj = Calendar.getInstance();
							String dt1 = dateFormat.format(calobj.getTime());
							int a = db
									.updateQuery("insert into ebank.admin(logs,time,ipAddress) values('"
											+ str + "','" + dt1 + "','"+ipAddress+",)");
							
							int b = db.updateQuery(" update user set blocked='"
									+ dt1 + "' where username='" + username
									+ "'");
							response.sendRedirect("LoginBlock.jsp");
							System.out.println(b);
						} 
						else {

							count++;
							System.out.println("count::" + count);
							HttpSession s = request.getSession();
							System.out.print("In User else part");
							s.setAttribute("error",
									"Username or password wrong");
							response.sendRedirect("Login.jsp");
						}
					}
				} else {
					response.sendRedirect("LoginBlock.jsp");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		else 
		{
			HttpSession s = request.getSession();
			System.out.println("in else");
			s.setAttribute("error", "Username or password wrong");
			System.out.println("fdgfdfg :: " + session.getAttribute("error"));

			response.sendRedirect("Login.jsp");

		}
	}
}
