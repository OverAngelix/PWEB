import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet-Fibo")
public class Fibo extends HttpServlet
{
  public void service( HttpServletRequest req, HttpServletResponse res )
  throws ServletException, IOException
  {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println( "<head><title>servlet FIBO</title>" );
    out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );
    out.println( "<h1>SUITE DE FIBO</h1>" );
	for(int i = 0;i<30;i++){
		 out.println( "<p>"+fib(i)+"</p>" );
	}
    out.println( "<h2></h2>" );
    out.println( "</center> </body>" );
  }
  
	  
   public int fib(int n) 
    {
      if (n < 2) return(n);
      return( fib(n-2) + fib(n-1) );
    }
  
}