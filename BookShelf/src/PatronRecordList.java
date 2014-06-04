import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PatronRecordList extends JPanel implements ActionListener, TableModelListener {
	
	private BookShelfDB myDB;
	
	private JTable myTable;
	
	private JPanel myButtonPanel;
	
	private JButton mySearchByPatronButton;
	
	private JButton mySearchByBookButton;
	
	private JButton myClearButton;
	
	private JButton myAddButton;
	
	private JButton myCheckOutButton;
	
	private LibraryFrame myFrame;
	
	private Object[][] myData;
	
	private String[] myColumnNames = {"recordID",  "patronID", "bookID", "borrowBy","returnBy"};
	
	private List<PatronRecord> myPatronRecordList;
	
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
		
		for (int i = 0; i < prList.size(); i++) {
			myData[i][0] = prList.get(i).getRecordID();
			myData[i][1] = prList.get(i).getPatronID();
			myData[i][2] = prList.get(i).getBookID();
			myData[i][3] = prList.get(i).getBorrowByDate();
			myData[i][4] = prList.get(i).getReturnByDate();
		}
		
		myTable = new JTable(myData, myColumnNames);
		JScrollPane scrollPane = new JScrollPane(myTable);
		
		myTable.getModel().addTableModelListener(this);
		
		if (myTable.getRowCount() > 0 ) {
			myTable.changeSelection(0, 0, false, false);
		}
		
		this.add(scrollPane, BorderLayout.CENTER);
		
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
			
			for (PatronRecord pr : myPatronRecordList) {
				if (pr.getReturnByDate() == null) {
					checkedOutOrders.add(pr);
				}
			}
			
			myPatronRecordList = checkedOutOrders;
			
			initializeTableData(myPatronRecordList);
			this.repaint();
		}
	}
	
	public void tableChanged(TableModelEvent theEvent) {
		int row = theEvent.getFirstRow();
		int col = theEvent.getColumn();
		TableModel model = (TableModel)theEvent.getSource();
		String columnName = model.getColumnName(col);
		
		Object data = model.getValueAt(row, col);
		if (col == myColumnNames.length - 1 || col == myColumnNames.length - 2) {
			if (((String)data).equals("")) {
				myDB.editPatronRecord(row, columnName, null);
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date parsed = format.parse((String)data);
				Date date = new Date(parsed.getTime());
				myDB.editPatronRecord(row, columnName, date);
			} catch (ParseException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		} else {
			myDB.editPatronRecord(row, columnName, data);
		}
	}
}
