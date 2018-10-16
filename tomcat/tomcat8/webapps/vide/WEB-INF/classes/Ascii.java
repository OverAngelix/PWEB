import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet-Ascii")
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
	for(int i = 32;i<=255;i++){
		out.println( " <tr>");
		for(int j = 0;j<2;j++){
			if(j==0){
				out.println( " <td> "+ i +" </td>");
			}else{
				out.println( " <td> "+ (char) i +" </td>");
			}
			
		}
		out.println( " </tr>");
	}
	out.println( "</table>" );

	
    out.println( "<h2>C'EST TROOOP BEAU</h2>" );
    out.println( "</center> </body>" );
  }

  
}