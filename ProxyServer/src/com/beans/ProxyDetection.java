package com.beans;

/**
 * This class represents Client who is sending requests to E-Banking Application
 * 
 *
 */
public class ProxyDetection 
{

	
	int id;
	
	String ipAddress;
	
	String dateTime;
	
	int ipSuspiciouscount;
	
	String malicious;
	
	String remoteHost;

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getMalicious() {
		return malicious;
	}

	public void setMalicious(String malicious) {
		this.malicious = malicious;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getIpSuspiciouscount() {
		return ipSuspiciouscount;
	}

	public void setIpSuspiciouscount(int ipSuspiciouscount) {
		this.ipSuspiciouscount = ipSuspiciouscount;
	}

	
}
