import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	
	private JButton myAddPatronButton;
	
	private JButton mySearchByButton;
	
	private JButton myClearButton;
	
	private JScrollPane myScrollPane;
	
	private JComboBox mySearchByList;
	
	private JTextField mySearchField;
	
	
	public PatronListPanel(LibraryFrame theFrame, BookShelfDB theDB) {
		super();
		
		myFrame = theFrame;
		
		myDB = theDB;
		
		initializePanel();
	}
	
	private void initializePanel() {
		this.setLayout(new BorderLayout());
		
		myPatronList = new ArrayList<Patron>();		
		
		try {
			myPatronList = myDB.getPatrons();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		initializeButtonPanel();
		initializeTableData(myPatronList);
	}
	
	private void initializeButtonPanel() {
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,1));
		
		myViewPatronRecordsButton = new JButton("View Records");
		myViewPatronRecordsButton.addActionListener(this);
		
		myAddPatronButton = new JButton("Add Patron");
		myAddPatronButton.addActionListener(this);
		
		mySearchByButton = new JButton("Search By");
		mySearchByButton.addActionListener(this);
		
		myClearButton = new JButton("Clear Search");
		myClearButton.addActionListener(this);
		
		buttonPanel.add(myViewPatronRecordsButton);
		buttonPanel.add(myAddPatronButton);
		
		mySearchByList = new JComboBox(myColumnNames);
		mySearchByList.setSelectedIndex(0);
		
		mySearchField = new JTextField(45);
		mySearchField.setText("Enter Keyword");
		JPanel searchFieldPanel = new JPanel();
		searchFieldPanel.setLayout(new GridLayout(0,1));
		
		searchFieldPanel.add(mySearchField);
		searchFieldPanel.add(mySearchByList);
		searchFieldPanel.add(mySearchByButton);
		searchFieldPanel.add(myClearButton);
		searchFieldPanel.setPreferredSize(new Dimension(200, 200));
		
		buttonPanel.add(searchFieldPanel);
		buttonPanel.add(myViewPatronRecordsButton);
		buttonPanel.add(myAddPatronButton);
		
		this.add(buttonPanel, BorderLayout.WEST);
	}
	
	private void initializeTableData(List<Patron> theList) {
		myData = new Object[theList.size()][myColumnNames.length];
		
		for (int i = 0; i < theList.size(); i++) {
			myData[i][0] = theList.get(i).getPatronID();
			myData[i][1] = theList.get(i).getFirstName();
			myData[i][2] = theList.get(i).getLastName();
			myData[i][3] = theList.get(i).getPatronEmail();
			myData[i][4] = theList.get(i).getPhoneNumber();
			myData[i][5] = theList.get(i).getStreetAddress();
			myData[i][6] = theList.get(i).getCity();
			myData[i][7] = theList.get(i).getState();
			myData[i][8] = theList.get(i).getZip();
		}
		
		myTable = new JTable(myData, myColumnNames);
		
		if (myTable.getRowCount() > 0) {
			myTable.changeSelection(0, 0, false, false);
		}
		
		myScrollPane = new JScrollPane(myTable);
		
		myTable.getModel().addTableModelListener(this);
		
		
		this.add(myScrollPane, BorderLayout.CENTER);
		
		this.revalidate();
	}
	
	
	public void actionPerformed(ActionEvent theEvent) {
		if (theEvent.getSource() == mySearchByButton) {
			if (!mySearchField.getText().equals("Enter Keyword")) {
					
				System.out.println("Searching By Keyword");
				
				List<Patron> tempPatronList = new ArrayList<Patron>();
				System.out.println(myPatronList.size());
		
				int searchForIndex = mySearchByList.getSelectedIndex();
				int found = 0;
				Object searchFor;
				
				for (Patron patron : myPatronList) {
					switch(searchForIndex) {
					case 0:
						if (patron.getPatronID() == Integer.parseInt(mySearchField.getText())) {
							tempPatronList.add(patron);
						}
						break;
					case 1:
						if (patron.getFirstName().contains(mySearchField.getText())) {
							tempPatronList.add(patron);
						}
						break;
					case 2:
						if (patron.getLastName().contains(mySearchField.getText())) {
							tempPatronList.add(patron);
						}
						break;
					case 3:
						if (patron.getPatronEmail().contains(mySearchField.getText())) {
							tempPatronList.add(patron);
						}
						break;
					case 4:
						if (patron.getPhoneNumber().contains(mySearchField.getText())) {
							tempPatronList.add(patron);
						}
						break;
					case 5:
						if (patron.getStreetAddress().contains(mySearchField.getText())) {
							tempPatronList.add(patron);
						}
						break;
					case 6:
						if (patron.getCity().contains(mySearchField.getText())) {
							tempPatronList.add(patron);
						}
						break;
					case 7:
						if (patron.getState().contains(mySearchField.getText())) {
							tempPatronList.add(patron);
						}
						break;
					case 8:
						if (patron.getZip().contains(mySearchField.getText())) {
							tempPatronList.add(patron);
						}
						break;
					default:
						break;
					}
				}
				
				System.out.println(found);
				myPatronList = tempPatronList;
				this.remove(myScrollPane);
				initializeTableData(myPatronList);
				this.repaint();
			}
		} else if (theEvent.getSource() == myClearButton) {
			try {
				myPatronList = myDB.getPatrons();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
			this.remove(myScrollPane);
			initializeTableData(myPatronList);
			this.repaint();
		} else if (theEvent.getSource() == myViewPatronRecordsButton) {
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
		} else if (theEvent.getSource() == myAddPatronButton) {
			myFrame.showPatronPanel();
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
