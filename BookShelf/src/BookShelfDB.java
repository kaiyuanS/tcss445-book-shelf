import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookShelfDB {
	private static String userName = "root";
	private static String password = "";
	private static String serverName = "localhost";
	private static Connection conn;
	private List<Publisher> pubList;
	private List<PatronRecord> prList;
	
	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		
		conn = DriverManager.getConnection("jdbc:" + "mysql" + "//"
				+ serverName + "/", connectionProps);
		
		System.out.println("Connected to database");
	}
	
	public List<Publisher> getPublishers() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select "
					 + "publisherName"
					 + ", publisherStreet"
					 + ", publisherCity"
					 + ", publisherState"
					 + ", publisherZip"
					 + ", publisherCountry"
					 + ", publisherFounded"
					 + " from BookShelf.Publisher";
		
		pubList = new ArrayList<Publisher>();
		try {
			stmt = conn.createStatement();
			ResultSet  rs = stmt.executeQuery(query);
			while(rs.next()) {
				String publisherName = rs.getString("publisherName");
				String publisherStreet = rs.getString("publisherStreet");
				String publisherCity = rs.getString("publisherCity");
				String publisherState = rs.getString("publisherState");
				String publisherZip = rs.getString("publisherZip");
				String publisherCountry = rs.getString("publisherCountry");
				int publisherFounded = rs.getInt("publisherFounded");
				
				Publisher publisher = new Publisher(publisherName, publisherStreet,
									  publisherCity, publisherState, publisherZip,
									  publisherCountry, publisherFounded);
				
				pubList.add(publisher);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return pubList;
	}
}
