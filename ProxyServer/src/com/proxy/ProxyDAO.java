package com.proxy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.ProxyDetection;


/**
 * This class is used for accessing data from 'proxydetect' table
 * 
 *
 */
public class ProxyDAO 
{

	TransactionManager tm;
	
	/**
	 * This method is used to check if ip address is already available in database
	 * @param ipAddress
	 * @return
	 */
	public String checkIPAddress(String ipAddress) 
	{
	
		tm = new TransactionManager();
		
		String query = "select * from proxydetect where ipaddress = '"+ipAddress+"'";
		
		ResultSet rs = tm.check(query);
		
		String available = "failure";
		
		try {
			if(rs.next())
			{
				
				available = "success";
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return available;
		
	}
	
	
	/**
	 * This method is used to get records of clients using ip address or remote host name
	 * @param ipAddress
	 * @param remoteHost
	 * @return
	 */
	public List<ProxyDetection> getIPAddress(String ipAddress, String remoteHost) 
	{
	
		tm = new TransactionManager();
		
		String query = "select * from proxydetect where ipaddress = '"+ipAddress+"' or remotehost = '"+remoteHost+"'";
		
		ResultSet rs = tm.check(query);
		
		
		
		
		List<ProxyDetection> proxyList = new ArrayList<ProxyDetection>();
		
		try {
			while(rs.next())
			{
				
				
				// set client information into ProxyDetection object
				ProxyDetection proxy = new ProxyDetection();
				
				proxy.setDateTime(rs.getString("date"));
				
				proxy.setId(rs.getInt("id"));
				
				proxy.setIpAddress(rs.getString("ipaddress"));
				
				proxy.setIpSuspiciouscount(rs.getInt("ipsuspiciouscount"));
				
				proxy.setRemoteHost(rs.getString("remotehost"));
				
				proxy.setMalicious(rs.getString("malicious"));
				
				proxyList.add(proxy);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return proxyList;
		
	}
	
	
	/**
	 * This method is used to insert client record into database
	 * @param proxy
	 * @return
	 */
	public String insertIPAddress(ProxyDetection proxy) 
	{
	
		tm = new TransactionManager();
		
		int count = proxy.getIpSuspiciouscount() + 1;
		
		String query = "insert into proxydetect(ipaddress,date,ipsuspiciouscount,malicious,remotehost) values('"+proxy.getIpAddress()+"', '"+proxy.getDateTime()+"',"+count+",'"+proxy.getMalicious()+"','"+proxy.getRemoteHost()+"')";
		
		
		int inserted = tm.check1(query);
		
		if(inserted == 1)
			return "success";
		
		else
			return "failure";
	
		
	}
	
	/**
	 * This method is used to update client record into database
	 * @param proxy
	 * @return
	 */
	public String updateDate(ProxyDetection proxy) 
	{
	
		tm = new TransactionManager();
		
		
		
		String query = "update proxydetect set date = '"+proxy.getDateTime()+"' where ipaddress='"+proxy.getIpAddress()+"'";
		
		
		int updated = tm.check1(query);
		
		if(updated == 1)
			return "success";
		
		else
			return "failure";
	
		
	}
		
	
	/**
	 * This method is used to update suspicious count of client into database
	 * @param proxy
	 * @return
	 */
	public String updateCount(ProxyDetection proxy) 
	{
	
		tm = new TransactionManager();
		
		System.out.println("Proxy Update Count Sus : "+proxy.getIpSuspiciouscount());
		
		String query = "update proxydetect set ipsuspiciouscount = "+proxy.getIpSuspiciouscount()+", malicious = '"+proxy.getMalicious()+"' where ipaddress='"+proxy.getIpAddress()+"'";
		
		
		int updated = tm.check1(query);
		
		if(updated == 1)
			return "success";
		
		else
			return "failure";
	
		
	}
		
	
	/**
	 * This method is used to get all clients records from database
	 * @return List<ProxyDetection> (List of clients)
	 */
	public List<ProxyDetection> getAllIPAddresses() 
	{
	
		tm = new TransactionManager();
		
		String query = "select * from proxydetect";
		
		ResultSet rs = tm.check(query);
		
		
		
		
		List<ProxyDetection> proxyList = new ArrayList<ProxyDetection>();
		
		
		try {
			while(rs.next())
			{
				
				// set client information into ProxyDetection object
				ProxyDetection proxy = new ProxyDetection();
				
				proxy.setDateTime(rs.getString("date"));
				
				proxy.setId(rs.getInt("id"));
				
				proxy.setMalicious(rs.getString("malicious"));
				
				proxy.setIpSuspiciouscount(rs.getInt("ipsuspiciouscount"));
				
				proxy.setRemoteHost(rs.getString("remotehost"));
				
				proxy.setIpAddress(rs.getString("ipaddress"));
				
				proxyList.add(proxy);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return proxyList;
		
	}
		
		
	
}
