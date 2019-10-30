/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.41
 * Generated at: 2018-03-22 08:22:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import com.dao.MiniState;
import com.util.DatabaseConnection;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.model.MiniStatement;
import com.dao.DB;
import com.model.Account;
import java.util.ArrayList;

public final class MiniStatement_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.sun.xml.internal.bind.v2.schemagen.xmlschema.List");
    _jspx_imports_classes.add("com.dao.MiniState");
    _jspx_imports_classes.add("java.sql.ResultSet");
    _jspx_imports_classes.add("com.model.MiniStatement");
    _jspx_imports_classes.add("com.dao.DB");
    _jspx_imports_classes.add("com.model.Account");
    _jspx_imports_classes.add("com.util.DatabaseConnection");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("     ");

	 if(request.getSession(false).getAttribute("uid")==null)
	    {
	    	response.sendRedirect("Login.jsp");
	    }
	 else
	 {
    
      out.write("\r\n");
      out.write("    \r\n");
      out.write(" ");
 MiniState ms= new MiniState(); // Creating the object of MiniState
 String accountnumber =  session.getAttribute("accountno").toString();
  DatabaseConnection db = new DatabaseConnection();
 ResultSet rs = db.selectQuery("SELECT * FROM ebank.user where accountno='"+accountnumber+"'");
 
 double availableBalance = 0.0;
 
 try{
	 
 if(rs.next());
  availableBalance = rs.getDouble("balance"); //Fetching the customers available balance from database.
 
	 }
		catch(Exception e)
		{
				
				e.printStackTrace();
		}
		finally
		{
 		
			try 
			{
			   if(rs!=null)
			   {
				rs.close();
				DatabaseConnection.closeConnection();
			   }
			} 
			catch(Exception e)
			{
					
					e.printStackTrace();
			}
		}	
		
 ArrayList<MiniStatement> list = ms.getDetails(accountnumber); //Passing the accountno to getDetails method of MiniState class.
 
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title>Net Banking</title>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">\r\n");
      out.write("\t\t<!-- stylesheets -->\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"resources\\css\\reset.css\">\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"resources\\css\\style.css\" media=\"screen\">\r\n");
      out.write("\t\t<link id=\"color\" rel=\"stylesheet\" type=\"text/css\" href=\"resources\\css\\colors\\blue.css\">\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css\">\r\n");
      out.write("\t\t<!-- scripts (jquery) -->\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\jquery-1.6.4.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<!--[if IE]><script language=\"javascript\" type=\"text/javascript\" src=\"resources/scripts/excanvas.min.js\"></script><![endif]-->\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\jquery-ui-1.8.16.custom.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\jquery.ui.selectmenu.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\jquery.flot.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\tiny_mce\\tiny_mce.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\tiny_mce\\jquery.tinymce.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<!-- scripts (custom) -->\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\smooth.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\smooth.menu.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\smooth.chart.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\smooth.table.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\smooth.form.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\smooth.dialog.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script src=\"resources\\scripts\\smooth.autocomplete.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t$(document).ready(function () {\r\n");
      out.write("\t\t\t\tstyle_path = \"resources/css/colors\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t$(\"#date-picker\").datepicker();\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t$(\"#box-tabs, #box-left-tabs\").tabs();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- end dialogs -->\r\n");
      out.write("\t\t<!-- header -->\r\n");
      out.write("\t\t<div id=\"header\">\r\n");
      out.write("\t\t\t<!-- logo -->\r\n");
      out.write("\t\t\t<div id=\"logo\">\r\n");
      out.write("\t\t\t\t<h1><a href=\"\" title=\"Net Banking\">Net Banking</a></h1>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- end logo -->\r\n");
      out.write("\t\t\t<!-- user -->\r\n");
      out.write("\t\t\t<ul id=\"user\">\r\n");
      out.write("\t\t\t\t<!--  <li class=\"first\"><a href=\"\">Account</a></li> -->\r\n");
      out.write("\t\t\t\t<li><i class=\"fa fa-sign-out fa-2x\"></i><a href=\"LogoutUser\">Logout</a>&nbsp;</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t</ul> \r\n");
      out.write("\t\t\t<!-- end user -->\r\n");
      out.write("\t\t\t<div id=\"header-inner\">\r\n");
      out.write("\t\t\t\t<div id=\"home\">\r\n");
      out.write("\t\t\t\t\t<a href=\"\" title=\"Home\"></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- quick -->\r\n");
      out.write("\t\t\t\t<ul id=\"quick\">\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"Home1.jsp\" title=\"Products\"><span class=\"normal\">Home</span></a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"ContactUs.jsp\" title=\"Products\"><span>Contact Us</span></a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"\" title=\"Events\"><span>Accounts</span></a>\r\n");
      out.write("\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"AccountDetails.jsp?uid=");
      out.print(session.getAttribute("uid"));
      out.write("\">Account Details</a></li> \r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"MiniStatement.jsp\">Mini Statements</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"\" title=\"Pages\"><span>Transaction</span></a>\r\n");
      out.write("\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"FundTransfer.jsp\">Transfer Money</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"BillPayment.jsp\">Bill Payments</a></li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"\" title=\"Settings\"><span>Account Settings</span></a>\r\n");
      out.write("\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"UpdateProfile.jsp\">Update Your Profile</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t<!-- end quick -->\r\n");
      out.write("\t\t\t\t<div class=\"corner tl\"></div>\r\n");
      out.write("\t\t\t\t<div class=\"corner tr\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- end header -->\r\n");
      out.write("\t\t<!-- content -->\r\n");
      out.write("\t\t<div id=\"content\">\r\n");
      out.write("\t\t\t<!-- end content / left -->\r\n");
      out.write("\t\t\t<div id=\"left\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<div>\r\n");
      out.write("\t\t\t<img src=\"resources/images/bank.jpg\" style=\"width: 220px;height: 665px; margin-left:60px\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div id=\"date-picker\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- end content / left -->\r\n");
      out.write("\t\t\t<!-- content / right -->\r\n");
      out.write("\t\t\t<div id=\"right\">\r\n");
      out.write("\t\t\t\t<!-- table -->\r\n");
      out.write("\t\t\t\t<div class=\"box\">\r\n");
      out.write("\t\t\t\t\t<!-- box / title -->\r\n");
      out.write("\t\t\t\t\t<div class=\"title\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<!-- <div class=\"search\">\r\n");
      out.write("\t\t\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t\t</div> -->\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<!-- end box / title -->\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<div class=\"table\" >\r\n");
      out.write("\t\t\t\t\t\t<form action=\"\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t\t<table style=\"width: 1067px\";>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>Account Number &nbsp:&nbsp");
      out.print(accountnumber);
      out.write("<br><br>\r\n");
      out.write("\t\t\t\t\t\t\t\tAvailable Balance &nbsp:&nbsp");
      out.print(availableBalance);
      out.write("</th>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th align=\"left\">Transaction ID</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th align=\"left\">Transaction Type</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th align=\"left\">Amount</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th align=\"left\">Date and Time</th>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t");

						for(int i=0;i<list.size();i++) 
						{
							MiniStatement ms1=list.get(i); //Accessing the transaction details of customer.
							System.out.println(ms1);
							out.print("<tr><td>"+ms1.getTransId()+"</td><td>"+ms1.getTransType()+"</td><td>"+ms1.getTransAmount()+"</td><td>"+ms1.getTransTime()+"</td></tr>");
						}

					
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</table>\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\t\t\t\t\r\n");
      out.write("\t\t\t\t<!-- end box / right -->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- end content / right -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- end content -->\r\n");
      out.write("\t\t<!-- footer -->\r\n");
      out.write("\t\t<div id=\"footer\">\r\n");
      out.write("\t\t\t<p>Copyright &copy; 2000-2010 Your Company. All Rights Reserved.</p>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- end footert -->\r\n");
      out.write("\t\t");
} 
      out.write("\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
