package com.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.AES.AESencrp;
import com.util.DatabaseConnection;

/**
 * This Servlet class is use to check email for sending OTP 
 * @author Renuka Gore
 *
 */
@WebServlet("/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email"); 				//Accesing the email entered from user
		DatabaseConnection db=new DatabaseConnection();
		ResultSet rs=db.selectQuery("select * from user where email='"+AESencrp.encrypt(email)+"'");
		try {
			if(rs.next())
			{
				response.sendRedirect("OTP.jsp?email="+email);
			}
			else
			{
				response.sendRedirect("Email.jsp?msg=This email id is not registered with us");
			}
}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(rs!=null)
				{
					rs.close();
					
				}
				
				DatabaseConnection.closeConnection();
			} 
			catch(SQLException e)
			{

				e.printStackTrace();
			}
		}
		
	}

}
