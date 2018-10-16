import java.io.*;
import java.sql.*;
import java.util.*;

public class DS
{
	 String url;
	  String nom;
	  String mdp ;
	public DS() throws Exception{
 	Properties prop = new Properties();
	InputStream input = null;
	 
	input = new FileInputStream("DONNEES.properties");
	prop.load(input);
    Class.forName(prop.getProperty("driver"));
    url = prop.getProperty("url");
   nom = prop.getProperty("nom");
    mdp = prop.getProperty("mdp");
	}
	
	Connection connect()throws Exception{
		return DriverManager.getConnection(url,nom,mdp);
	}
}
