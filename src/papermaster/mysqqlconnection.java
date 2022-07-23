package papermaster;
import java.sql.Connection;
import java.sql.DriverManager;
public class mysqqlconnection {
	@SuppressWarnings("exports")
	public static Connection getConnection()
	{
		Connection con=null;
			try{	
																		//Database, uid, pwd-khali:  ""
				con=DriverManager.getConnection("jdbc:mysql://localhost/newspaper","root","");
				System.out.println("Connnneeeccccttteeddddd");
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			return con;
			
	}
    
	public static void main(String[] args) 
	{
		System.out.println("***");
		getConnection();

	}
}
