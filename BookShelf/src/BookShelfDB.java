import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BookShelfDB {
	private static String userName = "_445team15";
    private static String password = "dubdap,";
    private static String serverName = "cssgate.insttech.washington.edu";
	private static Connection conn;
	private List<Publisher> pubList;
	private List<PatronRecord> prList;
	private List<Book> bookList;
	private List<BookInfo> bookInfoList;
	private List<Patron> patronList;
	
	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		
		conn = DriverManager.getConnection("jdbc:" + "mysql:" + "//"
				+ serverName + "/_445team15", connectionProps);
		
		System.out.println("Connected to database");
	}
	
	////////////////////////////////////////////////////////
	/// PUBLISHER METHODS //////////////////////////////////
	////////////////////////////////////////////////////////
	
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
					 + " from _445team15.Publisher";
		
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
	
	public void addPublisher(Publisher aPublisher) {
		String sql = "INSERT INTO _445team15.Publisher VALUES "
                + "(?, ?, ?, ?, ?, ?, ?);";
        
        PreparedStatement preparedStatement = null;
        try {
            if (conn == null) {
                createConnection();
            }
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, aPublisher.getName());
            preparedStatement.setString(2, aPublisher.getStreet());
            preparedStatement.setString(3, aPublisher.getCity());
            preparedStatement.setString(4, aPublisher.getState());
            preparedStatement.setString(5, aPublisher.getZipCode());
            preparedStatement.setString(6, aPublisher.getCountry());
            preparedStatement.setInt(7, aPublisher.getYearFounded());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
	}
	
	public void upDatePublisher(String aPublisherName, String anAttribute, Object aData) {
		String sql = "UPDATE _445team15.Publisher SET "
                + anAttribute + " = ? WHERE publisherName = ?;";
        
        PreparedStatement preparedStatement = null;
        try {
            if (conn == null) {
                createConnection();
            }
            preparedStatement = conn.prepareStatement(sql);
            if (aData instanceof String) {
            	preparedStatement.setString(1, (String)aData);
            } else {
            	preparedStatement.setInt(1, (int)aData);
            }
            
            preparedStatement.setString(2, aPublisherName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
	}
	
	public void removePublisher(String aPublisherName) {
		String sql = "DELETE FROM _445team15.Publisher WHERE publisherName = ?;";
        
        PreparedStatement preparedStatement = null;
        try {
            if (conn == null) {
                createConnection();
            }
            preparedStatement = conn.prepareStatement(sql);
            
            preparedStatement.setString(1, aPublisherName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
	}
	
	/// other publisher sql statements
	
	////////////////////////////////////////////////////////////////////
	////// PATRON RECORD METHODS ///////////////////////////////////////
	////////////////////////////////////////////////////////////////////
	
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
					 + " from _445team15.PatronRecord";
		
		prList = new ArrayList<PatronRecord>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int recordID = rs.getInt("recordID");
				int patronID = rs.getInt("patronID");
				int bookID = rs.getInt("bookID");
				Date borrowBy = rs.getDate("borrowBy");
				Date returnBy = rs.getDate("returnBy");
				
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
	
	public List<PatronRecord> getPatronRecordNonReturnedBooks() {
		List<PatronRecord> nonReturnedBooks = new ArrayList<PatronRecord>();
		
		try {
			prList = getPatronRecords();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (PatronRecord pr : prList) {
			if (pr.getReturnByDate() == null) {
				nonReturnedBooks.add(pr);
			}
		}
		
		return nonReturnedBooks;
	}
	
	/*
	public List<PatronRecord> getPatronRecordByPatronID(int patronID) {
		List<PatronRecord> individualPatronRecords = new ArrayList<PatronRecord>();
		
		try {
			prList = getPatronRecords();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (PatronRecord pr : prList) {
			if (pr.getPatronID() == patronID) {
				individualPatronRecords.add(pr);
			}
		}
		
		return individualPatronRecords;
	}
	*/
	
	public List<PatronRecord> getPatronRecordByPatronID(int patronID) throws SQLException {
		if (conn == null) {
			createConnection();
		}
		
		Statement stmt = null;
		
		String query = "select *"
					 + " from PatronRecord"
					 + " where PatronRecord.patronID = " + patronID;
		
		prList = new ArrayList<PatronRecord>();
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int recordNum = rs.getInt("recordInt");
				Date borrowBy = rs.getDate("borrowBy");
				int patronId = rs.getInt("patronID");
				int bookId = rs.getInt("bookID");
				Date returnBy = rs.getDate("returnBy");
				
				PatronRecord pr = new PatronRecord(recordNum, patronId, bookId, borrowBy, returnBy);
				
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
	
	public List<PatronRecord> getPatronRecordByBookID(int bookID) throws SQLException {
		if (conn == null) {
			createConnection();
		}
		
		Statement stmt = null;
		
		String query = "select *"
					 + " from PatronRecord"
					 + " where PatronRecord.bookID = " + bookID;
		
		prList = new ArrayList<PatronRecord>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int recordNum = rs.getInt("recordInt");
				Date borrowBy = rs.getDate("borrowBy");
				int patronId = rs.getInt("Patron_patronID");
				int bookId = rs.getInt("Book_bookID");
				Date returnBy = rs.getDate("returnBy");
				
				PatronRecord pr = new PatronRecord(recordNum, patronId, bookId, borrowBy, returnBy);
				
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
	

    /**
     * set return date
     * @param aRecordID
     * @param aDate
     */
    public void setReturnDate(int aRecordID, Calendar aDate) {
        String sql = "UPDATE _445team15.PatronRecord SET returnBy = ? WHERE recordID = ?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, new Date(aDate.getTimeInMillis()));
            preparedStatement.setInt(2, aRecordID);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    
    /**
     * get the record of a book
     * @param aBookID
     * @return
     */
    public List<PatronRecord> getBookRecord(int aBookID) {
        String sql = "SELECT * FROM _445team15.PatronRecord WHERE bookID = ?;";
        PreparedStatement preparedStatement = null;

        List<PatronRecord> orderList = new ArrayList<PatronRecord>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, aBookID);
            ResultSet recordResult = preparedStatement.executeQuery();
            while (recordResult.next()) {
                int recordID = recordResult.getInt("recordID");
                Date borrowBy = recordResult.getDate("borrowBy");
                int patronID = recordResult.getInt("patronID");
                int bookID = recordResult.getInt("bookID");
                Date returnBy = recordResult.getDate("returnBy");
                PatronRecord thePatronRecord = new PatronRecord(recordID, patronID, bookID, borrowBy, returnBy);
                orderList.add(thePatronRecord);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }
    
    /**
     * get the record of a patron
     * @param aBookID
     * @return
     */
    public List<PatronRecord> getPatronRecord(int aPatronID) throws SQLException {
        String sql = "SELECT * FROM _445team15.PatronRecord WHERE patronID = ?;";
        PreparedStatement preparedStatement = null;

        List<PatronRecord> orderList = new ArrayList<PatronRecord>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, aPatronID);
            ResultSet recordResult = preparedStatement.executeQuery();
            while (recordResult.next()) {
                int recordID = recordResult.getInt("recordID");
                Date borrowBy = recordResult.getDate("borrowBy");
                int patronID = recordResult.getInt("patronID");
                int bookID = recordResult.getInt("bookID");
                Date returnBy = recordResult.getDate("returnBy");
                PatronRecord thePatronRecord = new PatronRecord(recordID, patronID, bookID, borrowBy, returnBy);
                orderList.add(thePatronRecord);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }
	

    /**
     * place a new order
     */
    public void placeOrder(int aRecordID, Date aDate, int aPatronID, int aBookID) {
    	String sql = "INSERT INTO _445team15.PatronRecord VALUES "
                + "(?, ?, ?, ?, NULL);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, aRecordID);
            preparedStatement.setDate(2, aDate);
            preparedStatement.setInt(3, aPatronID);
            preparedStatement.setInt(4, aBookID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    

    /**
     * get next recordID
     * @return
     * @throws SQLException
     */
    public int getNextOrderID() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        
        String sql = "SELECT MAX(recordID) FROM _445team15.PatronRecord;";
        Statement statement = null;
        int maxOrderID = 0;
        try {
            statement = conn.createStatement();
            ResultSet patronResult = statement.executeQuery(sql);
            while (patronResult.next()) {
            	maxOrderID = patronResult.getInt("MAX(orderID)");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        
        return maxOrderID + 1;
    }
    
    public void editPatronRecord(int row, String columnName, Object data) {
    	
    	PatronRecord patronRecord = prList.get(row);
    	int recordID = patronRecord.getRecordID();
    	int patronID = patronRecord.getPatronID();
    	int bookID = patronRecord.getBookID();
    	
    	String sql = "update _445team15.PatronRecord set " + columnName + " = ? where recordID = ? "
    			   + "and patronID = ? and bookId = ? ";
    	System.out.println(sql);
    	PreparedStatement prepStatement = null;
    	try {
    		prepStatement = conn.prepareStatement(sql);
    		if (data instanceof String) {
    			prepStatement.setString(1, (String)data);
    		} else if (data instanceof Integer) {
    			prepStatement.setInt(1,  (Integer)data);
    		} else if (data instanceof Date) {
    			prepStatement.setDate(1, (Date)data);
    		} else if (data == null) {
    			prepStatement.setNull(1, Types.NULL);
    		}
    		
    		prepStatement.setInt(2, recordID);
    		prepStatement.setInt(3, patronID);
    		prepStatement.setInt(4, bookID);
    		prepStatement.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e);
    		e.printStackTrace();
    	}
    }
    
    
    /////////////////////////////////////////////////////////////
    //// BOOK INFO METHODS //////////////////////////////////////
    /////////////////////////////////////////////////////////////
    
    /**
     * add new book info
     * @param aBookInfo
     */
    public void addBookInfo(BookInfo aBookInfo) {
        String sql = "INSERT INTO _445team15.BookInfo VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, aBookInfo.getISBN());
            preparedStatement.setString(2, aBookInfo.getTitle());
            preparedStatement.setInt(3, aBookInfo.getYear());
            preparedStatement.setString(4, aBookInfo.getAuthor());
            preparedStatement.setString(5, aBookInfo.getFormat());
            preparedStatement.setInt(6, aBookInfo.getPageNumber());
            preparedStatement.setString(7, aBookInfo.getLanguage());
            preparedStatement.setInt(8, aBookInfo.getBookshelfNumber());
            preparedStatement.setInt(9, aBookInfo.getLayerNumber());
            preparedStatement.setString(10, aBookInfo.getPublisherName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    

    public List<BookInfo> getBookInfo() throws SQLException {
    	if (conn == null) {
    		createConnection();
    	}

    	Statement stmt = null;
    	
		String query = "select "
					 + "ISBN"
					 + ", title"
					 + ", year"
					 + ", author"
					 + ", format"
					 + ", pageNumber"
					 + ", Language"
					 + ", bookshelfNumber"
					 + ", layerNumber"
					 + ", publisherName"
					 + " from _445team15.BookInfo";
		
		bookInfoList = new ArrayList<BookInfo>();
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String isbn = rs.getString("ISBN");
				String title = rs.getString("title");
				int year = rs.getInt("year");
				String author = rs.getString("author");
				String format = rs.getString("format");
				int pageNumber = rs.getInt("pageNumber");
				String language = rs.getString("Language");
				int bookshelfNumber = rs.getInt("bookshelfNumber");
				int layerNumber = rs.getInt("layerNumber");
				String publisherName = rs.getString("publisherName");
				
				BookInfo binfo = new BookInfo(isbn, title, year, author, 
								 format, pageNumber, language, bookshelfNumber,
								 layerNumber, publisherName);
				
				bookInfoList.add(binfo);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return bookInfoList;		 
	}
    
    public BookInfo getBookInfoByISBN(String isbn) throws NullPointerException {
    	BookInfo bookInfo = null;
    	try {
    		bookInfoList = getBookInfo();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	for (BookInfo binfo : bookInfoList) {
    		if (binfo.getISBN().equals(isbn)) {
    			bookInfo = binfo;
    		}
    	}
    	
    	return bookInfo;
    }
    
    /**
     * update a book info
     * @param anISBN
     * @param anAttribute
     * @param data
     */
    public void updateBookInfo(String anISBN, String anAttribute, Object data) {
    	
        String sql = "UPDATE _445team15.BookInfo SET "
                + anAttribute + " = ? WHERE ISBN = ?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (data instanceof Integer) {
            	preparedStatement.setInt(1, (int)data);
            } else {
            	preparedStatement.setString(1, (String)data);
            }
            preparedStatement.setString(2, anISBN);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// BOOK METHODS //////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    
    /**
     * add a new book
     * @param aBook
     */
    public void addBook(Book aBook) {
        String sql = "INSERT INTO _445team15.Book VALUES " + "(?, ?);";
        
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, aBook.getBookID());
            preparedStatement.setString(2, aBook.getISBN());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    

    public List<Book> getBooks() throws SQLException {
    	if (conn == null) {
    		createConnection();
    	} 
    	
    	Statement stmt = null;
		String query = "select "
					 + "bookID"
					 + ", ISBN"
					 + " from _445team15.Book";
		
		bookList = new ArrayList<Book>();
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int bookID = rs.getInt("bookID");
				String isbn = rs.getString("ISBN");
				
				Book book = new Book(bookID, isbn);
				
				bookList.add(book);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return bookList;		 
	}
    
    public Book getBookByID(int bookID) {
    	List<Book> booksByID = new ArrayList<Book>();
    	
    	try {
    		bookList = getBooks();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	Book tempBook = null;
    	
    	for (Book book : bookList) {
    		if (book.getBookID() == bookID) {
    			tempBook = book;
    			break;
    		}
    	}
    	
    	return tempBook;
    }
    

    /**
     * get next bookID
     * @return
     * @throws SQLException
     */
    public int getNextBookID() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        
        String sql = "SELECT MAX(bookID) FROM _445team15.Book;";
        Statement statement = null;
        int maxBookID = 0;
        try {
            statement = conn.createStatement();
            ResultSet patronResult = statement.executeQuery(sql);
            while (patronResult.next()) {
                maxBookID = patronResult.getInt("MAX(bookID)");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        
        return maxBookID + 1;
    }
    
    //////////////////////////////////////////////////////////////////
    /////// PATRON METHODS ///////////////////////////////////////////
    //////////////////////////////////////////////////////////////////
    
    /**
     * get a list of all patron
     * @return
     * @throws SQLException
     */
    public List<Patron> getPatrons() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        
        String sql = "SELECT * FROM _445team15.Patron;";
        Statement statement = null;

        patronList = new ArrayList<Patron>();
        try {
            statement = conn.createStatement();
            ResultSet patronResult = statement.executeQuery(sql);
            while (patronResult.next()) {
                int patronID = patronResult.getInt("patronID");
                String firstName = patronResult.getString("firstName");
                String lastName = patronResult.getString("lastName");
                String patronEmail = patronResult.getString("patronEmail");
                String phoneNumber = patronResult.getString("phoneNumber");
                String streetAddress = patronResult.getString("streetAddress");
                String City = patronResult.getString("City");
                String State = patronResult.getString("State");
                String zip = patronResult.getString("zip");
                Patron thePatron = new Patron(patronID, firstName, lastName, patronEmail,
                        phoneNumber, streetAddress, City, State, zip);
                patronList.add(thePatron);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return patronList;
    }
    

    /**
     * add new patron
     * @param aPatron
     */
    public void addPatron(Patron aPatron) {
        String sql = "INSERT INTO _445team15.Patron VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        
        PreparedStatement preparedStatement = null;
        try {
            if (conn == null) {
                createConnection();
            }
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, aPatron.getPatronID());
            preparedStatement.setString(2, aPatron.getFirstName());
            preparedStatement.setString(3, aPatron.getLastName());
            preparedStatement.setString(4, aPatron.getPatronEmail());
            preparedStatement.setString(5, aPatron.getPhoneNumber());
            preparedStatement.setString(6, aPatron.getStreetAddress());
            preparedStatement.setString(7, aPatron.getCity());
            preparedStatement.setString(8, aPatron.getState());
            preparedStatement.setString(9, aPatron.getZip());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    
    
    /**
     * delete a patron
     * @param aPatronID
     */
    public void removePatron(int aPatronID) {
        String sql = "DELETE FROM _445team15.Patron WHERE patronID = ?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, aPatronID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    
    /**
     * update a patron
     * @param aPatronID
     * @param anAttribute
     * @param data
     */
    public void updatePatron(int aPatronID, String anAttribute, String data) {
        String sql = "UPDATE _445team15.Patron SET "
                + anAttribute + " = ? WHERE patronID = ?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, data);
            preparedStatement.setInt(2, aPatronID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    
    public void editPatron(int row, String columnName, Object data) {
    	
    	Patron patron = patronList.get(row);
    	int patronId = patron.getPatronID();
    	String sql = "update _445team15.Patron set " + columnName + " = ? where patronID = ? ";
    	System.out.println(sql);
    	PreparedStatement prepStmt = null;
    	try {
    		prepStmt = conn.prepareStatement(sql);
    		prepStmt.setString(1, (String)data);
    		prepStmt.setInt(2, patronId);
    		
    		prepStmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e);
    		e.printStackTrace();
    	}
    }
    
    /**
     * get next patronID
     * @return
     * @throws SQLException
     */
    public int getNextPatronID() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        
        String sql = "SELECT MAX(patronID) FROM _445team15.Patron;";
        Statement statement = null;
        int maxPatronID = 0;
        try {
            statement = conn.createStatement();
            ResultSet patronResult = statement.executeQuery(sql);
            while (patronResult.next()) {
                maxPatronID = patronResult.getInt("MAX(patronID)");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return maxPatronID + 1;
    }
    
    Patron getPatronByID(int patronId) throws SQLException {
    	if (conn == null) {
    		createConnection();
    	}
    	
    	Statement stmt = null;
    	String query = "select * from Patron where patronID = " + patronId;
    	
    	Patron thePatron = null;
    	
    	try {
    		stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(query);
    		while (rs.next()) {
    			
    			int patronID = rs.getInt("patronID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String patronEmail = rs.getString("patronEmail");
                String phoneNumber = rs.getString("phoneNumber");
                String streetAddress = rs.getString("streetAddress");
                String City = rs.getString("City");
                String State = rs.getString("State");
                String zip = rs.getString("zip");
                thePatron = new Patron(patronID, firstName, lastName, patronEmail,
                        phoneNumber, streetAddress, City, State, zip);
               
    		}
    	} catch (SQLException e) {
    		System.out.println(e);
    	} finally {
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	
    	return thePatron;
    }
    
    /////////////////////////////////////////////////////////////////////////
    /////// PUBLISHER METHODS ///////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    
    /**
     * get all publishers
     * @return
     * @throws SQLException 
     */
    /*public List<Publisher> getPublisher() throws SQLException {
    	if (conn == null) {
            createConnection();
        }
        
        String sql = "SELECT * FROM _445team15.Publisher;";
        Statement statement = null;

        List<Publisher> publisherList = new ArrayList<Publisher>();
        try {
            statement = conn.createStatement();
            ResultSet patronResult = statement.executeQuery(sql);
            while (patronResult.next()) {
                String publisherName = patronResult.getString("publisherName");
                String publisherStreet = patronResult.getString("publisherStreet");
                String publisherCity = patronResult.getString("publisherCity");
                String publisherState = patronResult.getString("publisherState");
                String publisherZip = patronResult.getString("publisherZip");
                String publisherCountry = patronResult.getString("publisherCountry");
                int publisherFounded =  patronResult.getInt("publisherFounded");
                
                Publisher thePublishr = new Publisher(publisherName, publisherStreet,
                		publisherCity, publisherState, publisherZip,
                		publisherCountry, publisherFounded);
                
                publisherList.add(thePublishr);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return publisherList;
    }*/
    
    ////////////////////////////////////////////////////////////////////////
    ////// OTHER METHODS ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    public List<Object[]> getBookListInfo() throws SQLException {
    	
    	if (conn == null) {
    		createConnection();
    	}
    	
    	Statement stmt = null;
    	
    	String query = "select bookID, title, author, ISBN"
    				 + " from Book natural join BookInfo";
    	
    	List<Object[]> bookList = new ArrayList<Object[]>();
    	
    	try {
    		stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(query);
    		
    		while(rs.next()) {
    			int bookId = rs.getInt("bookID");
    			String title = rs.getString("title");
    			String author = rs.getString("author");
    			String isbn = rs.getString("ISBN");
    			
    			Object[] obj = {bookId, title, author, isbn};
    			
    			bookList.add(obj);
    			
    		}
    	} catch (SQLException e) {
    		System.out.println(e);
    	} finally {
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	
    	return bookList;
    }
    
    /**
     * update a publisher
     * @param aPublisherName
     * @param anAttribute
     * @param data
     */
    public void updatePublisher(String aPublisherName, String anAttribute, Object data) {
        String sql = "UPDATE _445team15.Publisher SET "
                + anAttribute + " = ? WHERE publisherName = ?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (data instanceof Integer) {
            	preparedStatement.setInt(1, (int)data);
            } else {
            	preparedStatement.setString(1, (String)data);
            }
            preparedStatement.setString(2, aPublisherName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
    
    
    
    public List<BookInfo> getFilteredBookInfo(String aCondition) throws SQLException {
    	if (conn == null) {
    		createConnection();
    	}

    	Statement stmt = null;
    	
		String query = "SELECT * FROM _445team15.BookInfo WHERE " + aCondition + ";";
		
		bookInfoList = new ArrayList<BookInfo>();
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String isbn = rs.getString("ISBN");
				String title = rs.getString("title");
				int year = rs.getInt("year");
				String author = rs.getString("author");
				String format = rs.getString("format");
				int pageNumber = rs.getInt("pageNumber");
				String language = rs.getString("Language");
				int bookshelfNumber = rs.getInt("bookshelfNumber");
				int layerNumber = rs.getInt("layerNumber");
				String publisherName = rs.getString("publisherName");
				
				BookInfo binfo = new BookInfo(isbn, title, year, author, 
								 format, pageNumber, language, bookshelfNumber,
								 layerNumber, publisherName);
				
				bookInfoList.add(binfo);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return bookInfoList;		 
	}
    
    /**
     * remove a boonInfo
     * @param anISBN
     */
    public void removeBookInfo(String anISBN) {
        String sql = "DELETE FROM _445team15.BookInfo WHERE ISBN = ?";
        PreparedStatement preparedStatement = null;
        try {
        	preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, anISBN);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
}

