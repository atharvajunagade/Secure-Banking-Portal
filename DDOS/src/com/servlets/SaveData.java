package com.servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AES.AESencrp;
import com.password.Password; 
import com.util.DatabaseConnection;

/**
 * Servlet implementation class SaveData
 */
@WebServlet("/SaveData")
public class SaveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("\n\n\n\n\n\n\n\n\nIn SaveData.java");
		
		
		Double lat1=Double.parseDouble(request.getParameter("lat"));
		Double lng1=Double.parseDouble(request.getParameter("lng"));
		Date date1 = new Date(System.currentTimeMillis());
		Time time1 = new Time(System.currentTimeMillis());
		
	 
		HttpServletRequest requestHttp = (HttpServletRequest) request;
		 
		String urlString = requestHttp.getRequestURL().toString();
		
		String ipAddress = null;
		 
		// get IP Address of client from request
				if(urlString.contains("localhost"))
					ipAddress = request.getLocalAddr();
				
				else
					ipAddress = request.getRemoteAddr();
		
		
		String ipAddressofproxy = null;
		
		
	 
		
		
		
		System.out.println(lat1);
		System.out.println(lng1);
		System.out.println(date1);
		System.out.println(time1);
		System.out.println(ipAddress);
		System.out.println(ipAddressofproxy);
		
		
 
		   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


	        String currentTime = dateFormat.format(date1);   // get current data-time of request arrival
	        
	        System.out.println("Current Time : "+currentTime);
		
	      String savetime=currentTime;
		
		String available = "failure";
		
		 
	 
		
		DatabaseConnection db = new DatabaseConnection();  
		ResultSet rs = db.selectQuery("select * from location where ipaddress='"+ ipAddress + "'");
		 
			try {
				if(rs.next())
				{
					
					available = "success"; 
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			 
		    if(available.equals("failure"))
	        {
	        	
		    	db.updateQuery("insert into location(lat,lng,ipaddress,date)values('"+lat1+"','"+lng1+"','"+ipAddress+"','"+currentTime+"')");
	        	
	        }
		    else
		    {
		    	String latget=null;
		    	String lngget=null;
		    	String date=null;
		    	String query = "select lat,lng,date from location where ipaddress = '"+ipAddress+"'";
				
				ResultSet rs2 = db.selectQuery(query);
				try {
					if (rs2.next()) 
					{
						latget=rs2.getString("lat");
						lngget=rs2.getString("lng");
						date=rs2.getString("date");
						System.out.println("You have get previous stored lat lng = "+latget+"\t"+lngget+"\t"+date);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Checking differecnce");
				Double latgetcheck=Double.parseDouble(latget);
				Double lnggetcheck=Double.parseDouble(lngget);
				
				DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        		
        	 
					
        			Date datePrevious = null;
					try {
						datePrevious = formatter.parse(date);
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					Date dateCurrent = null;
					try {
						dateCurrent = formatter.parse(currentTime);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				 
				
			        long diff = dateCurrent.getTime() - datePrevious.getTime();
					
			        long diffSeconds = diff / 1000 % 60;
			        
			        System.out.println("\n\nDate diifer  "+diff+"\t"+diffSeconds);
				  
			        
			        long diffMiutes=diffSeconds/60;
			        long diffehrs=diffMiutes/60;
			        
			        System.out.println(diffMiutes+"\t"+diffehrs);
			        
				
				Double a=latgetcheck-lat1;
				Double b=lnggetcheck-lng1;
				
				
				if(a<0)
				{
					a=a*(-1);
				}
				if(b<0)
				{
					b=b*(-1);
				}
				
				System.out.println("Latdirece Long diiff   "+a+"\t"+b);
				
				System.out.println("Going to change in range");
				
				int rang=(int) diffehrs;
				rang=rang*10;
				if(rang<=0)
				{
					rang=10;
				}
				System.out.println("Range = "+rang);
				
				
				if(a>rang || b>rang)
				{
					int count = 0;
					System.out.println("Differice is greater thean rang");
					
					String getcount = "select ipsuspiciouscount from proxydetect where ipaddress = '"+ipAddress+"'";
					
					ResultSet rsgetcount = db.selectQuery(getcount);
					try {
						if (rsgetcount.next()) 
						{
							count=Integer.parseInt(rsgetcount.getString("ipsuspiciouscount")); 
							System.out.println("You have get ipsuspiciouscount = "+count);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count=count+1;
					
					String queryupdate = "update proxydetect set ipsuspiciouscount = '"+count+"' where ipaddress='"+ipAddress+"'";
					int countupdat=db.updateQuery(queryupdate);
					if(countupdat>0)
					{
					
					System.out.println("issuspicious count incremented \ngoing to update lat lng " +lat1+"\t"+lng1);
					
					}
					
					DatabaseConnection dblocupdate = new DatabaseConnection(); 
					String querylocation = "update location set lat = '"+lat1+"' , lng='"+lng1+"', date='"+currentTime+"' where ipaddress='"+ipAddress+"'";
					int locupdate=dblocupdate.updateQuery(querylocation);
					if(locupdate>0)
					{
						System.out.println("location is updated");
					}
					
					String str="location chagned";
					DateFormat dateFormatlocstore = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Calendar calobj = Calendar.getInstance();
					String dt1 = dateFormatlocstore.format(calobj.getTime());
					DatabaseConnection dbinserinadmin = new DatabaseConnection(); 
					String injectattack = "insert into ebank.admin(logs,time,ipAddress) values('"+ str + "','" + currentTime + "','"+ipAddress+"')";
					int injectattackstore=dbinserinadmin.updateQuery(injectattack);
					if(injectattackstore>0)
					{
						System.out.println("record  is inserted in admin\n\n");
					}
					
					System.out.println("\n\n");
					 
				}
				else
				{
					System.out.println("Differecnce is not greater than range\n\n\n");
				}
				
				
		    }
				
		    
		 
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
