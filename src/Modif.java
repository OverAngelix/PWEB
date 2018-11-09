
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
	        	System.out.println(session.getAttribute("role"));
	        	 if(!session.getAttribute("role").equals("Admin")) {
	        		 PrintWriter pw = res.getWriter();
	        	    	pw.print("<html><body>");
	        	    	pw.print("<h1>REMPLIR NOUVELLES COORDONNEES</h1>");
	        	        pw.print("<form action=servlet-Modif method=post>");
	        	  
	        	    	pw.print("Mdp<input name = mdp type=text>");
	        	  		pw.print("Nom<input name = nom type=text>");
	        	   		pw.print("Prenom<input name = prenom type=text>");
	        	   		
	        	   		pw.print("<br/>");
	        	   		pw.print("<input type=submit>");
	        	   		pw.print("</form>");
	        	   		pw.print("</body></html>");
	        
	        }else {
	        	 Connection conn = null;
	             
	             try {
	                 Class.forName("org.h2.Driver");
	                 conn = DriverManager.getConnection(DB_URL, USER, PASS);
	                 String sql = "select login from personne";
	                 PreparedStatement ps = conn.prepareStatement( sql );
	 				ResultSet rs = ps.executeQuery();
	        	PrintWriter pw = res.getWriter();
	        	
	        	pw.print("<html><body>");
	        	  pw.print("<font face='verdana'>");
	    		
	        		pw.println("Liste des logins :");
	    		while ( rs.next() ) {
	    		String log = rs.getString("login");
	    	
	    		pw.println("<p>"
	    				+log+"</br>"
	
	    				+"</p>");
	    		}
	    		  pw.print("</font>");
	             
	  			conn.close();
	        	pw.print("<h1>REMPLIR NOUVELLES COORDONNEES</h1>");
	            pw.print("<form action=servlet-Modif method=post>");
	            pw.print("Login<input name = log type=text>");
	        	pw.print("Mdp<input name = mdp type=text>");
	      		pw.print("Nom<input name = nom type=text>");
	       		pw.print("Prenom<input name = prenom type=text>");
	       		pw.print("Role<input name = role type=text>");
	       		pw.print("<br/>");
	       		pw.print("<input type=submit>");
	       		pw.print("</form>");
	       		pw.print("</body></html>");
	       	 pw.close();
	        }   catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            
	        e.printStackTrace();
	        }
	        }
	        }
   		
    }
    //lol
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Connection conn = null;
        
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            HttpSession session = req.getSession( true );
            if(!session.getAttribute("role").equals("Admin")) {
            String query = "update PERSONNE set mdp=?,nom=?,prenom=? where login='"+session.getAttribute("user")+"'";
    		PreparedStatement ps = conn.prepareStatement( query );
    		ps.setString(1, req.getParameter("mdp"));
    		ps.setString(2, req.getParameter("nom"));
    		ps.setString(3, req.getParameter("prenom"));
    		ps.executeUpdate();
            }else {
            	 String query = "update PERSONNE set mdp=?,nom=?,prenom=?,role=? where login='"+req.getParameter("log")+"'";
         		PreparedStatement ps = conn.prepareStatement( query );
         		ps.setString(1, req.getParameter("mdp"));
         		ps.setString(2, req.getParameter("nom"));
         		ps.setString(3, req.getParameter("prenom"));
         		ps.setString(4, req.getParameter("role"));
         	
         		ps.executeUpdate();
            }
    		conn.close();
			res.sendRedirect("./servlet-Menu");
			
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	
	
    }


}