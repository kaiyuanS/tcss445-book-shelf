import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This is the PatronRecordList panel class. It is used to show a scrollable list of all the 
 * PatronRecords currently within the database. This panel allows the user to search all records
 * for a selected book or patron, the option to clear these search results, the option to view all 
 * records with books currently checked out, and to add a record. The table will hold all the values
 * of the PatronRecord class along with a due date in the last column, which will be thirty days
 * after the borrowBy date.
 * 
 * @author Kevin Alexander
 * @version June 4, 2014
 *
 */
public class PatronRecordList extends JPanel implements ActionListener, TableModelListener {
	/**
	 * The database.
	 */
	private BookShelfDB myDB;
	/**
	 * The table to hold all the tuple values.
	 */
	private JTable myTable;
	/**
	 * The panel which will hold all the buttons.
	 */
	private JPanel myButtonPanel;
	/**
	 * The button used to search by a selected patron.
	 */
	private JButton mySearchByPatronButton;
	/**
	 * The button used to search by a selected book.
	 */
	private JButton mySearchByBookButton;
	/**
	 * The button used to clear the search results.
	 */
	private JButton myClearButton;
	/**
	 * The button used to add a new Record.
	 */
	private JButton myAddButton;
	/**
	 * The button used to view all records with books currently checked out.
	 */
	private JButton myCheckOutButton;
	/**
	 * The main LibraryFrame.
	 */
	private LibraryFrame myFrame;
	/**
	 * An array which holds all of the data.
	 */
	private Object[][] myData;
	/**
	 * The column names for the table.
	 */
	private String[] myColumnNames = {"recordID",  "patronID", "bookID", "borrowBy","returnBy", "dueBy"};
	/**
	 * A list of the PatronRecords.
	 */
	private List<PatronRecord> myPatronRecordList;
	
	private JScrollPane myScrollPane;
	/**
	 * The constructor for the PatronRecordListPanel. It takes in a LibraryFrame object, a 
	 * BookShelfDB object, and an Object object as parameters which it will set to myFrame and myDB 
	 * respectively and use the Object in initializing the panel. Itcalls the method initializePanel() 
	 * to initialize the panel.
	 * 
	 * @param theFrame A LibraryFrame object which holds this panel.
	 * @param theDatabase A BookShelfDB object which will be used to gather information
	 * from the database.
	 * @param theObject A Book or 
	 */
	public PatronRecordList(LibraryFrame theFrame, BookShelfDB theDatabase, Object theObject) {
		super();
		
		myFrame = theFrame;
		
		myDB = theDatabase;
		
		initializePanel(theObject);
	}
	
	private void initializePanel(Object theObject) {
		this.setLayout(new BorderLayout());
		
		myPatronRecordList = new ArrayList<PatronRecord>();
		try {
			if (theObject instanceof Book) {
				Book book = (Book)theObject;
				myPatronRecordList = myDB.getPatronRecordByBookID(book.getBookID());
			} else if (theObject instanceof Patron) {
				Patron patron = (Patron)theObject;
				myPatronRecordList = myDB.getPatronRecordByPatronID(patron.getPatronID());
			} else {
				myPatronRecordList = myDB.getPatronRecords();
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.getStackTrace();
		}
		initializeButtonPanel();
		initializeTableData(myPatronRecordList);
		this.repaint();
	}
	
	private void initializeTableData(List<PatronRecord> prList) {
		myData = new Object[prList.size()][myColumnNames.length];
		
		Date date = null;
		Calendar cal = null;
		for (int i = 0; i < prList.size(); i++) {
			myData[i][0] = prList.get(i).getRecordID();
			myData[i][1] = prList.get(i).getPatronID();
			myData[i][2] = prList.get(i).getBookID();
			myData[i][3] = prList.get(i).getBorrowByDate();
			myData[i][4] = prList.get(i).getReturnByDate();
			date = (Date)myData[i][3];
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 30);
			myData[i][5] = new Date(cal.getTime().getTime());
		}
		
		myTable = new JTable(myData, myColumnNames);
		myScrollPane = new JScrollPane(myTable);
		myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		myTable.getModel().addTableModelListener(this);
		
		if (myTable.getRowCount() > 0 ) {
			myTable.changeSelection(0, 0, false, false);
		}
		
		this.add(myScrollPane, BorderLayout.CENTER);
		
		this.revalidate();
	}
	
