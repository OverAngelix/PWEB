
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.ArrayList;

public class Menu extends HttpServlet
{
    
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		Connection con = null;
		
		
			HttpSession session = req.getSession( true );
			PrintWriter out  = res.getWriter();
	        if(session.getAttribute("user")== null) {
	        	res.sendRedirect("login.html");
	        }else {
	        	out.print("<!-- Test.html -->\r\n" + 
	        			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n" + 
	        			"<HTML>\r\n" + 
	        			"<HEAD>\r\n" + 
	        			"   <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" + 
	        			"   <TITLE>LOGIN</TITLE>\r\n" + 
	        			"</HEAD>\r\n" + 
	        			"<BODY>\r\n" + 
	        			"\r\n" + 
	        			"<center>\r\n" + 
	        			"<h1>MENU</h1>\r\n" + 
	        			"</center>\r\n" + 
	        			"<a href =\"./servlet-Lecture\">VOIR COORDONNEES</a>\r\n" + 
	        			"<a href =\"./servlet-Modif\">MODIFIER COORDONNEES</a>\r\n" + 
	        			"\r\n" + 
	        			"</BODY>\r\n" + 
	        			"</HTML>\r\n" );
	        }
    }


}