package com.dao;
import java.net.*;
import java.io.*;

/**
 * This class is use for Forgot Password. 
 * 
 *
 */
public class ForgotPassword {
	
	 public static void main(String[] args) throws Exception
	 {
	        URL yahoo = new URL("http://www.yahoo.com/");
	        URLConnection yc = yahoo.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) 
	        System.out.println(inputLine);
	        in.close();
	 }

}
