
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;


public class MonEquipe extends HttpServlet
{
	
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost:9092/~/test";
    static final String USER = "sa";
    static final String PASS = "";
    public ArrayList<String> listeEq=new ArrayList<>();
    public ArrayList<Integer> numlisteEq=new ArrayList<>();

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Connection conn = null;
        
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           String sql= "SELECT * from equipes ";

           PreparedStatement ps = conn.prepareStatement( sql );
				ResultSet rs = ps.executeQuery();
            PrintWriter pw = res.getWriter();
			  pw.print("<font face='verdana'>");
			   pw.print("<table>");
		while ( rs.next() ) {
		listeEq.add(rs.getString("nom_equipe"));
		numlisteEq.add(rs.getInt("num_equipe"));
		}
		
		
		 pw.print("<h1>CHOIX DE MON EQUIPE PREFERE</h1>");
		 pw.print("<form action=servlet-ListeRencontre method=get>");
		pw.print("Nombre de colonne ? ");
		 pw.print("<select name = equipe>");
		 for(int i =0;i<listeEq.size();i++) {
			 pw.print("<option value="+numlisteEq.get(i)+" >"+ listeEq.get(i)+" </option>");
			}
		 pw.print("</select> <br/> <input type=submit></form>");
		 pw.println("<a href=http://localhost:8080/Tout/servlet-ListeSimple> SUPPRIMER MON EQUIPE</a>");
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