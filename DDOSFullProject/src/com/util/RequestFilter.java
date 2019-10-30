package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.ParseException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



/**
 * This Filter Class is used to forward requests to Proxy Server for processing of requests
 *
 */
@WebFilter("/RequestFilter")
public class RequestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RequestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		HttpServletRequest requestHttp = (HttpServletRequest) request;
		
		HttpServletResponse responseHttp = (HttpServletResponse) response;
		
		String urlString = requestHttp.getRequestURL().toString();
		
		
		String hostName = requestHttp.getRemoteHost();
		
		System.out.println("Remote Host : "+hostName);
		
		String ipAddress = null;
		
		
		
		// get IP Address of client from request
		if(urlString.contains("localhost"))
			ipAddress = request.getLocalAddr();
		
		else
			ipAddress = request.getRemoteAddr();
		
		
		System.out.println("Ip Address : "+ipAddress);
		
		System.out.println("Request URL is : "+urlString);
		
		URL resourceURL = null;
		
	
			
		
		JSONObject json = null;
		try {
			
			// call method to read JSON data from url
			json = readJsonFromUrl("http://localhost:8080/ProxyServer/ProxyServlet?ipaddress="+ipAddress+"&hostName="+hostName);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		System.out.println("Json Object is : "+json);
		
		
		
		if(json.get("result").equals("success"))
		{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		else
		{
			responseHttp.sendRedirect("AttackerRedirect.jsp");
		}
		
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
	public static JSONObject readJsonFromUrl(String url) throws IOException, ParseException 
    {
    	// This method is used to read Json Object from specified url executing sentiment analysis online
    	
    	

   	 TrustManager[] trustAllCerts = new TrustManager[] {
   		       new X509TrustManager() {
   		          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
   		            return null;
   		          }

   		          public void checkClientTrusted(X509Certificate[] certs, String authType) {  }

   		          public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

   		       }
   		    };

   		    SSLContext sc = null;
 				try {
 					sc = SSLContext.getInstance("SSL");
 				} catch (NoSuchAlgorithmException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
   		    try {
 					sc.init(null, trustAllCerts, new java.security.SecureRandom());
 				} catch (KeyManagementException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
   		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

   		    // Create all-trusting host name verifier
   		    HostnameVerifier allHostsValid = new HostnameVerifier() {
   		        public boolean verify(String hostname, SSLSession session) {
   		          return true;
   		        }
   		    };
   		    // Install the all-trusting host verifier
   		    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
   		    /*
   		     * end of the fix
   		     */
   	
    	
        InputStream is = new URL(url).openStream();  // connect to proxy servlet using url and get data into input stream from proxy server
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          
          
          String jsonText = readAll(rd);
          
          
          int start=jsonText.indexOf("{");
          int end=jsonText.lastIndexOf("}")+1;
          String substr=jsonText.substring(start,end);             
          
         JSONObject json = new JSONObject(substr);  // parse data into JSON Object
            System.out.println("Json : "+json.toString());
          return json;
        } finally {
          is.close();
        }
    }
	
	private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
    }

}
