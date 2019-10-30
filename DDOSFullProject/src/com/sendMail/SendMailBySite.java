
package com.sendMail;

/**
 * This  class is use for sending mail.  
 * 
 *
 */
import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
  
public class SendMailBySite 
{  
    public static String sendMail(String toUser,String emailMessage)
    {
    	System.out.println("In mail");
        String host="smtp.gmail.com";  
        final String user="opulentinfotechpvtltd";	//change accordingly  
        final String password="opulent09";			//change accordingly  
        String subject="EBank Notification";
        // String to="sushilbirajdar7@gmail.com";	//change accordingly  
        
        //Get the session object  
        Properties props = new Properties();  
        props.put("mail.smtp.host",host);  
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", "465");
        props.setProperty("mail.smtp.protocol", "smtps");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.enable", "true");  

        Session session = Session.getInstance(props, new javax.mail.Authenticator() 
        {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() 
        {
            return new PasswordAuthentication(user, password);
        }
        }); 
        try
        {  				
	        MimeMessage message = new MimeMessage(session);  		 //Compose the message  
	        message.setFrom(new InternetAddress(user));  
	        message.addRecipient(Message.RecipientType.TO,new InternetAddress(toUser));        
	        message.setSubject(subject);       
	        message.setContent(emailMessage,"text/html" );
	        Transport.send(message);    							//sending the message 
	        System.out.println("message sent successfully...");  
	        return "Message Send Successfully";
        } 
        catch (MessagingException e) 
        { 
        	return "Message Sending Failed...."; 
        }  
    	}
    
    public static void main(String[] args) 
    {  
	    	String msg="<h1>sending html mail check</h1>";        
	    	String result=SendMailBySite.sendMail("udaygarg92@gmail.com", msg);
	    	System.out.println("Result : "+result);
    }  
}  