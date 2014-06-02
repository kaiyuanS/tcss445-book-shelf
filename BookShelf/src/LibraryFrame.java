import javax.swing.JFrame;
import javax.swing.JPanel;


public class LibraryFrame extends JFrame{
	
	private BookShelfDB myDatabase;
	
	private JPanel myBookListPanel;
	private JPanel myPatronListPanel;
	private JPanel myRecordListPanel;
	private JPanel myBottonPanel;
	private JPanel myFilterPanel;
	private JPanel mySearchPanel;
	
	public LibraryFrame() {
		myDatabase = new BookShelfDB();
		addPanel();
        pack();
        setLocationRelativeTo(null);
	}
	
	private void addPanel() {
		
	}
}
