import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

public class Ascii extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;charset=ISO-8859-1");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet ASCII</title>" );
    out.println( "<META content=\"charset=ISO-8859-1\"></head><body><center>" );
    out.println( "<h1>TEST DE MA ASCII</h1>" );
	out.println( "<table border=6 cellspacing=6 cellpadding=10>" );
	int param;
	if(req.getParameter("Col") == null || req.getParameter("Col").equals("")){
		param=1;
	}
	else {
		param=Integer.parseInt(req.getParameter("Col"));
	}
	 
	int col = param;
	int inc = (255-32)/param;
	int val=32;
	int valinit=32;
	int valu=0;
	
	while (valu<inc){
		out.println( " <tr>");
		int cpt=0;
		val = valinit+valu;
		while(cpt<col ){
			out.println( " <td> "+ val +" </td>");			
			out.println( " <td> "+ (char) val +" </td>");
			cpt++;
			val+=inc;
		}
		out.println( " </tr>");
		valu++;
		
	}
	
		
		
	
	out.println( "</table>" );

	
    out.println( "<h2>C'EST TROOOP BEAU</h2>" );
    out.println( "</center> </body>" );
  }

  
}
