	package com.dao;
	
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.OutputStreamWriter;
	import java.net.URL;
	import java.net.URLConnection;
	import java.net.URLEncoder;
	import java.util.Random;
	import com.dao.SendSms;
	
	/**
	 * This class is use for Message sending. 
	 * @author Renuka Gore
	 *
	 */
	
	public class SendSms 
	{
	public static String retval="";
	
	public static String SendSmsToNumber(String user,String pwd,String to,String msg,String sid,String fl)
	{
	String rsp="";
	
	try
	{	
		// Construct The Post Data	
		System.out.println("Send sms class");		
		String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
		data += "&" + URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8");
		data += "&" + URLEncoder.encode("to", "UTF-8") + "=" + URLEncoder.encode(to, "UTF-8");
		data += "&" + URLEncoder.encode("msg", "UTF-8") + "=" + URLEncoder.encode(msg, "UTF-8");
		data += "&" + URLEncoder.encode("sid", "UTF-8") + "=" + URLEncoder.encode(sid, "UTF-8");
		data += "&" + URLEncoder.encode("fl", "UTF-8") + "=" + URLEncoder.encode(fl, "UTF-8");
	
		//Push the HTTP Request
		URL url = new URL("http://login.smsgatewayhub.com/smsapi/pushsms.aspx?");
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data);
		wr.flush();
	
		//Read The Response
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null)
		{
		retval += line;
		}
		
		wr.close();
		rd.close();

		rsp = retval;
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return rsp;
	}
	public static int gen() 
	{
	    Random r = new Random( System.currentTimeMillis()); //Dynamically OTP generation.
	    return 10000 + r.nextInt(20000);
	}
	public static void main(String[] args) 
	{
		String msg="Kind Attention!!! This is the code for your security purpose \n Its valid for next 10 minits only...\n " +gen();
		String response = SendSms.SendSmsToNumber("atulchikane", "8336320", "9730488157", msg, "WEBSMS", "0"); //Sending Message.
		
	}
	}