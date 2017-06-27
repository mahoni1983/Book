import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Singleton for MySQL queries
 * 
 * @author Dmitrij Starikovic
 */

public class DBase 
{
	String CONNECTION_STRING = "jdbc:mysql://localhost:3306/BOOK";
	String USER = "root";
	String PASS = "123456";
	
	private static DBase instance = null;
	/**
	 * get dbase
	 * @return dbase instance
	 */
	public static DBase getInstance() 
	{
		if (instance == null) {
			instance = new DBase();
		}
		return instance;
	}
	
	private Connection connection = null;
	/**
	 * get connection
	 * @return connection
	 */
	public Connection getConnection () 
	{
		return connection;
	}

	/**
	 * private constructor
	 */
	private DBase () {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASS);
		}
		catch (Exception e) {
			System.out.println("Connection problems--> " + e.getMessage());
		}	
	}
	
}
