
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.ArrayList;

public class Authent extends HttpServlet
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
        Connection conn = null;
        
        try {
        	HttpSession session = req.getSession( true );
        	//session.setMaxInactiveInterval(10); 10 secondes de session
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
            String sql = "SELECT login,mdp,role from PERSONNE where login='"+req.getParameter("log")+"' AND mdp='"+req.getParameter("mdp")+"'";
            PreparedStatement ps = conn.prepareStatement( sql );
			ResultSet rs = ps.executeQuery();
			if(rs.first()) {
		    		session.setAttribute("user",req.getParameter("log"));
		    		session.setAttribute("role", rs.getString("role"));
		    		System.out.println(rs.getString("role"));
		    		
			}else{
				session.setAttribute("user", null);
			}
			res.sendRedirect("servlet-Statue");
    		conn.close();
			
			
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	
	
    }


}