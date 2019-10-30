package com.servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.dao.SendSms;
import com.dao.Transaction;
import com.sendMail.SendMailBySite;
import com.util.DatabaseConnection;

/**
 * This Servlet class performs Bill Payment transactions. 
 * @author Renuka Gore
 *
 */
@WebServlet("/BillPayments")
public class BillPayments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillPayments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String saccountno=request.getSession().getAttribute("accountno").toString(); //Accessing the parameters from session
		int uid1=Integer.parseInt(request.getSession().getAttribute("uid").toString());
		
		String mobileno=request.getParameter("mobileno"); //Accessing the form parameters
		boolean aa=CrossSiteScript.escapeHtml(request.getParameter("mobileno"));
		
		String customerno=request.getParameter("customerno");
		boolean bb=CrossSiteScript.escapeHtml(request.getParameter("customerno"));
		
		double amount=Double.parseDouble(request.getParameter("amount"));
		boolean cc=CrossSiteScript.escapeHtml(request.getParameter("amount"));
		
		String policyno=request.getParameter("policyno");
		boolean dd=CrossSiteScript.escapeHtml(request.getParameter("policyno"));
		
		String remarks=request.getParameter("remarks");
		boolean ee=CrossSiteScript.escapeHtml(request.getParameter("remarks"));
		
		String cmb=request.getParameter("billtype");
		boolean gg=CrossSiteScript.escapeHtml(request.getParameter("billtype"));
		
		DatabaseConnection db=new DatabaseConnection();
		ResultSet rs=db.selectQuery("select * from user where accountno='"+saccountno+"'"); //Fetching the current balance.
		
		String email = "";
		
		double balance=0;
		try
		{
		if(rs.next())
		{
			balance=rs.getDouble("balance");
			
			email = AESencrp.decrypt(rs.getString("email"));
			
		}
		
		System.out.println("\n\n");
		
		System.out.println("amount : "+amount);
		
		System.out.println("\n\n");
		
		System.out.println("balance : "+balance);
		
		System.out.println("\n\n");
		
		if(!(amount > balance))
		{
		
		System.out.println(cmb.equals("1"));
		System.out.println(cmb.equals("2"));
		
		if((aa==true)|| (bb==true) || (cc==true) || (dd==true) || (ee==true) || (gg==true))
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
			System.out.println("Cross Site Scripting Attack");
			
			DatabaseConnection dbs=new DatabaseConnection();
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //This is for current date and time.
			Calendar calobj = Calendar.getInstance();
			String dt=dateFormat.format(calobj.getTime());
			dbs.updateQuery("insert into ebank.admin(logs,time,ipAddress) values('" + str+ "','" + dt + "','"+ipAddress+"' )");
			response.sendRedirect("BillPayment.jsp");
		}
		
		else if(cmb.equals("1"))		//This is for Mobile Recharge transaction.
		{
			
			if(!(amount > 500.00))
			{
			
			balance=(balance-amount);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar calobj = Calendar.getInstance();
			String dt=dateFormat.format(calobj.getTime());
			
			int a=db.updateQuery("update user,transactions set user.balance='"+balance+"' where accountno='"+saccountno+"'");
			String str="insert into ebank.transactions(tuid,datetime,sender_account,remarks,amount,final_balance_sender,mobile_number) values('"+uid1+"','"+dt+"','"+saccountno+"','"+remarks+"','"+amount+"','"+balance+"','"+mobileno+"')";
			System.out.println(str);
			int b=db.updateQuery(str);
			System.out.println("Mobile recharge Sucess");
			
			HttpSession session = request.getSession();
			String mob=(String)AESencrp.decrypt((String) session.getAttribute("contact"));
			
			String msg1="Mobile Recharge Successfully Done!!!!!!! <br/> Recharge Amount : "+amount;
			
			SendMailBySite.sendMail(email, msg1);     //Sending the recharge information to user by Email.
			
			int autono=SendSms.gen();
			System.out.println("Reg Mobile no:"+mob);
			String msg="Mobile Recharge Successfully Done!!!!!!!"+autono;				
			SendSms.SendSmsToNumber("atulchikane", "8336320", mob, msg, "WEBSMS", "0"); // This is for message sending.	
			
			response.sendRedirect("MobileRechargeMessage.jsp");
			
			
			}
			else
			{
				
				StringBuffer sb = new StringBuffer("");
				  
				
				  
				sb.append("BillPayment.jsp");
				sb.append("?message=You_cannot_do_Mobile_Recharge_above_Rs.500");
				
				  
				  
				String url = sb.toString();
				  
				String urlEncoded = response.encodeRedirectURL(url) ;
				  
				response.sendRedirect(urlEncoded);	
				
			}
			
		}
		
		else if(cmb.equals("2")) //This is for Mobile Electricity Bill transaction.
		{
			balance=(balance-amount);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar calobj = Calendar.getInstance();
			String dt=dateFormat.format(calobj.getTime());
			
			int a=db.updateQuery("update user,transactions set user.balance='"+balance+"' where accountno='"+saccountno+"'");
			int b=db.updateQuery("insert into ebank.transactions(tuid,datetime,sender_account,remarks,amount,final_balance_sender,customer_no)values('"+uid1+"','"+dt+"','"+saccountno+"','"+remarks+"','"+amount+"','"+balance+"','"+customerno+"')");
			System.out.println("Electricity Bill Successfully paid");
			
			HttpSession session = request.getSession();
			String mob=(String)AESencrp.decrypt((String) session.getAttribute("contact"));
			
			String msg1="Electricity Bill Successfully paid!!!!!!! <br/> Bill Amount : "+amount;
			
			SendMailBySite.sendMail(email, msg1);     //Sending the recharge information to user by Email.
			
			int autono=SendSms.gen();	
			System.out.println("Reg Mobile no:"+mob);
			String msg="Electricity Bill Successfully paid!!!!!!!"+autono;				
			SendSms.SendSmsToNumber("atulchikane", "8336320", mob, msg, "WEBSMS", "0"); // This is for message sending.	
			
			response.sendRedirect("BillPaymentMessage.jsp");
		}
		
		else if(cmb.equals("3")) //This is for Insurance Tax transaction.
		{
			balance=(balance-amount);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar calobj = Calendar.getInstance();
			String dt=dateFormat.format(calobj.getTime());
			
			int a=db.updateQuery("update user,transactions set user.balance='"+balance+"' where accountno='"+saccountno+"'");
			int b=db.updateQuery("insert into ebank.transactions(tuid,datetime,sender_account,remarks,amount,final_balance_sender,policy_no)values('"+uid1+"','"+dt+"','"+saccountno+"','"+remarks+"','"+amount+"','"+balance+"','"+policyno+"')");
			System.out.println("Insurance paid sucesfully");
			
			HttpSession session = request.getSession();
			
			String msg1="Insurance is sucesfully paid!!!!!!! <br/> Insurance Amount : "+amount;
			
			SendMailBySite.sendMail(email, msg1);     //Sending the recharge information to user by Email.
			
			int autono=SendSms.gen();
			String mob=(String)AESencrp.decrypt((String) session.getAttribute("contact"));
			System.out.println("Reg Mobile no:"+mob);
			String msg="Insurance is sucesfully paid!!!!!!!"+autono;				
			SendSms.SendSmsToNumber("atulchikane", "8336320", mob, msg, "WEBSMS", "0"); // This is for message sending.	
			
			response.sendRedirect("InsuranceTax.jsp");
		}
		}
		else
		{
			
			StringBuffer sb = new StringBuffer("");
			  
			
			  
			sb.append("BillPayment.jsp");
			sb.append("?message=You_do_not_have_sufficient_balance_in_your_account");
			
			  
			  
			String url = sb.toString();
			  
			String urlEncoded = response.encodeRedirectURL(url) ;
			  
			response.sendRedirect(urlEncoded);	
			
			
			
		}
}
		
		catch(Exception e)
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
