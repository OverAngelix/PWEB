import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.time.*;
import java.time.temporal.*;
import java.sql.*;


public class Cpt extends HttpServlet
{
    

    private int cptGlobal = 0;
    
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
	res.setContentType("text/html;charset=UTF-8");
	PrintWriter out = res.getWriter();
	cptGlobal++;
	HttpSession session = req.getSession();
	if(session.getAttribute("cptLocal") == null) session.setAttribute("cptLocal",1);
	else session.setAttribute("cptLocal",(int)session.getAttribute("cptLocal")+1);
	        
	out.println("<head><title>Compteur</title>");
	out.println("<META content= \"charset=Utf-8\"></head><body><center>");
	out.println("<h1>Vous avez accédé "+session.getAttribute("cptLocal")+" sur un total de "+cptGlobal+"</h1>");
    }
}