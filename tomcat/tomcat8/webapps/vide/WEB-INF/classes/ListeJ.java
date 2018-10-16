// Servlet Test.java  de test de la configuration
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

@WebServlet("/servlet-ListeJ")
public class ListeJ extends HttpServlet
{
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost:9092/~/TP2servlet";
    static final String USER = "sa";
    static final String PASS = "";

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Connection conn = null;
        
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
            String sql = "SELECT * from joueurs order by  club";
           PreparedStatement ps = conn.prepareStatement( sql );
				ResultSet rs = ps.executeQuery();
      


            PrintWriter pw = res.getWriter();
			  pw.print("<font face='verdana'>");
			   pw.print("<table>");
			   
           pw.println("<table>");
		   int clubp=0;
	pw.println("<tr> <th> numjoueur </th> <th> nomjoueur </th>  <th> pays </th>  <th> poste </th>  <th> maillot </th>  <th> daten </th>  <th> club </th>  <th> salaire </th> </tr>");
		while ( rs.next() ) {
		pw.println("<tr>");
		int numjoueur = rs.getInt("num_joueur");
		String nomjoueur = rs.getString("nom_joueur");
		String pays = rs.getString("pays");
		String poste = rs.getString("poste");
		int maillot = rs.getInt("maillot");
		Date datenai = rs.getDate("date_naissance");
		int club = rs.getInt("club");
		int salaire = rs.getInt("salaire");
				/*if ( calculateAge(daten,n) > 30 ) {
			pw.println("<td bgcolor= red>" + numjoueur + "</td>" + "<td>" + nomjoueur + "</td>" + "<td>" + pays + "</td>" + "<td>" + poste + "</td>" + "<td>" + maillot + "</td>" + "<td>" + daten + "</td>" + "<td>" + club + "</td>" + "<td>" + salaire + "</td>");
		} else {*/
			pw.println("<td>" + numjoueur + "</td>" + "<td>" + nomjoueur + "</td>" + "<td>" + pays + "</td>" + "<td>" + poste + "</td>" + "<td>" + maillot + "</td>" + "<td>" + datenai + "</td>" + "<td>" + club + "</td>" + "<td>" + salaire + "</td>");
		//}
		pw.println("</tr>");
		
		}
		
		pw.println("</table>");
          

            pw.print("</font>");
            pw.close();
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