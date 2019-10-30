package com.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.util.DatabaseConnection;
import com.AES.AESencrp;
import com.dao.SendSms;
import com.sendMail.SendMailBySite;

/**
 * This Servlet class is use to send the message with OTP number to user. 
 * @author Renuka Gore
 *
 */
@WebServlet("/SendSMSServlet")
public class SendSMSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendSMSServlet() {
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
			String mob=request.getParameter("mobile");	//Accessing the form parameters.
			String email=request.getParameter("email");
			DatabaseConnection db=new DatabaseConnection();
			String query="select * from user where email='"+AESencrp.encrypt(email)+"' and contact='"+AESencrp.encrypt(mob)+"'";
			ResultSet rs = db.selectQuery(query);
			
            if(rs.next())
            {
				int autono=SendSms.gen(); 				//This is for OTP generation.
				
				String emailMessage = "E-Banking Website Notification : <br/> <br/> Your Email : "+email + "<br/> <br/> Your OTP : "+autono;
				
				String result = SendMailBySite.sendMail(email, emailMessage);
				
				System.out.println("Email "+result);
				
				
				String msg="Kind Attention!!! This is the code for your security purpose \n " +autono;
				SendSms.SendSmsToNumber("atulchikane", "8336320", mob, msg, "WEBSMS", "0"); // This is for message sending.
				query="update user set securityno='"+autono+"' where email='"+AESencrp.encrypt(email)+"' ";
				db.updateQuery(query);
				System.out.println("Send sms servlet update: "+query);
				response.sendRedirect("OTPNumber.jsp?email="+email);
            }
            else
            {
				response.sendRedirect("OTP.jsp?email="+email+"&msg=wrong mobile number");
            }
		}
		catch(Exception e){e.printStackTrace();  	}
	}

}
