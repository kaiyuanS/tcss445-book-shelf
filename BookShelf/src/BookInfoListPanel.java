import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * This is a list view of the Book information list
 * @author Kaiyuan Shi
 * @version Spr. 2014
 */
@SuppressWarnings("serial")
public class BookInfoListPanel extends JPanel implements ActionListener, TableModelListener{
	
	/** the database */
	private BookShelfDB myDatebase;
	
	/** the frame */
	private LibraryFrame myFrame;
	
	/** the list of the book information */
	private List<BookInfo> myBookInfoList = new ArrayList<BookInfo>();
	
	/** the filter panel */
	private JPanel myFilterPanel;
	
	/** the list panel */
	private JPanel myListPanel;
	
	/** the data table */
	private JTable myBookTable;
	
	/** the scroll pane */
	private JScrollPane myListScrollPane;
	
	/** the cloumn name array */
	private String[] myColName = {"title", "author", "ISBN", "year", "format", "pageNumber",
			"language", "bookshelfNumber", "layerNumber", "publisherName"};
	
	/** the table data */
	private Object[][] myBookData;
	
	/* all the  buttons */
	JButton mySearch;
	JButton myClear;
	JButton myAdd;
	JButton myDelete;
	
	/* the filter labels and radio buttons */
	private JLabel myYearLabel;
	ButtonGroup myYearGroup;
	private JRadioButton myYearBefore1990;
	private JRadioButton myYear1991_2000;
	private JRadioButton myYear2001_2010;
	private JRadioButton myYearAfter2011;
	
	private JLabel myPageLabel;
	ButtonGroup myPageGroup;
	private JRadioButton myPageLess500;
	private JRadioButton myPage501_1000;
	private JRadioButton myPage1001_2000;
	private JRadioButton myPageMore2000;
	
	private JLabel myFormatLabel;
	ButtonGroup myFormatGroup;
	private JRadioButton myHardCover;
	private JRadioButton myPaperBack;
	
	private JLabel myLanguageLabel;
	ButtonGroup myLanguageGroup;
	private JRadioButton myEnglish;
	private JRadioButton mySpanish;
	private JRadioButton myFrench;
	private JRadioButton myChinese;
	private JRadioButton myRussian;
	private JRadioButton myOtherLanguage;
	
	private static Dimension LABEL_SIZE = new Dimension(200, 20);
	private static Dimension BUTTON_SIZE = new Dimension(200, 15);
	
	/**
	 * the construtor
	 * @param aDatabase the database
	 * @param aFrame the main frame
	 */
	public BookInfoListPanel(BookShelfDB aDatabase, LibraryFrame aFrame) {
		super();
		myDatebase = aDatabase;
		myFrame = aFrame;
		configButton();
		configFilter();
		configList();
		addComponents();
	}
	
	/**
	 * initialize the panel
	 */
	public void initialize() {
		myClear.doClick();
		mySearch.doClick();
		
	}
	
	/**
	 * ads the buttons
	 */
	private void configButton() {
		myYearLabel = new JLabel("Year: ");
		myYearLabel.setPreferredSize(LABEL_SIZE);
		myYearBefore1990 = new JRadioButton("Before 1990");
		myYearBefore1990.setPreferredSize(BUTTON_SIZE);
		myYear1991_2000 = new JRadioButton("1991 ~ 2000");
		myYear1991_2000.setPreferredSize(BUTTON_SIZE);
		myYear2001_2010 = new JRadioButton("2001 ~ 2010");
		myYear2001_2010.setPreferredSize(BUTTON_SIZE);
		myYearAfter2011 = new JRadioButton("After 2010");
		myYearAfter2011.setPreferredSize(BUTTON_SIZE);
		myYearGroup = new ButtonGroup();
		myYearGroup.add(myYearBefore1990);
		myYearGroup.add(myYear1991_2000);
		myYearGroup.add(myYear2001_2010);
		myYearGroup.add(myYearAfter2011);
		
		myPageLabel = new JLabel("Page Number: ");
		myPageLabel.setPreferredSize(LABEL_SIZE);
		myPageLess500 = new JRadioButton("Less than 500");
		myPageLess500.setPreferredSize(BUTTON_SIZE);
		myPage501_1000 = new JRadioButton("501 ~ 1000");
		myPage501_1000.setPreferredSize(BUTTON_SIZE);
		myPage1001_2000 = new JRadioButton("1001 ~ 2000");
		myPage1001_2000.setPreferredSize(BUTTON_SIZE);
		myPageMore2000 = new JRadioButton("More than 2000");
		myPageMore2000.setPreferredSize(BUTTON_SIZE);
		myPageGroup = new ButtonGroup();
		myPageGroup.add(myPageLess500);
		myPageGroup.add(myPage501_1000);
		myPageGroup.add(myPage1001_2000);
		myPageGroup.add(myPageMore2000);
		
		myFormatLabel = new JLabel("Format: ");
		myFormatLabel.setPreferredSize(LABEL_SIZE);
		myHardCover = new JRadioButton("Hard cover");
		myHardCover.setPreferredSize(BUTTON_SIZE);
		myPaperBack = new JRadioButton("Paper back");
		myPaperBack.setPreferredSize(BUTTON_SIZE);
		myFormatGroup = new ButtonGroup();
		myFormatGroup.add(myHardCover);
		myFormatGroup.add(myPaperBack);
		
		myLanguageLabel = new JLabel("Lannguage: ");
		myLanguageLabel.setPreferredSize(LABEL_SIZE);
		myEnglish = new JRadioButton("English");
		myEnglish.setPreferredSize(BUTTON_SIZE);
		mySpanish = new JRadioButton("Spanish");
		mySpanish.setPreferredSize(BUTTON_SIZE);
		myFrench = new JRadioButton("French");
		myFrench.setPreferredSize(BUTTON_SIZE);
		myChinese = new JRadioButton("Chinese");
		myChinese.setPreferredSize(BUTTON_SIZE);
		myRussian = new JRadioButton("Russian");
		myRussian.setPreferredSize(BUTTON_SIZE);
		myOtherLanguage = new JRadioButton("Other");
		myOtherLanguage.setPreferredSize(BUTTON_SIZE);
		myLanguageGroup = new ButtonGroup();
		myLanguageGroup.add(myEnglish);
		myLanguageGroup.add(mySpanish);
		myLanguageGroup.add(myFrench);
		myLanguageGroup.add(myChinese);
		myLanguageGroup.add(myRussian);
		myLanguageGroup.add(myOtherLanguage);
	}
	
