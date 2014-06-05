import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LibraryFrame extends JFrame{
	
	private BookShelfDB myDatabase;
	
	private JPanel myButtonPanel;
	
	private JPanel myCurrentPanel;
	
	
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
	
	
	private void initPanels() {
		initButtonPanel();
		myCurrentPanel = new BookInfoListPanel(myDatabase, this);
		
		this.add(myCurrentPanel, BorderLayout.CENTER);
		
		this.add(myButtonPanel, BorderLayout.SOUTH);
	}
	
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
	
	public void showBookPanel() {
		System.out.println("LibraryFrame: Changing to BookPanel");
		replaceContentPanel(new BookPanel(this, myDatabase));
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
		replaceContentPanel(new PatronListPanel(this, myDatabase));
	}
	
	public void showPatronPanel() {
		replaceContentPanel(new PatronPanel(this, myDatabase));
	}
	
	public void showPatronRecordPanel() {
		replaceContentPanel(new PatronRecordPanel(this, myDatabase));
	}
}