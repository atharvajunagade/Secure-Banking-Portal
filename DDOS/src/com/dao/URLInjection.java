package com.dao;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * This class is use for Encoding and Decoding of URL.  
 * @author Renuka Gore
 *
 */

public class URLInjection {
	
	public String encode(String url)
	{
		try {

			String encodedUrl = URLEncoder.encode(url, "UTF-8"); //Encoding the inputed URL

			System.out.println("Encoded URL " + encodedUrl);

			} catch (UnsupportedEncodingException e) {

				System.err.println(e);

			}
		return url;
	}

	public String decode(String url)
	{
		try {
			
			String decodedUrl = URLDecoder.decode(url, "UTF-8"); //Decoding the inputed URL

			System.out.println("Dncoded URL " + decodedUrl);

			} catch (UnsupportedEncodingException e) {

				System.err.println(e);

			}
		return url;
	}
  public static void main(String args[]) 
  {
	  URLInjection u=new URLInjection();
	  String s=u.encode("NIleshe");
	  System.out.println(s);
	  String s1=u.decode(s);
	  System.out.println(s1);
	  
	
    }
}

