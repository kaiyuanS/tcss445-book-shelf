import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class PatronListPanel extends JPanel implements ActionListener, TableModelListener {
	
	private BookShelfDB myDB;
	
	private LibraryFrame myFrame;
	
	private JTable myTable;
	
	private String[] myColumnNames = {"patronID", "firstName", "lastName", "patronEmail", "phoneNumber", "street", "city", "state", "zip"};
	
	private Object[][] myData;
	
	private List<Patron> myPatronList;
	
	private JButton myViewPatronRecordsButton;
	
	
	public PatronListPanel(LibraryFrame theFrame, BookShelfDB theDB) {
		super();
		
		myFrame = theFrame;
		
		myDB = theDB;
		
		initializePanel();
	}
	
	private void initializePanel() {
		this.setLayout(new BorderLayout());
		
		myPatronList = new ArrayList<Patron>();
		
		myViewPatronRecordsButton = new JButton("View Records");
		myViewPatronRecordsButton.addActionListener(this);
		
		this.add(myViewPatronRecordsButton, BorderLayout.SOUTH);
		
		
		try {
			myPatronList = myDB.getPatrons();
			
			myData = new Object[myPatronList.size()][myColumnNames.length];
			
			for (int i = 0; i < myPatronList.size(); i++) {
				myData[i][0] = myPatronList.get(i).getPatronID();
				myData[i][1] = myPatronList.get(i).getFirstName();
				myData[i][2] = myPatronList.get(i).getLastName();
				myData[i][3] = myPatronList.get(i).getPatronEmail();
				myData[i][4] = myPatronList.get(i).getPhoneNumber();
				myData[i][5] = myPatronList.get(i).getStreetAddress();
				myData[i][6] = myPatronList.get(i).getCity();
				myData[i][7] = myPatronList.get(i).getState();
				myData[i][8] = myPatronList.get(i).getZip();
			}
			
			myTable = new JTable(myData, myColumnNames);
			
			if (myTable.getRowCount() > 0) {
				myTable.changeSelection(0, 0, false, false);
			}
			
			JScrollPane scrollPane = new JScrollPane(myTable);
			
			myTable.getModel().addTableModelListener(this);
			
			
			this.add(scrollPane, BorderLayout.CENTER);
			
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent theEvent) {
		if (theEvent.getSource() == myViewPatronRecordsButton) {
			if (myTable.getSelectedRow() >= 0) {
				int patronID = (int)myTable.getValueAt(myTable.getSelectedRow(), 0);
				String firstName = (String)myTable.getValueAt(myTable.getSelectedRow(), 1);
				String lastName = (String)myTable.getValueAt(myTable.getSelectedRow(), 2);
				String email = (String)myTable.getValueAt(myTable.getSelectedRow(), 3);
				String phNumber = (String)myTable.getValueAt(myTable.getSelectedRow(), 4);
				String stAddr = (String)myTable.getValueAt(myTable.getSelectedRow(), 5);
				String city = (String)myTable.getValueAt(myTable.getSelectedRow(), 6);
				String state = (String)myTable.getValueAt(myTable.getSelectedRow(), 7);
				String zip = (String)myTable.getValueAt(myTable.getSelectedRow(), 8);
				
				Patron patron = new Patron(patronID, firstName, lastName, email, phNumber, stAddr,
							    city, state, zip);
				myFrame.showPatronRecordListPanel(patron);
			}
		}
	}
	
	public void tableChanged(TableModelEvent theEvent) {
		int row = theEvent.getFirstRow();
		int col = theEvent.getColumn();
		TableModel model = (TableModel)theEvent.getSource();
		String columnName = model.getColumnName(col);
		if (!columnName.equals("patronId")) {
			Object data = model.getValueAt(row, col);
			myDB.editPatron(row, columnName, data);
		}
		
	}
}
