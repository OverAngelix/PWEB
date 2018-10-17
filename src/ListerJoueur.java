import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;
import java.sql.*;


public class ListerJoueur extends HttpServlet
{ 
	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:tcp://localhost:9092/~/test";
	static final String USER = "sa";
	static final String PASS = "";
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		Connection con = null;
		
		try{
			HttpSession session = req.getSession( true );
			Class.forName("org.h2.Driver");
	        con = DriverManager.getConnection(DB_URL, USER, PASS);
			out.println("<head><title>Liste Joueurs</title>");
			out.println("<META content= \"charset=Utf-8\"></head><body><center>");
		    out.println("<p><a href=\"./servlet-ListerJoueur?poste=ATT\">ATT</a> <a href=\"./servlet-ListerJoueur?poste=GAR\">GAR</a> <a href=\"./servlet-ListerJoueur?poste=MIL\">MIL</a> <a href=\"./servlet-ListerJoueur?poste=DEF\">DEF</a></p>");
				
		        
			String poste = req.getParameter("poste");
			if(poste == null) poste = "ATT";

			String query = "SELECT * from joueurs WHERE poste='"+poste+"'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			out.println("<h1>Poste: "+poste+"</h1>");
			out.println("<table border=1>");
			
			out.println("<tr>");
			out.println("<th style=\"border: black groove 1px\">Maillot</th>");
			out.println("<th style=\"border: black groove 1px\">Nom</th>");
			out.println("<th style=\"border: black groove 1px\"></th>");
			out.println("</tr>");
			if(session.getAttribute("listeJoueurs") == null) {
				session.setAttribute("listeJoueurs",new ArrayList<String>());
			}
			
			while(rs.next()){
				if(((ArrayList) session.getAttribute("listeJoueurs")).contains(rs.getString("nom_joueur"))){
				}else {
				out.println("<tr>");
				out.println("<td style=\"border: black groove 1px\">"+rs.getString("maillot")+"</td>");
				out.println("<td style=\"border: black groove 1px\">"+rs.getString("nom_joueur")+"</td>");
				out.println("<td style=\"border: black groove 1px\"><a href=\"./servlet-ChoisirJoueur?nom="+rs.getString("nom_joueur")+"\">Choisir</a></td>");
				out.println("</tr>");
				}
			}
			
			out.println("</table>");
			out.println("</body>");
			
		} 
		catch (Exception e) {
			out.println(e.getMessage());
			} 
		finally
		{
			try {con.close();} catch(Exception e2) {}
		}
	}
	
}