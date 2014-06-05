import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class BookListPanel extends JPanel implements ActionListener, TableModelListener {
	
	private String[] myColumnNames = {"bookID", "title", "author", "ISBN" };
	
	private Object[][] myData;
	
	private JTable myTable;
	
	private List<Object[]> myBookListInfo;
	
	private LibraryFrame myFrame;
	
	private BookShelfDB myDB;
	
	private JButton myAddBookButton;
	
	private JButton mySearchPatronRecordButton;
	
	private JButton mySearchByButton;
	
	private JButton myClearSearchButton;
	
	private JComboBox<String> mySearchByList;
	
	private JTextField mySearchField;
	
	private JScrollPane myScrollPane;
	
	public BookListPanel(LibraryFrame theFrame, BookShelfDB theDB) {
		super();
		
		myFrame = theFrame;
		
		myDB = theDB;
		
		initializePanel();
	}
	
	private void initializePanel() {
		this.setLayout(new BorderLayout());
		
		myBookListInfo = new ArrayList<Object[]>();
		
		try {
			myBookListInfo = myDB.getBookListInfo();
			
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		initializeButtonPanel();
		initializeTableData(myBookListInfo);
		
		this.repaint();
	}
	
	private void initializeTableData(List<Object[]> bookList) {
		
		myData = new Object[bookList.size()][myColumnNames.length];
		
		for (int i = 0; i < bookList.size(); i++) {
			myData[i][0] = bookList.get(i)[0];
			myData[i][1] = bookList.get(i)[1];
			myData[i][2] = bookList.get(i)[2];
			myData[i][3] = bookList.get(i)[3];
		}
		
		myTable = new JTable(myData, myColumnNames);
		myScrollPane = new JScrollPane(myTable);
		
		myTable.getModel().addTableModelListener(this);
		
		if (myTable.getRowCount() > 0 ) {
			myTable.changeSelection(0, 0, false, false);
		}
		
		this.add(myScrollPane, BorderLayout.CENTER);
		
		this.revalidate();
	}
	
	private void initializeButtonPanel() {
		
		myAddBookButton = new JButton("Add Book");
		mySearchPatronRecordButton = new JButton("Get Patron Records");
		mySearchByButton = new JButton("Search By");
		myClearSearchButton = new JButton("Clear Search");
		
		myAddBookButton.addActionListener(this);
		mySearchPatronRecordButton.addActionListener(this);
		mySearchByButton.addActionListener(this);
		myClearSearchButton.addActionListener(this);
		
		
		mySearchByList = new JComboBox<String>(myColumnNames);
		mySearchByList.setSelectedIndex(0);
		
		mySearchField = new JTextField(45);
		mySearchField.setText("Enter Keyword");
		JPanel searchFieldPanel = new JPanel();
		searchFieldPanel.setLayout(new GridLayout(0,1));
		
		searchFieldPanel.add(mySearchField);
		searchFieldPanel.add(mySearchByList);
		searchFieldPanel.add(mySearchByButton);
		searchFieldPanel.add(myClearSearchButton);
		
		searchFieldPanel.setPreferredSize(new Dimension(200, 200));
		
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(0,1));
		
		buttonPanel.add(searchFieldPanel);
		buttonPanel.add(myAddBookButton);
		buttonPanel.add(mySearchPatronRecordButton);
		
		//buttonPanel.setPreferredSize(new Dimension(myFrame.getWidth()/2, myFrame.getHeight()));
		
		this.add(buttonPanel, BorderLayout.WEST);
	}
	
	public void actionPerformed(ActionEvent theEvent) {
		if (theEvent.getSource() == myAddBookButton) {
			myFrame.showBookPanel();
		} else if (theEvent.getSource() == mySearchPatronRecordButton) {
			if (myTable.getRowCount() > 0) {
				int bookID = (int)myTable.getValueAt(myTable.getSelectedRow(), 0);
				String isbn = (String)myTable.getValueAt(myTable.getSelectedRow(), 3);
				System.out.println("LibraryFrame switching panel to PatronRecordList for bookID = " + bookID);
				Book book = new Book(bookID, isbn);
				myFrame.showPatronRecordListPanel(book);
			}
		} else if (theEvent.getSource() == mySearchByButton) {
			if (!mySearchField.getText().equals("Enter Keyword")) {
				
				System.out.println("Searching By Keyword");
				
				List<Object[]> tempBookList = new ArrayList<Object[]>();
				System.out.println(myBookListInfo.size());
		
				int searchForIndex = mySearchByList.getSelectedIndex();
				int found = 0;
				
				for (Object[] bookItem : myBookListInfo) {
					Object obj = bookItem[searchForIndex];
					
					if (searchForIndex == 0) {
						try {
							if ((int)bookItem[searchForIndex] == Integer.parseInt(mySearchField.getText())) {
								tempBookList.add(bookItem);
								found++;
							}
						} catch (NumberFormatException e) {
							System.out.println(e);
							e.printStackTrace();
						}
					} else {
						String value = (String)obj;
						if (value.contains(mySearchField.getText())) {
							tempBookList.add(bookItem);
							found++;
						}
					}
				}
				System.out.println(found);
				myBookListInfo = tempBookList;
				this.remove(myScrollPane);
				initializeTableData(myBookListInfo);
				this.repaint();
			}
		} else if (theEvent.getSource() == myClearSearchButton) {
			try {
				myBookListInfo = myDB.getBookListInfo();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
			this.remove(myScrollPane);
			initializeTableData(myBookListInfo);
			this.repaint();
		} 
	}
	
	public void tableChanged(TableModelEvent theEvent) {
		this.remove(myScrollPane);
		initializeTableData(myBookListInfo);
		this.repaint();
	}

}
