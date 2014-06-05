import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SearchPanel extends JPanel implements ActionListener{
	
	private BookShelfDB myDatabase;
	//private LibraryFrame myFrame;
	
	private JTextField myKeyword;
	private JLabel myLabel;
	private JButton mySearch;
	
	private JCheckBox myTitle;
	private JCheckBox myAuthor;
	private JCheckBox myISBN;
	private JCheckBox myYear;
	
	private JPanel mySearchPanel;
	private JPanel myResultPanel;
	
	private JTable myBookTable;
	private JScrollPane myListScrollPane;
	private String[] myColName = {"title", "author", "ISBN", "year", "format", "pageNumber",
			"language", "bookselfNumber", "layerNumber", "publisherName"};
	private Object[][] myBookData;
	private List<BookInfo> myResultList = new ArrayList<BookInfo>();
	
	
	
	public SearchPanel(BookShelfDB aDatabase, LibraryFrame aFrame) {
		super();
		myDatabase = aDatabase;
		//myFrame = aFrame;
		buildSearchPanel();
		buildResultPanel();
	}

	private void buildSearchPanel() {
		mySearchPanel = new JPanel(new GridLayout(2,1));
		
		myKeyword = new JTextField();
		myKeyword.setPreferredSize(new Dimension(400,40));
		mySearch = new JButton("Search");
		mySearch.setPreferredSize(new Dimension(100,40));
		mySearch.addActionListener(this);
		
		myLabel = new JLabel("please elect the attributes you want to search");
		
		myTitle = new JCheckBox("Title");
		myAuthor = new JCheckBox("Author");
		myISBN = new JCheckBox("ISBN");
		myYear = new JCheckBox("Year");
		
		JPanel keywordPanel = new JPanel(new FlowLayout());
		JPanel checkPanel = new JPanel(new FlowLayout());
		
		keywordPanel.add(myKeyword);
		keywordPanel.add(mySearch);
		checkPanel.add(myLabel);
		checkPanel.add(myTitle);
		checkPanel.add(myAuthor);
		checkPanel.add(myISBN);
		checkPanel.add(myYear);
		
		mySearchPanel.add(keywordPanel);
		mySearchPanel.add(checkPanel);
		add(mySearchPanel, BorderLayout.NORTH);
	}
	
	private void buildResultPanel() {
		myResultPanel = new JPanel(new BorderLayout());
		myBookData = new Object[myResultList.size()][myColName.length];
		for (int i = 0; i < myResultList.size(); i++) {
			myBookData[i][0] = myResultList.get(i).getTitle();
			myBookData[i][1] = myResultList.get(i).getAuthor();
			myBookData[i][2] = myResultList.get(i).getISBN();
			myBookData[i][3] = myResultList.get(i).getYear();
			myBookData[i][4] = myResultList.get(i).getFormat();
			myBookData[i][5] = myResultList.get(i).getPageNumber();
			myBookData[i][6] = myResultList.get(i).getLanguage();
			myBookData[i][7] = myResultList.get(i).getBookshelfNumber();
			myBookData[i][8] = myResultList.get(i).getLayerNumber();
			myBookData[i][9] = myResultList.get(i).getPublisherName();
		}
		myBookTable = new JTable(myBookData, myColName);
		myBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		myBookTable.setEnabled(false);
		myListScrollPane = new JScrollPane(myBookTable);
		myListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		myListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		myResultPanel.add(myListScrollPane, BorderLayout.CENTER);
		myResultPanel.setPreferredSize(new Dimension(600,400));
		add(myResultPanel, BorderLayout.CENTER);
		repaint();
	}
	
	private String getCondition() {
		String condition = "FALSE";
		if (myKeyword.getText() == null || myKeyword.getText().equals("")) {
			//nothing happened
		} else {
			String keyWord = myKeyword.getText();

			if (myTitle.isSelected()) {
				condition += " OR title LIKE '%" + keyWord + "%'";
			}
			if (myAuthor.isSelected()) {
				condition += " OR author LIKE'%" + keyWord + "%'";
			}
			if (myISBN.isSelected()) {
				condition += " OR ISBN LIKE '%" + keyWord + "%'";
			}
			if (myYear.isSelected()) {
				boolean isDigit = true;
				for(int i = 0; i < keyWord.length(); i++) {
					if (!Character.isDigit(keyWord.charAt(i))) {
						isDigit = false;
						break;
					}
				}
				
				if (isDigit) {
					condition += " OR `year` = " + keyWord;
				}
			}
		}
		return condition;
	}


	@Override
	public void actionPerformed(ActionEvent anEvent) {
		if (anEvent.getSource() == mySearch) {
			try {
				myResultList = myDatabase.getFilteredBookInfo(getCondition());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			remove(myResultPanel);
			buildResultPanel();
			myResultPanel.revalidate();
			add(myResultPanel);
			this.repaint();
		}
		
	}
}
