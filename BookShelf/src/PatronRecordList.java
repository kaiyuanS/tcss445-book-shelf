import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.sql.SQLException;
import java.util.List;

public class PatronRecordList extends JPanel {
	
	private BookShelfDB myDatabase;
	
	private JTable myTable;
	
	private JPanel myButtonPanel;
	
	private JButton mySearchByPatronB;
	
	private JButton mySearchByBookB;
	
	public PatronRecordList(BookShelfDB db, Object theObject) {
		super();
		
		
		myDatabase = db;
		initializePanel(theObject);
	}
	
	private JTable initializeTable(Object[][] data) {
		
		final String[] colNames = {"recordID",  "patronID", "bookID", "borrowBy","returnBy"};
		JTable table = new JTable(data, colNames);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		
		return table;
	}
	
	private void initializePanel(Object theObject) {
		this.setLayout(new BorderLayout());
		//initializeButtons();
		
		if (theObject == null) {
			initializeByNull();
		} else {
			if (theObject instanceof Book) {
				initializeByBook(theObject);
			} else if (theObject instanceof Patron) {
				initializeByPatron(theObject);
			}
		}
	}
	
	private void initializeByBook(Object theObject) {
		Book book = (Book)theObject;
		
		try {
			List<PatronRecord> prList = myDatabase.getPatronRecordByBookID(book.getBookID());
			
			Object[][] prArray = new Object[prList.size()][];
			prList.toArray(prArray);
			
			myTable = initializeTable(prArray);
			
			JScrollPane scrollPane = new JScrollPane(myTable);
			myTable.setFillsViewportHeight(true);
			
			this.add(scrollPane, BorderLayout.CENTER);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void initializeByPatron(Object theObject) {
		Patron patron = (Patron)theObject;
		
		try {
			List<PatronRecord> prList = myDatabase.getPatronRecordByPatronID(patron.getPatronID());
			
			Object[][] prArray = new Object[prList.size()][];
			prList.toArray(prArray);
			
			myTable = initializeTable(prArray);
			
			JScrollPane scrollPane = new JScrollPane(myTable);
			myTable.setFillsViewportHeight(true);
			
			this.add(scrollPane, BorderLayout.CENTER);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void initializeByNull() {
		
		try {
			List<PatronRecord> prList = myDatabase.getPatronRecords();
			
			Object[][] prArray = new Object[prList.size()][];
			prList.toArray(prArray);
			
			myTable = initializeTable(prArray);
			
			JScrollPane scrollPane = new JScrollPane(myTable);
			myTable.setFillsViewportHeight(true);
			
			this.add(scrollPane, BorderLayout.CENTER);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	private void initializeButtons() {
		myButtonPanel = new JPanel();
		
		myButtonPanel.setLayout(new BoxLayout(myButtonPanel, BoxLayout.PAGE_AXIS));
		
		mySearchByPatronB = new JButton("Search By Patron");
		mySearchByBookB = new JButton("Search By Book");
		
		mySearchByPatronB.setEnabled(false);
		mySearchByBookB.setEnabled(false);
		
		mySearch
		
		myButtonPanel.add(mySearchByBookB);
		myButtonPanel.add(mySearchByPatronB);
		
		this.add(myButtonPanel, BorderLayout.EAST);
	}
	*/
}
