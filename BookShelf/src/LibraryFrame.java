import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryFrame extends JFrame{
	
	private BookShelfDB myDatabase;
	
	private BookListPanel myBookListPanel; //
	private PatronListPanel myPatronListPanel; //
	private PatronRecordList myRecordListPanel;
	private PublisherListPanel myPublisherListPanel;
	private BookInfoPanel myBookInfo;
	private JPanel myPatronInfo;
	private JPanel myPublisherInfo;
	private JPanel myButtonPanel; //1
	private JPanel mySearchPanel;
	private BookInfoListPanel myBookInfoListPanel;
	//private BookListPanel myBookList;
	
	private JPanel myCurrentPanel;
	
	
	public LibraryFrame() {
		myDatabase = new BookShelfDB();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(new BorderLayout());
		//addPanel();
		initPanels();
		//myRecordListPanel = new PatronRecordList(this, myDatabase, null);
		//this.add(myRecordListPanel, BorderLayout.CENTER);
		/*myPatronListPanel = new PatronListPanel(this, myDatabase);
		myContentPanel = myPatronListPanel;
		myBookInfoList = new BookInfoListPanel(myDatabase, this);
		myContentPanel = myBookInfoList;
		myBookListPanel = new BookListPanel(this, myDatabase);
		myContentPanel = myBookListPanel;
		*/
		//this.add(myContentPanel);
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
	}
	
	
	private void initPanels() {
		initButtonPanel();
		//initBookListPanel();
		myBookInfoListPanel = new BookInfoListPanel(myDatabase, this);
		/*initBookInfo();
		initPublisherPanel();
		initPublisherListPanel();*/
		this.add(myButtonPanel, BorderLayout.SOUTH);
		//this.add(myBookListPanel, BorderLayout.CENTER);
		//this.add(myPublisherListPanel, BorderLayout.CENTER);
		//myCurrentPanel = myPublisherListPanel;
		//this.add(myPublisherInfoPanel, BorderLayout.CENTER);
		//myCurrentPanel = myBookInfoPanel;
		this.add(myBookInfoListPanel, BorderLayout.CENTER);
		myCurrentPanel = myBookInfoListPanel;
		//this.add(myBookInfoPanel, BorderLayout.CENTER);
		//myCurrentPanel = myBookInfoPanel;
		
	}
	
	private void initButtonPanel() {
		myButtonPanel = new JPanel();
		JButton searchBooks = new JButton("Search Books");
		JButton searchPatrons = new JButton("Search Patrons");
		JButton searchKeyword = new JButton("Search Keyword");
		myButtonPanel.setLayout(new FlowLayout());
		
		searchBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theActionEvent) {
				showBookInfoListPanel();
			}
		});
		
		searchPatrons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theActionEvent) {
				showPatronListPanel();
			}
		});
		
		searchKeyword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theActionEvent) {
				showSearchPanel();
			}
		});
		
		myButtonPanel.add(searchBooks);
		myButtonPanel.add(searchPatrons);
		myButtonPanel.add(searchKeyword);
		
	}
	
	/*
	private void initBookListPanel() {
		//myBookListPanel = new JPanel();
		//myBookListPanel.setLayout(new BorderLayout());
		
		List<Object[]> bookList = new ArrayList<Object[]>();
		
		String[] columnNames = {"BookID", "Title", "Author", "ISBN"};
		try {
			bookList = myDatabase.getBookListInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Object[][] data = new Object[bookList.size()][];
		bookList.toArray(data);
		
		JTable bookListTable = new JTable(data, columnNames);
		ListSelectionModel lsm = bookListTable.getSelectionModel();
		
		bookListTable.setColumnSelectionAllowed(false);
		bookListTable.setRowSelectionAllowed(true);
		JScrollPane scrollPane = new JScrollPane(bookListTable);
		bookListTable.setFillsViewportHeight(true);
		myBookListPanel.add(scrollPane, BorderLayout.CENTER);
	}
	*/
	
	private void initPublisherPanel() {
		myPublisherInfo = new JPanel();
		myPublisherInfo.setLayout(new BorderLayout());
		
		
		
		List<Publisher> publisherList = new ArrayList<Publisher>();
		try {
			publisherList = myDatabase.getPublishers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] publisherColName = {"Publisher Name", "Street", "City", "State",
				"Zip", "Country", "Founded"};
		Object[][] publisherData = new Object[publisherList.size()][publisherColName.length];
		
		for (int i = 0; i < publisherList.size(); i++) {
			publisherData[i][0] = publisherList.get(i).getName();
			publisherData[i][1] = publisherList.get(i).getStreet();
			publisherData[i][2] = publisherList.get(i).getCity();
			publisherData[i][3] = publisherList.get(i).getState();
			publisherData[i][4] = publisherList.get(i).getZipCode();
			publisherData[i][5] = publisherList.get(i).getCountry();
			publisherData[i][6] = publisherList.get(i).getYearFounded();
		}
		
		JTable publisherTable = new JTable(publisherData, publisherColName);
		publisherTable.setColumnSelectionAllowed(false);
		publisherTable.setRowSelectionAllowed(true);
		JScrollPane publisherScrollPane = new JScrollPane(publisherTable);
		publisherTable.setFillsViewportHeight(true);
		myPublisherInfo.add(publisherScrollPane, BorderLayout.CENTER);
		
	}
	private void initBookInfoList() {
		//myBookInfoList = new BookInfoListPanel(this, myDatabase);
	}
	
	private class BookSearchListener implements ActionListener {
		
		JFrame myMainFrame;
		
		public BookSearchListener(final JFrame theFrame) {
			myMainFrame = theFrame;
		}
		
		public void actionPerformed(ActionEvent theActionEvent) {
			//myMainFrame.add(comp)
		}
	}
	/*
	private void configPanel() {
		BookInfo testBookInfo = new BookInfo("1234567890123", "Book Title", 2010,
                "Book Author", 1, 500, "English", 20, 3, "Book Publisher");
		myButtonPanel = new ButtonPanel();
		myBookInfo = new BookInfoPanel(testBookInfo);
		myBookListPanel = new BookListPanel(myDatabase);
	}
	*/
	
	private void addPanel() {
		//add(myBookInfo, BorderLayout.CENTER);
		//add(myBookListPanel, BorderLayout.CENTER);
		//add(myButtonPanel, BorderLayout.SOUTH);
	}
	
	public void replaceContentPanel(JPanel thePanel) {
		this.remove(myCurrentPanel);
		myCurrentPanel = thePanel;
		
		this.add(myCurrentPanel, BorderLayout.CENTER);
		this.pack();
		this.repaint();
	}
	
	public void showPatronRecordListPanel(Object theObject) {
		System.out.println("LibraryFrame: Changing to PatronRecordList");
		replaceContentPanel(new PatronRecordList(this, myDatabase, theObject));
	}
	
	public void showBookListPanel() {
		System.out.println("LibraryFrame: Changing to BookListPanel");
		replaceContentPanel(new BookListPanel(this, myDatabase));
	}
	

	
	/////////////////////////////////////////////////////
	////JPanel Switch Method Here////////////////////////
	/////////////////////////////////////////////////////
	public void showPublisherListPanel() {
		replaceContentPanel(new PublisherListPanel(myDatabase, this));
	}
	
	public void showPublisherInfoPanel() {
		replaceContentPanel(new PublisherInfoPanel(myDatabase, this));
	}
	
	public void showBookInfoListPanel() {
		replaceContentPanel(new BookInfoListPanel(myDatabase, this));
	}
	
	public void showBookInfoPanel() {
		replaceContentPanel(new BookInfoPanel(myDatabase, this));
	}
	
	public void showSearchPanel() {
		replaceContentPanel(new SearchPanel(myDatabase, this));
	}
	
	public void showPatronListPanel() {
		replaceContentPanel(new PublisherListPanel(myDatabase, this));
	}
	
}
