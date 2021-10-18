package edwardboca_1934440;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqliteConnection {

	
	
	public static Connection dbConnector()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Studentdb.db");
			
			return conn;
			
		}
		
		catch(Exception e)
		{
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
		
	}
	
}
