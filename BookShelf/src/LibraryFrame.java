import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LibraryFrame extends JFrame{
	
	private BookShelfDB myDatabase;
	
	private JPanel myBookListPanel; //
	private JPanel myPatronListPanel; //
	private JPanel myRecordListPanel;
	private JPanel myPublisherListPanel;
	private BookInfoPanel myBookInfo;
	private JPanel myPatronInfo;
	private JPanel myPublisherInfo;
	private ButtonPanel myBottonPanel; //1
	private JPanel mySearchPanel;
	
	public LibraryFrame() {
		myDatabase = new BookShelfDB();
		this.setPreferredSize(new Dimension(800, 600));
		configPanel();
		addPanel();
        pack();
        setLocationRelativeTo(null);
	}
	
	private void configPanel() {
		BookInfo testBookInfo = new BookInfo("1234567890123", "Book Title", 2010,
                "Book Author", 1, 500, "English", 20, 3, "Book Publisher");
		myBottonPanel = new ButtonPanel();
		myBookInfo = new BookInfoPanel(testBookInfo);
	}
	
	private void addPanel() {
		add(myBookInfo, BorderLayout.CENTER);
		add(myBottonPanel, BorderLayout.SOUTH);
	}
}
