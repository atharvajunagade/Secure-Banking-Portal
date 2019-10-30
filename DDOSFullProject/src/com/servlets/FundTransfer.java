package com.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.AES.AESencrp;
import com.dao.CrossSiteScript;
import com.dao.SendSms;
import com.util.DatabaseConnection;

/**
 * This Servlet class performs the Fund Transfer Operation and Automicity
 *
 *
 */
@WebServlet("/FundTransfer")
public class FundTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundTransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	{
		// TODO Auto-generated method stub	
		String transPin=AESencrp.decrypt(request.getSession().getAttribute("pin").toString()); //Accessing the parameters from session.
		int uid1=Integer.parseInt(request.getSession().getAttribute("uid").toString());
		String saccountno=request.getSession().getAttribute("accountno").toString(); 
		
		String raccountno=request.getParameter("raccountno"); 						//Accessing the form parameters.
		boolean a=CrossSiteScript.escapeHtml(request.getParameter("raccountno"));
		
		String benificiaryName=request.getParameter("benificiary_name");
		boolean b=CrossSiteScript.escapeHtml(request.getParameter("benificiary_name"));
		
		double transAmt=Double.parseDouble(request.getParameter("transAmount"));
		boolean c=CrossSiteScript.escapeHtml(request.getParameter("transAmount"));
		
		String remarks=request.getParameter("remarks");
		boolean d=CrossSiteScript.escapeHtml(request.getParameter("transAmount"));
		
		String pin=request.getParameter("pin");
		boolean g=CrossSiteScript.escapeHtml(request.getParameter("transAmount"));
		
		ResultSet rs1 = null;
		
		ResultSet rs2 = null;
		
		ResultSet rs11 = null;
		
		System.out.print(a+" "+b+" "+c+" "+d+" "+g);	
		
		
	
		if((a==true)|| (b==true) || (c==true) || (d==true) || (g==true))
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
			try {
				response.sendRedirect("FundTransfer.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
		int result=0;
		try
		{
			DatabaseConnection db=new DatabaseConnection();
			double rBalance=0,sBalance=0;	// This is Sender and Receiver balance
			rs1=db.selectQuery("select balance from user where accountno='"+saccountno+"'"); //Fetching the Sender and Receivers balance from database.
			rs2=db.selectQuery("select balance from user where accountno='"+raccountno+"'");

			if(rs1.next())
			{
				sBalance=rs1.getDouble("balance");

			}
			if(rs2.next())
			{
				rBalance=rs2.getDouble("balance");
			}
			
			
			if(sBalance>transAmt && pin.equals(transPin))
			{
				
			

			String uuid = Calendar.getInstance().getTimeInMillis()+UUID.randomUUID().toString();	 //This is user id
			
			db.updateQuery("insert into temp_transactions values ('"+uuid+"','"+saccountno+"','"+raccountno+"',"+sBalance+","+rBalance+")"); //Inserting data in temporary created table.

			sBalance=sBalance-transAmt; 				//This id for debit transaction.
			String str="Debited";
			System.out.println("sender balance:"+sBalance);
			db.updateQuery("update user set balance='"+sBalance+"' where accountno='"+saccountno+"'");

			rBalance=rBalance+transAmt; //This is for Credit transaction.
			str="Credited";
			System.out.println("sender balance:"+rBalance);
			db.updateQuery("update user set balance='"+rBalance+"' where accountno='"+raccountno+"'");
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // This is for Current date and time.
			Calendar calobj = Calendar.getInstance();
			String dt=dateFormat.format(calobj.getTime());	//dt stores date and time
			
				if(pin.equals(transPin)) 
				{
					try
					{
						DatabaseConnection dbs=new DatabaseConnection();
						HttpSession session = request.getSession();
						int autono=SendSms.gen();
						String mob=(String)AESencrp.decrypt((String) session.getAttribute("contact"));
						System.out.println("Reg Mobile no:"+mob);
						result=db.updateQuery("insert into ebank.transactions(tuid,datetime,sender_account,receiver_account,remarks,amount,benificiary_name,final_balance_sender,final_balance_receiver) values('"+uid1+"','" +dt+ "','"+saccountno+ "','"+raccountno+ "','"+remarks+ "','"+transAmt+ "','"+benificiaryName+"','"+sBalance+"','"+rBalance+"' )");
						String msg="Fund Transfer Successfully Done!!!!!!!"+autono;				
						SendSms.SendSmsToNumber("atulchikane", "8336320", mob, msg, "WEBSMS", "0"); // This is for message sending.	
						System.out.print("Message sending successfully done");
					}
					catch(Exception e)
							{
								result=-1;
								System.out.println(result);
								e.printStackTrace();
							}
					finally
					{
						System.out.println(result);
						
						if(result==-1) // This is for Achieving the Automicity property. 
						{
							DatabaseConnection db1=new DatabaseConnection();
							String str1="Transaction is RollBack";
							DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							Calendar calobj1 = Calendar.getInstance();
							String dt1=dateFormat1.format(calobj.getTime());
							System.out.println(db.updateQuery("insert into admin(logs,time) values('"+str1+"','"+dt1+"')"));
							
							rs11 = db.selectQuery("select * from temp_transactions where id='"+uuid+"'");
							if(rs11.next())
							{
								db.updateQuery("update user set balance='"+rs11.getDouble(4)+"' where accountno='"+saccountno+"'");
								db.updateQuery("update user set balance='"+rs11.getDouble(5)+"' where accountno='"+raccountno+"'");
							}
							request.getRequestDispatcher("Error.jsp?msg=Some error occurs during transaction....please try again").forward(request, response);
						}
					}		
					request.getRequestDispatcher("FundMessage.jsp?raccountno="+raccountno+" &transAmount= "+transAmt+" &benificiary_name= "+benificiaryName+"").forward(request, response);
				}
				else
				{
					response.sendRedirect("Error.jsp?msg=Wrong pin number....please try again");
				}		
		}
			else
			{
				response.sendRedirect("Error.jsp?msg=Insufficient funds or wrong pin");
			}
		}

		catch(Exception e)
		{
			result=-1;
			System.out.println(result);
			e.printStackTrace();

		}
		finally
		{
			try 
			{
				if(rs1!=null)
				{
					rs1.close();
					
				}
				
				if(rs2!=null)
				{
					rs2.close();
					
				}
				
				if(rs11!=null)
				{
					rs11.close();
					
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
		
		
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
