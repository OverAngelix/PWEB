
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;


public class ListeRencontre extends HttpServlet
{
	public static boolean triop = false;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost:9092/~/test";
    static final String USER = "sa";
    static final String PASS = "";
    static String monEquipePref="";
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Connection conn = null;
       
       
        // Creer
        if(req.getParameter("equipe") != null){
        Cookie MonCookie = new Cookie("equipes", req.getParameter("equipe"));
        MonCookie.setMaxAge(100);
        res.addCookie(MonCookie);
        monEquipePref= req.getParameter("equipe");
        }else {
        // Lire
        Cookie[] cookies = req.getCookies();
        for(int i=0; i < cookies.length; i++)
        {
        	Cookie MonCookie = cookies[i];
        if (MonCookie.getName().equals("equipes"))
        {
        monEquipePref= cookies[i].getValue();
        }
        	}
        }
        
        	
        
        	
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           String tri= req.getParameter("tri");
           String sql;
           boolean triage=false;
           boolean sort=false;
           
           if(req.getParameter("plop") == null || req.getParameter("plop").equals("")){}
      	   else {sort=true;}
           if(req.getParameter("tri") == null || req.getParameter("tri").equals("")){}
           else {triage=true;}   
       
           if(triage==true && sort==true) {
        	   sql = "SELECT * from rencontres where eq1="+ monEquipePref+"OR eq2="+monEquipePref+"order by "+tri+" "+req.getParameter("plop");
           }else if(triage==true && sort==false) {
        	   sql = "SELECT * from rencontres where eq1="+monEquipePref +"OR eq2="+monEquipePref+" order by "+tri;
           }else if(triage==false && sort==true) {
        	   sql = "SELECT * from rencontres  where eq1="+monEquipePref +"OR eq2="+monEquipePref;
           }else {
        	   sql = "SELECT * from rencontres  where eq1="+monEquipePref +"OR eq2="+monEquipePref;
           }
           
           PreparedStatement ps = conn.prepareStatement( sql );
				ResultSet rs = ps.executeQuery();
      


            PrintWriter pw = res.getWriter();
            
			  pw.print("<font face='verdana'>");
			 pw.print("<a href=http://localhost:8080/Tout/servlet-MonEquipe> MON EQUIPE</a>");
			   pw.print("<table>");
			   
           pw.println("<table>");
		   int clubp=0;
		   if(!triop) {
			   pw.println("<tr> <th> <a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=num_match&plop=asc>num_match </a></th>"
						+ " <th><a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=eq1&plop=asc>eq1</a> </th>  "
						+ "<th><a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=eq2&plop=asc>eq2 </a> </th> "
						+ " <th><a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=jour&plop=asc>jour </a>  </th>  "
						+ "<th> <a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=sc1&plop=asc>sc1 </a> </th> "
						+ " <th><a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=sc2&plop=asc>sc2 </a> </th></tr>");
			   triop=true;
		   }else {
			   pw.println("<tr> <th> <a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=num_match&plop=desc>num_match </a></th>"
						+ " <th><a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=eq1&plop=desc>eq1</a> </th>  "
						+ "<th><a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=eq2&plop=desc>eq2 </a> </th> "
						+ " <th><a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=jour&plop=desc>jour </a>  </th>  "
						+ "<th> <a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=sc1&plop=desc>sc1 </a> </th> "
						+ " <th><a href=http://localhost:8080/Tout/servlet-ListeSimple?tri=sc2&plop=desc>sc2 </a> </th></tr>");
			   triop=false;
		   }
	
		while ( rs.next() ) {
		pw.println("<tr>");
		int num_match = rs.getInt("num_match");
		int eq1  = rs.getInt("eq1");
		int eq2 = rs.getInt("eq2");
		int sc1 = rs.getInt("sc1");
		int sc2 = rs.getInt("sc2");
		Date jour = rs.getDate("jour");
		pw.println("<td>" + num_match + "</td>" + "<td>" + eq1 + "</td>" + "<td>" + eq2 + "</td>" + "<td>" + jour + "</td>" + "<td>" + sc1 + "</td>" + "<td>" + sc2 + "</td><td>"+"<input type=hidden name=supp value="+num_match+"><a href=http://localhost:8080/Tout/servlet-Delete?supp="+num_match+"><input type=button value=supprimer></a>"+"</td>");
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