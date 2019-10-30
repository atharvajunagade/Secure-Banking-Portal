package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.util.DatabaseConnection;

/**
 * This Servlet class is use to Check the OTP Number.
 * 
 *
 */
@WebServlet("/SendOTPNumber")
public class SendOTPNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendOTPNumber() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{			
			String email=(String) request.getSession().getAttribute("email"); //Accessing the email from session.
			String autono=request.getParameter("no");						 //autono stores the OTP number.
			DatabaseConnection db=new DatabaseConnection();
			ResultSet rs=db.selectQuery("select * from user where email='"+email+"'");
			String message="";
			if(rs.next())
			{
				if(rs.getString("securityno").equals(autono)) //Comparing the OTP entered by user with the OTP in database.
				{
					message="Correct";
				}
				else
				{
					message="Wrong";
				}
					
			}
			PrintWriter out = response.getWriter();
			out.println(message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
