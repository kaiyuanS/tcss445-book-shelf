import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
					 + " from LibraryDB.Publisher";
		
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
	
	public List<Publisher> getPublisherByName(String publisherName) {
		List<Publisher> filterList = new ArrayList<Publisher>();
		try {
			pubList = getPublishers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Publisher pub : pubList) {
			if (pub.getName().toLowerCase().contains(publisherName.toLowerCase())) {
				filterList.add(pub);
			}
		}
		
		return filterList;
	}
	
	/// other publisher sql statements
	
	public List<PatronRecord> getPatronRecords() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		
		Statement stmt = null;
		String query = "select "
					 + "recordID"
					 + ", patronID"
					 + ", bookID"
					 + ", borrowBy"
					 + ", returnBy"
					 + " from LibraryDB.PatronRecord";
		
		prList = new ArrayList<PatronRecord>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int recordID = rs.getInt("recordID");
				int patronID = rs.getInt("patronID");
				int bookID = rs.getInt("bookID");
				Date borrBy = rs.getDate("borrowBy");
				Date retBy = rs.getDate("returnBy");
				
				Calendar borrowBy = new GregorianCalendar();
				borrowBy.setTime(borrBy);
				
				Calendar returnBy = null;
				
				if (retBy != null) {
					returnBy = new GregorianCalendar();
					returnBy.setTime(retBy);
				}
				
				PatronRecord pr = new PatronRecord(recordID, patronID, bookID, borrowBy, returnBy);
				
				prList.add(pr);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return prList;		 
	}
}
