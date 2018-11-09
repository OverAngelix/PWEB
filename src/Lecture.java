
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;


public class Lecture extends HttpServlet
{
	public static boolean triop = false;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost:9092/~/test";
    static final String USER = "sa";
    static final String PASS = "";

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Connection conn = null;
        
        try {
        	
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            HttpSession session = req.getSession( true );
           
       	 if(session.getAttribute("user")== null) {
   	        	res.sendRedirect("login.html");
   	        }else {
   	        	if(!session.getAttribute("role").equals("Admin")) {
           String sql = "select * from Personne where login='"+session.getAttribute("user")+"'";
           PreparedStatement ps = conn.prepareStatement( sql );
				ResultSet rs = ps.executeQuery();
            PrintWriter pw = res.getWriter();
			  pw.print("<font face='verdana'>");
		  pw.println("<center><h2>"+session.getAttribute("user")+"</h2></center>");
	
		while ( rs.next() ) {
		String log = rs.getString("login");
		String mdp= rs.getString("mdp");
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		String adresse = rs.getString("adresse");
		String email = rs.getString("email");
		String tel = rs.getString("tel");
		String datenaiss = rs.getString("datenaiss");
		String role = rs.getString("role");
		pw.println("<p>"
				+"login :"+log+"</br>"
				+"mdp :"+mdp+"</br>"
				+"nom :"+nom+"</br>"
				+"prenom :"+prenom+"</br>"
				+"adresse :"+adresse+"</br>"
				+"email :"+email+"</br>"
				+"tel :"+tel+"</br>"
				+"date de naissance :"+datenaiss+"</br>"
				+"role :"+role+"</br>"
				+"</p>");
		}
		
		
   	        

            pw.print("</font>");
            pw.close();
			conn.close();
   	        	}else {
   	        		String sql = "select * from Personne";
   	             PreparedStatement ps = conn.prepareStatement( sql );
   	  				ResultSet rs = ps.executeQuery();
   	              PrintWriter pw = res.getWriter();
   	  			  pw.print("<font face='verdana'>");
   	  	
   	  		while ( rs.next() ) {
   	  		String log = rs.getString("login");
   	  		String mdp= rs.getString("mdp");
   	  		String nom = rs.getString("nom");
   	  		String prenom = rs.getString("prenom");
   	  		String adresse = rs.getString("adresse");
   	  		String email = rs.getString("email");
   	  		String tel = rs.getString("tel");
   	  		String datenaiss = rs.getString("datenaiss");
   	  		String role = rs.getString("role");
   	  		pw.println("<p>"
   	  				+"login :"+log+"</br>"
   	  				+"mdp :"+mdp+"</br>"
   	  				+"nom :"+nom+"</br>"
   	  				+"prenom :"+prenom+"</br>"
   	  				+"adresse :"+adresse+"</br>"
   	  				+"email :"+email+"</br>"
   	  				+"tel :"+tel+"</br>"
   	  				+"date de naissance :"+datenaiss+"</br>"
   	  				+"role :"+role+"</br>"
   	  				+"</p>");
   	  		}
   	  		
   	  		
   	     	        

   	              pw.print("</font>");
   	              pw.close();
   	  			conn.close();
   	        	}
   	        	
   	        }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            
        e.printStackTrace();
        }
        
	
	
    }


}