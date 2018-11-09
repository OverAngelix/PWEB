
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

public class Modif extends HttpServlet
{
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost:9092/~/test";
    static final String USER = "sa";
    static final String PASS = "";

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	 HttpSession session = req.getSession( true );
    	 if(session.getAttribute("user")== null) {
	        	res.sendRedirect("login.html");
	        }else {
	        	
    	PrintWriter pw = res.getWriter();
    	pw.print("<html><body>");
    	pw.print("<h1>REMPLIR NOUVELLES COORDONNEES</h1>");
        pw.print("<form action=servlet-Modif method=post>");
    	pw.print("Log<input name = log type=text>");
    	pw.print("Mdp<input name = mdp type=text>");
  		pw.print("Nom<input name = nom type=text>");
   		pw.print("Prenom<input name = prenom type=text>");
   		pw.print("Adresse<input name = add type=text>");
   		pw.print("Email<input name = mail type=text>");
   		pw.print("Tel<input name = tel type=text>");
   		pw.print("Naiss<input name = naiss type=text>");
   		pw.print("Role<input name = role type=text>");
   		pw.print("<br/>");
   		pw.print("<input type=submit>");
   		pw.print("</form>");
   		pw.print("</body></html>");
	        
	        }
   		
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Connection conn = null;
        
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            HttpSession session = req.getSession( true );
            if(session.getAttribute("role")!="Admin") {
            String query = "update PERSONNE set login=?,mdp=?,nom=?,prenom=?,adresse=?,email=?,tel=?,datenaiss=?,role=? where login='"+session.getAttribute("user")+"'";
    		PreparedStatement ps = conn.prepareStatement( query );
    		ps.setString(1, req.getParameter("log"));
    		ps.setString(2, req.getParameter("mdp"));
    		ps.setString(3, req.getParameter("nom"));
    		ps.setString(4, req.getParameter("prenom"));
    		ps.setString(5, req.getParameter("add"));
    		ps.setString(6, req.getParameter("mail"));
    		ps.setString(7, req.getParameter("tel"));
    		ps.setString(8, req.getParameter("naiss"));
    		ps.executeUpdate();
    		
    		session.setAttribute("user",req.getParameter("log"));
            }else {
            	 String query = "update PERSONNE set login=?,mdp=?,nom=?,prenom=?,adresse=?,email=?,tel=?,datenaiss=?,role=? where login='"+req.getParameter("log")+"'";
         		PreparedStatement ps = conn.prepareStatement( query );
         		ps.setString(1, req.getParameter("log"));
         		ps.setString(2, req.getParameter("mdp"));
         		ps.setString(3, req.getParameter("nom"));
         		ps.setString(4, req.getParameter("prenom"));
         		ps.setString(5, req.getParameter("add"));
         		ps.setString(6, req.getParameter("mail"));
         		ps.setString(7, req.getParameter("tel"));
         		ps.setString(8, req.getParameter("naiss"));
         		ps.setString(9, req.getParameter("role"));
         		
         		if(req.getParameter("log").equals(session.getAttribute("log"))){
         			session.setAttribute("user",req.getParameter("log"));
            		session.setAttribute("role",req.getParameter("role"));
         		}
         		ps.executeUpdate();
            }
    		conn.close();
			res.sendRedirect("menu.html");
			
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	
	
    }


}