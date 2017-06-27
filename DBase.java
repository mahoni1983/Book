import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection to MySQL server
 * 
 * @author Eugene
 * 
 */

public class DBase {
	String CONNECTION_STRING = "jdbc:mysql://localhost:3306/BOOK?useSSL=false";
	String USER = "root";
	String PASS = "root";
	private static DBase instance = null;
	private Connection connection = null;

	public static DBase getInstance() {
		if (instance == null)
			instance = new DBase();
		return instance;
	}

	private DBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(CONNECTION_STRING, USER,
					PASS);
		} catch (Exception e) {
			e.printStackTrace();
			connection = null;
			System.out.println("Connection to SQL server problems:"
					+ e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

}