	/**
	 * configure the filter components
	 */
	private void configFilter() {
		myClear = new JButton("Clear All");
		mySearch = new JButton("Search");
		myFilterPanel = new JPanel();
		myFilterPanel.setPreferredSize(new Dimension(200, 500));
		myClear.setPreferredSize(new Dimension(100, 20));
		myClear.addActionListener(this);
		mySearch.addActionListener(this);
		myFilterPanel.setLayout(new GridLayout(22, 1));
		myFilterPanel.add(myYearLabel);
		myFilterPanel.add(myYearBefore1990);
		myFilterPanel.add(myYear1991_2000);
		myFilterPanel.add(myYear2001_2010);
		myFilterPanel.add(myYearAfter2011);
		myFilterPanel.add(myPageLabel);
		myFilterPanel.add(myPageLess500);
		myFilterPanel.add(myPage501_1000);
		myFilterPanel.add(myPage1001_2000);
		myFilterPanel.add(myPageMore2000);
		myFilterPanel.add(myFormatLabel);
		myFilterPanel.add(myHardCover);
		myFilterPanel.add(myPaperBack);
		myFilterPanel.add(myLanguageLabel);
		myFilterPanel.add(myEnglish);
		myFilterPanel.add(mySpanish);
		myFilterPanel.add(myFrench);
		myFilterPanel.add(myChinese);
		myFilterPanel.add(myRussian);
		myFilterPanel.add(myOtherLanguage);
		myFilterPanel.add(mySearch);
		myFilterPanel.add(myClear);
	}
	
	/**
	 * configure the list
	 */
	private void configList() {
		myListPanel= new JPanel();
		
		refreshData();
		
		myBookTable = new JTable(myBookData, myColName);
		myBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		myBookTable.getModel().addTableModelListener(this);
		myListScrollPane = new JScrollPane(myBookTable);
		myListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		myListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		myAdd = new JButton("Add");
		myDelete = new JButton("Delete");
		JPanel buttons = new JPanel(new FlowLayout());
		buttons.add(myAdd);
		buttons.add(myDelete);
		myAdd.addActionListener(this);
		myDelete.addActionListener(this);
		myListPanel.setLayout(new BorderLayout());
		myListPanel.add(myListScrollPane, BorderLayout.CENTER);
		myListPanel.add(buttons, BorderLayout.SOUTH);
	}
	
	/**
	 * add all components
	 */
	private void addComponents() {
		add(myFilterPanel, BorderLayout.WEST);
		add(myListPanel, BorderLayout.CENTER);
	}
	
