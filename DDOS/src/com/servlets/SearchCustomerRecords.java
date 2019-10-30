package com.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.AES.AESencrp;
import com.mysql.jdbc.ResultSet;
import com.util.DatabaseConnection;

/**
 * This Servlet class is use to search customer by their names. 
 * @author Renuka Gore
 *
 */
@WebServlet("/SearchCustomerRecords")
public class SearchCustomerRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCustomerRecords() {
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
		
		ResultSet rs;
		
		String name=AESencrp.encrypt(request.getParameter("fullname")); //Accessing the customer name entered by user.
		DatabaseConnection db=new DatabaseConnection();
		
		
		rs=(ResultSet) db.selectQuery("select * from user where fullname='"+name+"'");
		request.getSession().setAttribute("searchC",rs); 				//Setting the customer details in session.
		response.sendRedirect("SearchCustomerRecords.jsp");
	}

}
