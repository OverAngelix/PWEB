
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

public class CreeRencontre extends HttpServlet
{
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost:9092/~/TP2servlet";
    static final String USER = "sa";
    static final String PASS = "";

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	PrintWriter pw = res.getWriter();
    	pw.print("<html><body>");
    	pw.print("<h1>REMPLIR FORMULAIRE</h1>");
        pw.print("<form action=servlet-CreeRencontre method=post>");
    	pw.print("NUM MATCH<input name = Num_match type=text>");
    	pw.print("EQ1<input name = eq1 type=text>");
  		pw.print("EQ2<input name = eq2 type=text>");
   		pw.print("JOUR<input name = jour type=text>");
   		pw.print("SC1<input name = sc1 type=text>");
   		pw.print("SC2<input name = sc2 type=text>");
   		pw.print("<br/>");
   		pw.print("<input type=submit>");
   		pw.print("</form>");
   		pw.print("</body></html>");
   		
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Connection conn = null;
        
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
            String[] data = new String[2];
            String d = req.getParameter("jour");
            data = d.split("/");
            Date dinsert = new Date(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0]));
            String query = "INSERT INTO rencontres (num_match,eq1,eq2,jour,sc1,sc2) VALUES (?,?,?,?,?,?)";
    		PreparedStatement ps = conn.prepareStatement( query );
    		ps.setInt    (1, Integer.parseInt(req.getParameter("Num_match")));
    		ps.setInt (2,  Integer.parseInt(req.getParameter("eq1")));
    		ps.setInt (3,  Integer.parseInt(req.getParameter("eq2")));
    		ps.setDate (4, dinsert);
    		ps.setInt (5,  Integer.parseInt(req.getParameter("sc1")));
    		ps.setInt (6,  Integer.parseInt(req.getParameter("sc2")));
    		ps.executeUpdate();
    		conn.close();
			res.sendRedirect("servlet-ListeSimple");
			
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	
	
    }


}