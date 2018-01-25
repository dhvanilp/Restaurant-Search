import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","mahim23");
			con.prepareStatement("CREATE DATABASE IF NOT EXISTS `test`").execute();
			con.prepareStatement("USE `test`").execute();
		}catch(Exception e){System.out.println(e);}
		return con;
	}

}
