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
	private JPanel myPatronListPanel; //
	private JPanel myRecordListPanel;
	private JPanel myPublisherListPanel;
	private BookInfoPanel myBookInfo;
	private JPanel myPatronInfo;
	private JPanel myPublisherInfo;
	private JPanel myButtonPanel; //1
	private JPanel mySearchPanel;
	
	
	public LibraryFrame() {
		myDatabase = new BookShelfDB();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(new BorderLayout());
		addPanel();
		initPanels();
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
	}
	
	private void initPanels() {
		initButtonPanel();
		initBookListPanel();
		this.add(myButtonPanel, BorderLayout.SOUTH);
		this.add(myBookListPanel, BorderLayout.CENTER);
		
	}
	
	private void initButtonPanel() {
		myButtonPanel = new JPanel();
		JButton searchBooks = new JButton("Search Books");
		JButton searchPatrons = new JButton("Search Patrons");
		JButton searchKeyword = new JButton("Search Keyword");
		myButtonPanel.setLayout(new FlowLayout());
		
		searchBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theActionEvent) {
				
			}
		});
		
		searchPatrons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theActionEvent) {
				
			}
		});
		
		searchKeyword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theActionEvent) {
				
			}
		});
		
		myButtonPanel.add(searchBooks);
		myButtonPanel.add(searchPatrons);
		myButtonPanel.add(searchKeyword);
		
	}
	
	private void initBookListPanel() {
		myBookListPanel = new JPanel();
		myBookListPanel.setLayout(new BorderLayout());
		
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
	
	private class BookSearchListener implements ActionListener {
		
		JFrame myMainFrame;
		
		public BookSearchListener(final JFrame theFrame) {
			myMainFrame = theFrame;
		}
		
		public void actionPerformed(ActionEvent theActionEvent) {
			//myMainFrame.add(comp)
		}
	}
	
	private void configPanel() {
		BookInfo testBookInfo = new BookInfo("1234567890123", "Book Title", 2010,
                "Book Author", 1, 500, "English", 20, 3, "Book Publisher");
		myBottonPanel = new ButtonPanel();
		myBookInfo = new BookInfoPanel(testBookInfo);
		myBookListPanel = new BookListPanel(myDatabase);
	}
	
	private void addPanel() {
		//add(myBookInfo, BorderLayout.CENTER);
		add(myBookListPanel, BorderLayout.CENTER);
		add(myBottonPanel, BorderLayout.SOUTH);
	}
}
