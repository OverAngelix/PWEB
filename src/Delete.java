// Servlet Test.java  de test de la configuration
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

@WebServlet("/servlet-ListeJ")
public class Delete extends HttpServlet
{
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost:9092/~/test";
    static final String USER = "sa";
    static final String PASS = "";

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Connection conn = null;
        
        try {
        	String del = req.getParameter("supp");
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            int delint = Integer.parseInt(del);
            String sql = "delete from rencontres where num_match="+delint;
           PreparedStatement ps = conn.prepareStatement( sql );
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