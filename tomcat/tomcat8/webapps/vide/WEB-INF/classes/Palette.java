import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet-Palette")
public class Palette extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
	
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet Pallete</title>" );
    out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );
    out.println( "<h1>TEST DE MA PALETTE</h1>" );
	out.println( "<table border=6 cellspacing=6 cellpadding=10>" );
	 String n = req.getParamter("r"); 
	for(int i = 0;i<15;i++){
		out.println( " <tr>");
		for(int j = 0;j<15;j++){
			out.println( " <td bgcolor=#"+n+""+convert(i)+""+convert(j)+"></td>");
		}
		out.println( " </tr>");
	}
	out.println( "</table>" );

	
    out.println( "<h2>C'EST TROOOP BEAU</h2>" );
    out.println( "</center> </body>" );
  }
  
	  
   public static String convert(int n) {
  
	String str = Integer.toHexString(n);
	return str;
}
  
}