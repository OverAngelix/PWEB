// Servlet Test.java  de test de la configuration
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

@WebServlet("/servlet-ListeJ")
public class DeleteJoueurArray extends HttpServlet
{
  

    protected void service(HttpServletRequest req, HttpServletResponse res) {
    String nom = req.getParameter("supp");
	HttpSession session = req.getSession();
    ((ArrayList) session.getAttribute("listeJoueurs")).remove(nom);
	try {
		res.sendRedirect("servlet-ChoisirJoueur");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    }


}