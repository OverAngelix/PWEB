import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//@WebServlet("/servlet-Palette")
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
	
	for(int i = 0;i<15;i++){
		out.println( " <tr>");
		for(int j = 0;j<15;j++){
			if(req.getParameter("r") == null || req.getParameter("r").equals("")){
				out.println( " <td bgcolor=#0"+convert(i)+""+convert(j)+"></td>");
			}else if (Integer.parseInt(req.getParameter("r"))>15 || Integer.parseInt(req.getParameter("r"))<0){
				out.println( " <td bgcolor=#0"+convert(i)+""+convert(j)+"></td>");
			}else
				out.println( " <td bgcolor=#"+convert(Integer.parseInt(req.getParameter("r")))+""+convert(i)+""+convert(j)+"></td>");
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