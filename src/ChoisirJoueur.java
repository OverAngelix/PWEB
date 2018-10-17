import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.time.*;
import java.time.temporal.*;
import java.sql.*;

@WebServlet("/servlet-ChoisirJoueur")
public class ChoisirJoueur extends HttpServlet
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
			out.println("<head><title>Liste des Joueurs</title>");
			out.println("<META content= \"charset=Utf-8\"></head><body><center>");
			out.println("<a href=\"./servlet-ListerJoueur\">Retour a la Liste</a>");
			out.println("\"<a href=\"./servlet-Reset\">Remise a zero des joueurs</a>");    
			Class.forName("org.h2.Driver");
	        con = DriverManager.getConnection(DB_URL, USER, PASS); 
			
			String nom = req.getParameter("nom");
		

			if(nom != null){
			    String query = "SELECT * from joueurs WHERE nom_joueur='"+nom+"'";
			    PreparedStatement ps = con.prepareStatement(query);
			    ResultSet rs = ps.executeQuery();

		    	if(session.getAttribute("listeJoueurs") == null) {
		    		session.setAttribute("listeJoueurs",new ArrayList<String>());
		    	}
		    	((ArrayList) session.getAttribute("listeJoueurs")).add(nom);
			}

			//Affichage de l'ArrayList
			if(session.getAttribute("listeJoueurs") != null){
			    String queryTab = "SELECT * from joueurs WHERE ";
			    
			    for(int i = 0; i < ((ArrayList) session.getAttribute("listeJoueurs")).size(); i++){
					queryTab += " nom_joueur='"+((ArrayList) session.getAttribute("listeJoueurs")).get(i)+"' ";
					if(i < ((ArrayList) session.getAttribute("listeJoueurs")).size()-1) queryTab += " OR ";
				}

			    PreparedStatement ps2 = con.prepareStatement(queryTab);
			    ResultSet rs2 = ps2.executeQuery();
			    out.println("<table border=1>");
			
			    out.println("<tr>");
			    out.println("<th style=\"border: black groove 1px\">Maillot</th>");
			    out.println("<th style=\"border: black groove 1px\">Nom</th>");
			    out.println("<th style=\"border: black groove 1px\">Poste</th>");
			    out.println("<th style=\"border: black groove 1px\"></th>");
			    out.println("</tr>");
			    while(rs2.next()){
					out.println("<tr>");
					out.println("<td style=\"border: black groove 1px\">"+rs2.getString("maillot")+"</td>");
					out.println("<td style=\"border: black groove 1px\">"+rs2.getString("nom_joueur")+"</td>");
					out.println("<td style=\"border: black groove 1px\">"+rs2.getString("poste")+"</td>");
					out.println("<td style=\"border: black groove 1px\"><a href=\"./servlet-DeleteJoueurArray?supp="+rs2.getString("nom_joueur")+"\">SUPPRIMER</a></td>");
					out.println("</tr>");
			    }
			}
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