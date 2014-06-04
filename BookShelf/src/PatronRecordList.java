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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatronRecordList extends JPanel implements ActionListener, TableModelListener {
	
	private BookShelfDB myDB;
	
	private JTable myTable;
	
	private JPanel myButtonPanel;
	
	private JButton mySearchByPatronButton;
	
	private JButton mySearchByBookButton;
	
	private LibraryFrame myFrame;
	
	private Object[][] myData;
	
	private String[] myColumnNames = {"recordID",  "patronID", "bookID", "borrowBy","returnBy"};
	
	public PatronRecordList(LibraryFrame theFrame, BookShelfDB theDatabase, Object theObject) {
		super();
		
		myFrame = theFrame;
		
		myDB = theDatabase;
		
		initializePanel(theObject);
	}
	
	private void initializePanel(Object theObject) {
		this.setLayout(new BorderLayout());
		
		List<PatronRecord> prList = new ArrayList<PatronRecord>();
		try {
			if (theObject instanceof Book) {
				Book book = (Book)theObject;
				prList = myDB.getPatronRecordByBookID(book.getBookID());
			} else if (theObject instanceof Patron) {
				Patron patron = (Patron)theObject;
				prList = myDB.getPatronRecordByPatronID(patron.getPatronID());
			} else {
				prList = myDB.getPatronRecords();
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.getStackTrace();
		}
		initializeButtonPanel();
		initializeTableData(prList);
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
		
		mySearchByPatronButton.addActionListener(this);
		mySearchByBookButton.addActionListener(this);
		
		myButtonPanel.add(mySearchByBookButton);
		myButtonPanel.add(mySearchByPatronButton);
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
			
		}
	}
	
	public void tableChanged(TableModelEvent theEvent) {
		int row = theEvent.getFirstRow();
		int col = theEvent.getColumn();
		TableModel model = (TableModel)theEvent.getSource();
		String columnName = model.getColumnName(col);
		
		Object data = model.getValueAt(row, col);
		myDB.editPatron(row, columnName, data);
		
	}
	
}
