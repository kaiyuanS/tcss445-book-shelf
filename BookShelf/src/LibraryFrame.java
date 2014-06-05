import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
/**
 * The LibraryFrame frame will hold all the panels necessary for the BookShelf library system. 
 * 
 * @author Kevin Alexander
 * @author Shi Kaiyuan
 * @version June 4, 2014
 *
 */
public class LibraryFrame extends JFrame{
	
	private BookShelfDB myDatabase;
	
	private JPanel myButtonPanel;
	
	private JPanel myCurrentPanel;
	
	/**
	 * The LibraryFrame constructor. It initializes the frame and sets the first panel to show a
	 * list of BookInfo items.
	 */
	public LibraryFrame() {
		myDatabase = new BookShelfDB();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(850, 600));
		this.setLayout(new BorderLayout());
		initPanels();
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
	}
	
	/**
	 * This method initializes the first panel (BookInfoListPanel) that will be placed
	 * on the center of the LibraryFrame.
	 */
	private void initPanels() {
		initButtonPanel();
		myCurrentPanel = new BookInfoListPanel(myDatabase, this);
		
		this.add(myCurrentPanel, BorderLayout.CENTER);
		
		this.add(myButtonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * This method initializes the buttons that will be on the LibraryFrame.
	 */
	private void initButtonPanel() {
		myButtonPanel = new JPanel();
		JButton searchBooks = new JButton("Search Book Info");
		JButton searchPublishers = new JButton("Search Publisher Info");
		JButton searchPatrons = new JButton("Search Patrons");
		JButton searchKeyword = new JButton("Search Keyword");
		JButton viewRecord = new JButton("View Record");
		JButton searchTheBooks = new JButton("Search Books");
		myButtonPanel.setLayout(new FlowLayout());
		
		searchBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theActionEvent) {
				showBookInfoListPanel();
			}
		});
		
		searchPublishers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theActionEvent) {
				showPublisherListPanel();
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
		
		viewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theActionEvent) {
				showPatronRecordListPanel(null);
			}
		});
		
		searchTheBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showBookListPanel();
			}
		});
		
		myButtonPanel.add(searchTheBooks);
		myButtonPanel.add(searchPublishers);
		myButtonPanel.add(searchBooks);
		myButtonPanel.add(searchPatrons);
		myButtonPanel.add(searchKeyword);
		myButtonPanel.add(viewRecord);
		
	}
	
	/**
	 * This method is used to replace the current JPanel on the LibraryFrame.
	 * @param thePanel A JPanel.
	 */
	public void replaceContentPanel(JPanel thePanel) {
		this.remove(myCurrentPanel);
		myCurrentPanel = thePanel;
		
		this.add(myCurrentPanel, BorderLayout.CENTER);
		this.pack();
		this.repaint();
	}
	
	/**
	 * This method replaces the current JPanel with a PatronRecordList panel.
	 * @param theObject An object which is used to initialize the PatronRecordList panel.
	 */
	public void showPatronRecordListPanel(Object theObject) {
		System.out.println("LibraryFrame: Changing to PatronRecordList");
		replaceContentPanel(new PatronRecordList(this, myDatabase, theObject));
	}
	
	/**
	 * This method replaces the current JPanel with a BookListPanel.
	 */
	public void showBookListPanel() {
		System.out.println("LibraryFrame: Changing to BookListPanel");
		replaceContentPanel(new BookListPanel(this, myDatabase));
	}
	
	/**
	 * This method replaces the current JPanel with a BookPanel.
	 */
	public void showBookPanel() {
		System.out.println("LibraryFrame: Changing to BookPanel");
		replaceContentPanel(new BookPanel(this, myDatabase));
	}
	
	/**
	 * This method replaces the current JPanel with a PublisherListPanel.
	 */
	public void showPublisherListPanel() {
		replaceContentPanel(new PublisherListPanel(myDatabase, this));
	}
	
	/**
	 * This method replaces the current JPanel with a PublisherInfoPanel.
	 */
	public void showPublisherInfoPanel() {
		replaceContentPanel(new PublisherInfoPanel(myDatabase, this));
	}
	
	/**
	 * This method replaces the current JPanel with a BookInfoListPanel.
	 */
	public void showBookInfoListPanel() {
		replaceContentPanel(new BookInfoListPanel(myDatabase, this));
	}
	
	/**
	 * This method replaces the current JPanel with a BookInfoPanel.
	 */
	public void showBookInfoPanel() {
		replaceContentPanel(new BookInfoPanel(myDatabase, this));
	}
	
	/**
	 * This method replaces the current JPanel with a SearchPanel.
	 */
	public void showSearchPanel() {
		replaceContentPanel(new SearchPanel(myDatabase, this));
	}
	
	/**
	 * This method replaces the current JPanel with a PatronListPanel.
	 */
	public void showPatronListPanel() {
		replaceContentPanel(new PatronListPanel(this, myDatabase));
	}
	
	/**
	 * This method replaces the current JPanel with a PatronPanel.
	 */
	public void showPatronPanel() {
		replaceContentPanel(new PatronPanel(this, myDatabase));
	}
	
	/**
	 * This method replaces the current JPanel with a PatronRecordPanel..
	 */
	public void showPatronRecordPanel() {
		replaceContentPanel(new PatronRecordPanel(this, myDatabase));
	}
}