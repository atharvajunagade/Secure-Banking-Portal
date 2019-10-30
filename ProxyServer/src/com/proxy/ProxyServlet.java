package com.proxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.beans.ProxyDetection;

/**
 * This is Proxy Server which implements TBAD Algorithm for detecting DDOS Attack
 * 
 */
@WebServlet("/ProxyServlet")
public class ProxyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 
	 
	 
	 private static final int threshold = 5;
	 
	 private static int ipIncidenceListCOunt = 0;
       
    
    public ProxyServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doPost(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		// TBAD Algorithm is implemented here
		
		String ipAddress = request.getParameter("ipaddress");
		
		System.out.println("Ip Address is : "+ipAddress);
		
		String hostName = request.getParameter("hostName");
		
		System.out.println("Host Name is : "+hostName);
		
		
		boolean dateUpdated = false;
		
		
		JSONObject json = new JSONObject();
		
		
      
        
        List<ProxyDetection> proxyList = null;
        
        
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        String currentTime = dateFormat.format(date);   // get current data-time of request arrival
        
        System.out.println("Current Time : "+currentTime);
        
        
        ProxyDetection proxy = new ProxyDetection();
        
        
        // check if client ip address is already available
        String available = new ProxyDAO().checkIPAddress(ipAddress); 
        
        System.out.println("IP Address available : "+available);
        
        
        if(available.equals("failure"))
        {
        	
        	// set client information into ProxyDetection bean object
        	proxy.setDateTime(currentTime);
        	
        	proxy.setIpAddress(ipAddress);
        	
        	proxy.setIpSuspiciouscount(0);
        	
        	proxy.setMalicious("no");
        	
        	proxy.setRemoteHost(hostName);
        	
        	
        	// insert client record into database
        	String inserted = new ProxyDAO().insertIPAddress(proxy);
        	
        	System.out.println("IP Address inserted : "+inserted);
        	
        	json.put("result", "success");
        	
        	
        }
        else
        {
        	
        	
        	
        	// insert client record from database
        	proxyList = new ProxyDAO().getIPAddress(ipAddress, hostName);
        	
        	
        	int proxyCount = 0;
        	
        	while(proxyCount < proxyList.size())
        	{
        	
        		proxy = proxyList.get(proxyCount);
        	
        		
        		if(proxy.getMalicious().equals("no"))
        		{
        		
        			// get date-time of last request of same client from database
        		String proxyDate = proxyList.get(proxyCount).getDateTime();
        		
        		System.out.println("Proxy date : "+proxyDate);
        		
        		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        		
        		try {
					
        			Date datePrevious = formatter.parse(proxyDate);
					
					Date dateCurrent = formatter.parse(currentTime);
					
					
					// Calculate difference between two requests times from same ip address
					long diff = dateCurrent.getTime() - datePrevious.getTime();
					
			        long diffSeconds = diff / 1000 % 60;
			        
			        
			        
		        		
		        		proxy.setDateTime(currentTime);
		        		
		        		
		        		// update date-time of request arrival
		        		String isDateUpdated = new ProxyDAO().updateDate(proxy);
		        		
		        		System.out.println("Is Date Updated : "+isDateUpdated);
		        		
		        		System.out.println("Diffeecnce in seconds : "+diffSeconds);
		        		
		        		
		        	
			        
			        // check if two arrival times difference is less than 5 seconds
			        if(diffSeconds < 5)
			        {
			        	
			        	System.out.println("In diffSeconds > 5");
			        	
			        	
			        	
			        	proxy.setIpSuspiciouscount(proxy.getIpSuspiciouscount() + 1);
			        	
			        	System.out.println("Suspicious Count : "+proxy.getIpSuspiciouscount());
			        	
			        	
			        	// update Suspicious Count of client in database
			        	String countUpdated = new ProxyDAO().updateCount(proxy);
			        	
			        	System.out.println("Proxy updated : "+countUpdated);
			        	
			        	
			        	if(proxy.getIpSuspiciouscount() > threshold)
			        	{
			        		
			        		// Reject Request Packets
			        		
			        		System.out.println("Reject Request...............");
			        		
			        		proxy.setMalicious("yes");
			        		
			        		String countUpdated1 = new ProxyDAO().updateCount(proxy);
				        	
				        	System.out.println("Proxy updated Malicious : "+countUpdated1);
			        		
			        		json.put("result", "failure");
			        		
			        		
			        	}
			        	else
			        	{
			        		
			        		// Allow Request Packets to access System
			        		
			        		System.out.println("Allow Request...............");
			        		
			        		json.put("result", "success");
			        		
			        	}
			        	
			        	
			        	
			        }
			        else
			        {
			        	System.out.println("in else diffSeconds < 5");
			        	System.out.println("Allow Request...............");
		        		
		        		json.put("result", "success");
			        }
			        	
			        
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		}
        		else
        		{
        			
        			System.out.println("Reject Request...............");
	        		
	        		
	        		
	        		json.put("result", "failure");
        			
        		}
        		
        		
        		proxyCount++;
        		
        	}
        	
        	
        	
        }
        
        
      // send response to calling application
        
        PrintWriter out = response.getWriter();
        
        response.setContentType("application/json");
        
        out.write(json.toString());
        
       
		
		
	}

}