	private void initializeButtonPanel() {
		myButtonPanel = new JPanel();
		myButtonPanel.setLayout(new GridLayout(0,1));
		
		mySearchByPatronButton = new JButton("Search By Selected Patron");
		mySearchByBookButton = new JButton("Search By Selected Book");
		myClearButton = new JButton("Clear Search");
		myAddButton = new JButton("Add PatronRecord");
		myCheckOutButton = new JButton("See Checked Out");
		
		mySearchByPatronButton.addActionListener(this);
		mySearchByBookButton.addActionListener(this);
		myClearButton.addActionListener(this);
		myAddButton.addActionListener(this);
		myCheckOutButton.addActionListener(this);
		
		myButtonPanel.add(mySearchByBookButton);
		myButtonPanel.add(mySearchByPatronButton);
		myButtonPanel.add(myClearButton);
		myButtonPanel.add(myCheckOutButton);
		myButtonPanel.add(myAddButton);
		
		
		this.add(myButtonPanel, BorderLayout.WEST);
	}
	
	public void actionPerformed(ActionEvent theEvent) {
		List<PatronRecord> prList;
		if (theEvent.getSource() == mySearchByPatronButton) {
			int patronID = (int)myTable.getValueAt(myTable.getSelectedRow(), 1);
			
			prList = new ArrayList<PatronRecord>();
			try {
				prList = myDB.getPatronRecord(patronID);
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
			if (prList.size() == 0) {
				JOptionPane.showConfirmDialog(null, "No records for PatronID " + patronID);
			} else {
				initializeTableData(prList);
				this.repaint();
			}
		} else if (theEvent.getSource() == mySearchByBookButton) {
			int bookID = (int)myTable.getValueAt(myTable.getSelectedRow(), 2);
			
			prList = new ArrayList<PatronRecord>();
			try {
				prList = myDB.getPatronRecordByBookID(bookID);
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
			if (prList.size() == 0) {
				JOptionPane.showConfirmDialog(null, "No records for BookID " + bookID);
			} else {
				initializeTableData(prList);
				this.repaint();
			}
		} else if (theEvent.getSource() == myClearButton) {
			try {
				myPatronRecordList = myDB.getPatronRecords();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
			initializeTableData(myPatronRecordList);
			this.repaint();
		} else if (theEvent.getSource() == myAddButton) {
			myFrame.showPatronRecordPanel();
		} else if (theEvent.getSource() == myCheckOutButton) {
			
			List<PatronRecord> checkedOutOrders = new ArrayList<PatronRecord>();
			try {
				myPatronRecordList = myDB.getPatronRecords();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
			System.out.println(myPatronRecordList.size());
			for (PatronRecord pr : myPatronRecordList) {
				if (pr.getReturnByDate() == null) {
					System.out.println("pr: " + pr.getRecordID() + " returnDate is null");
					checkedOutOrders.add(pr);
				}
			}
			

			myPatronRecordList = checkedOutOrders;
			this.remove(myScrollPane);
			initializeTableData(myPatronRecordList);
			this.repaint();
		}
	}
	
	public void tableChanged(TableModelEvent theEvent) {
		int row = theEvent.getFirstRow();
		int col = theEvent.getColumn();
		TableModel model = (TableModel)theEvent.getSource();
		String columnName = model.getColumnName(col);
		if (col != 5) {
			Object data = model.getValueAt(row, col);
			if (col == 3 || col == 4) {
				Date date = null;
				if (((String)data).equals("")) {
					myDB.editPatronRecord(row, columnName, null);
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					java.util.Date parsed = format.parse((String)data);
					date = new Date(parsed.getTime());
					myDB.editPatronRecord(row, columnName, date);
				} catch (ParseException e) {
					System.out.println(e);
					e.printStackTrace();
				}
				
				if (col == 4) {
					Date d = (Date)model.getValueAt(row, 5);
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					
					if (date.after(d)) {
						long length = date.getTime() - d.getTime();
						// seconds
						length /= 1000;
						// minutes
						length /= 60;
						// hours
						length /= 60;
						// days
						length /= 24;
						double dues = .2 * length;
						
						JOptionPane.showMessageDialog(null, "BOOK OVERDUE: Charge: $" + dues);
					}
				}
			} else {
				myDB.editPatronRecord(row, columnName, data);
			}
		}
	}
}
