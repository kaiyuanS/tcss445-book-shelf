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
	private JPanel myCurrentPanel;
	private BookShelfDB myDatabase;
	
	private JPanel myBookListPanel; //
	private JPanel myPatronListPanel; //
	private JPanel myRecordListPanel;
	private PublisherListPanel myPublisherListPanel;
	private BookInfoPanel myBookInfoPanel;
	private JPanel myPatronInfo;
	private PublisherInfoPanel myPublisherInfoPanel;
	private JPanel myButtonPanel; //1
	private SearchPanel mySearchPanel;
	private BookInfoListPanel myBookInfoListPanel;
	
	
	public LibraryFrame() {
		myDatabase = new BookShelfDB();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(new BorderLayout());
		//addPanel();
		initPanels();
		//myRecordListPanel = new PatronRecordList(myDatabase, null);
		//this.add(myRecordListPanel, BorderLayout.CENTER);
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
		//mySearchPanel = new SearchPanel(myDatabase, this);
		//myCurrentPanel = myBookInfoPanel;
		//this.add(myCurrentPanel, BorderLayout.CENTER);
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
	
	/*rivate void initBookListPanel() {
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
	}*/
	
	/*private void initPublisherPanel() {
		myPublisherInfoPanel = new PublisherInfoPanel(myDatabase, this);
	}
	private void initBookInfoList() {
		myBookInfoListPanel = new BookInfoListPanel(myDatabase, this);
	}
	private void initBookInfo() {
		myBookInfoPanel = new BookInfoPanel(myDatabase, this);
	}
	private void initPublisherListPanel() {
		myPublisherListPanel = new PublisherListPanel(myDatabase, this);
	}*/
	
	/*private class BookSearchListener implements ActionListener {
		
		JFrame myMainFrame;
		
		public BookSearchListener(final JFrame theFrame) {
			//myMainFrame = theFrame;
			
		}
		
		public void actionPerformed(ActionEvent theActionEvent) {
			//myMainFrame.add(comp)
		}
	}*/
	
	/*private void configPanel() {
		BookInfo testBookInfo = new BookInfo("1234567890123", "Book Title", 2010,
                "Book Author", "Hard Cover", 500, "English", 20, 3, "Book Publisher");
		myButtonPanel = new ButtonPanel();
		myBookInfoPanel = new BookInfoPanel(myDatabase);
		myBookListPanel = new BookListPanel(myDatabase);
	}*/
	
	/*private void addPanel() {
		//add(myBookInfo, BorderLayout.CENTER);
		add(myBookListPanel, BorderLayout.CENTER);
		add(myButtonPanel, BorderLayout.SOUTH);
	}*/
	
	/////////////////////////////////////////////////////
	////JPanel Switch Method Here////////////////////////
	/////////////////////////////////////////////////////
	public void showPublisherListPanel() {
		remove(myCurrentPanel);
		myCurrentPanel = new PublisherListPanel(myDatabase, this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
		this.repaint();
	}
	
	public void showPublisherInfoPanel() {
		remove(myCurrentPanel);
		myCurrentPanel = new PublisherInfoPanel(myDatabase, this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
		this.repaint();
	}
	
	public void showBookInfoListPanel() {
		//System.out.println("show book info list panel");
		remove(myCurrentPanel);
		myCurrentPanel = new BookInfoListPanel(myDatabase, this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
		this.repaint();
	}
	
	public void showBookInfoPanel() {
		//System.out.println("show book info panel");
		remove(myCurrentPanel);
		myCurrentPanel = new BookInfoPanel(myDatabase, this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
		this.repaint();
	}
	
	public void showSearchPanel() {
		remove(myCurrentPanel);
		myCurrentPanel = new SearchPanel(myDatabase, this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
		this.repaint();
	}
}
