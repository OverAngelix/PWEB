
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.ArrayList;

public class Statue extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost:9092/~/test";
    static final String USER = "sa";
    static final String PASS = "";
    
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		Connection con = null;
		
		try{
			HttpSession session = req.getSession( true );
			Class.forName("org.h2.Driver");
	        con = DriverManager.getConnection(DB_URL, USER, PASS); 
	        if(session.getAttribute("user") != null) {
	        	res.sendRedirect("./servlet-Menu");
	        }else {
	        	res.sendRedirect("login.html");
	        }
			
    		con.close();
			
			
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	
	
    }


}