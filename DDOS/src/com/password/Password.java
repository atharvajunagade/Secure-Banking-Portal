package com.password;

/**
 * This class is use for generating the password and pin number dynamically.  
 * @author Renuka Gore
 *
 */

public class Password {
static String password;
	public static String password(String email)
	{
		try
		{
		String name=email.substring(0, email.lastIndexOf('@')); 	//Generating the username.
		
		 int randomPIN = (int)(Math.random()*9000)+1000; 			//Generating the pin number.
	
 		System.out.println(name);
 		
		password=name+Integer.toString(randomPIN); 					//Generating the password.
		
		return password;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return email;
		}
	
	 public static void main(String[] args) {
		password("");
	}
}