	/**
	 * refresh the data according the the list get from the database
	 */
	private void refreshData() {
		try {
			myBookInfoList = myDatebase.getBookInfo();
			myBookData = new Object[myBookInfoList.size()][myColName.length];
			for (int i=0; i<myBookInfoList.size(); i++) {
				myBookData[i][0] = myBookInfoList.get(i).getTitle();
				myBookData[i][1] = myBookInfoList.get(i).getAuthor();
				myBookData[i][2] = myBookInfoList.get(i).getISBN();
				myBookData[i][3] = myBookInfoList.get(i).getYear();
				myBookData[i][4] = myBookInfoList.get(i).getFormat();
				myBookData[i][5] = myBookInfoList.get(i).getPageNumber();
				myBookData[i][6] = myBookInfoList.get(i).getLanguage();
				myBookData[i][7] = myBookInfoList.get(i).getBookshelfNumber();
				myBookData[i][8] = myBookInfoList.get(i).getLayerNumber();
				myBookData[i][9] = myBookInfoList.get(i).getPublisherName();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get the SQL string according to the filter
	 */
	private void filterBookInfo() {
		String aCondition = "TRUE";
		
		if (myYearBefore1990.isSelected()) {
			aCondition += " AND `year` <= 1990";
		} else if (myYear1991_2000.isSelected()) {
			aCondition += " AND `year` > 1990 AND year <= 2000";
		} else if (myYear2001_2010.isSelected()) {
			aCondition += " AND `year` > 2000 AND year <= 2010";
		} else if (myYearAfter2011.isSelected()) {
			aCondition += " AND `year` > 2010";
		}
		if (myPageLess500.isSelected()) {
			aCondition += " AND pageNumber <= 500";
		} else if (myPage501_1000.isSelected()) {
			aCondition += " AND pageNumber > 500 AND pageNumber <= 1000";
		} else if (myPage1001_2000.isSelected()) {
			aCondition += " AND pageNumber > 1000 AND pageNumber <= 2000";
		} else if (myPageMore2000.isSelected()) {
			aCondition += " AND pageNumber > 2000";
		}
		if (myHardCover.isSelected()) {
			aCondition += " AND `format` = 'Hard Cover'";
		} else if (myPaperBack.isSelected()) {
			aCondition += " AND `format` = 'Paper Back'";
		}
		if (myEnglish.isSelected()) {
			aCondition += " AND `Language` = 'English'";
		} else if (mySpanish.isSelected()) {
			aCondition += " AND `Language` = 'Spanish'";
		} else if (myFrench.isSelected()) {
			aCondition += " AND `Language` = 'French'";
		} else if (myChinese.isSelected()) {
			aCondition += " AND `Language` = 'Chinese'";
		} else if (myRussian.isSelected()) {
			aCondition += " AND `Language` = 'Russian'";
		} else if (myOtherLanguage.isSelected()) {
			aCondition += " AND `Language` <> 'English' AND"
					+ " Language <> 'Spanish' AND"
					+ " Language <> 'French' AND"
					+ " Language <> 'Chinese' AND"
					+ " Language <> 'Russian'";
		}
		
		try {
			myBookInfoList = myDatebase.getFilteredBookInfo(aCondition);
			refreshTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method shows the filtered the filtered bookInfo in the JTable
	 */
	private void refreshTable() {
		for (int i=0; i<myBookData.length; i++) {
			if (i < myBookInfoList.size()) {
				myBookData[i][0] = myBookInfoList.get(i).getTitle();
				myBookData[i][1] = myBookInfoList.get(i).getAuthor();
				myBookData[i][2] = myBookInfoList.get(i).getISBN();
				myBookData[i][3] = myBookInfoList.get(i).getYear();
				myBookData[i][4] = myBookInfoList.get(i).getFormat();
				myBookData[i][5] = myBookInfoList.get(i).getPageNumber();
				myBookData[i][6] = myBookInfoList.get(i).getLanguage();
				myBookData[i][7] = myBookInfoList.get(i).getBookshelfNumber();
				myBookData[i][8] = myBookInfoList.get(i).getLayerNumber();
				myBookData[i][9] = myBookInfoList.get(i).getPublisherName();
			} else {
				myBookData[i][0] = null;
				myBookData[i][1] = null;
				myBookData[i][2] = null;
				myBookData[i][3] = null;
				myBookData[i][4] = null;
				myBookData[i][5] = null;
				myBookData[i][6] = null;
				myBookData[i][7] = null;
				myBookData[i][8] = null;
				myBookData[i][9] = null;
			}
		}
		
		myBookTable.repaint();

	}
	
	private void unSelectedAll() {
		myYearGroup.clearSelection();
		myPageGroup.clearSelection();
		myFormatGroup.clearSelection();
		myLanguageGroup.clearSelection();
	}
	
	/**
	 * this method change the panel according to the buttons the user clicked
	 */
	@Override
	public void actionPerformed(ActionEvent anEvent) {
		if (anEvent.getSource() == myClear) { //clear selections
			unSelectedAll();
			filterBookInfo();
		} else if (anEvent.getSource() == mySearch) { //search the bookInfo by filter
			filterBookInfo();
		} else if (anEvent.getSource() == myAdd) { //add a new bookInfo
			myFrame.showBookInfoPanel();
		} else if (anEvent.getSource() == myDelete) { //delete a bookInfo 
			int row = myBookTable.getSelectedRow();
			if (row != -1) {
				String theISBN = (String)myBookData[row][2];
				myDatebase.removeBookInfo(theISBN);
				mySearch.doClick();
			}
		}
		
	}
	
	/**
	 * when bookInfo was edited in the JTable,
	 * modify the data it in the database 
	 */
	@Override
	public void tableChanged(TableModelEvent anEvent) {
		int row = anEvent.getFirstRow();
        int column = anEvent.getColumn();
        TableModel model = (TableModel)anEvent.getSource();
        String columnName = model.getColumnName(column);
        String ISBN = (String)model.getValueAt(row, 2);
        Object data = model.getValueAt(row, column);

        myDatebase.updateBookInfo(ISBN, columnName, data);
		
	}

	
}
