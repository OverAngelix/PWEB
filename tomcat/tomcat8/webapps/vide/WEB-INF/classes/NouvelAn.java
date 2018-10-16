import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.time.*;
import java.time.temporal.*;

@WebServlet("/servlet-NouvelAn")
public class NouvelAn extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet AN</title>" );
    out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );
    out.println( "<h1>TEST DE MON NOUVEL AN</h1>" );
	
	
	 out.println( "<p>TEMPS RESTANT :"+ second()+" SECONDES AVANT LA PROCHAINE ANNEE</p>" );
    out.println( "<h2>C'EST TROOOP BEAU</h2>" );
    out.println( "</center> </body>" );
  }
	
	public long second(){
	LocalDateTime today = LocalDateTime.now();
	LocalDateTime newYear = LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0);
	Duration delay = Duration.between(today, newYear);
	long seconds = delay.get( ChronoUnit.SECONDS );
	return seconds;
	}
  
}