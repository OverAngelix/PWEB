// Servlet Test.java  de test de la configuration
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;


public class Disconnect extends HttpServlet
{

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        
        HttpSession session = req.getSession( true );
			session.invalidate();
			res.sendRedirect("login.html");
      
	
	
    }


}