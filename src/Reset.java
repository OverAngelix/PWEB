import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class Reset extends HttpServlet
{
    
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
	
	HttpSession session = req.getSession();
	session.invalidate();
	res.sendRedirect("servlet-ListerJoueur");
    }
}