package com.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.AES.AESencrp;
import com.mysql.jdbc.ResultSet;
import com.util.DatabaseConnection;

/**
 * This Servlet class is use to search the customer by their account number.
 * @author Renuka Gore
 *
 */
@WebServlet("/SearchAccount")
public class SearchAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAccount() {
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
		
		String accountno=request.getParameter("accountno"); //accessing the account number entered from user.
		DatabaseConnection db=new DatabaseConnection();	
		 
		 ResultSet rs=(ResultSet) db.selectQuery("select * from user where accountno='"+accountno+"'");
		
		
			request.getSession().setAttribute("search",rs); // Setting the user details in session.
			response.sendRedirect("SearchAccountRecords.jsp");
		
		
		
	}

}
