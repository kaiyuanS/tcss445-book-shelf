import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class BookListPanel extends JPanel{
	
	private BookShelfDB myDatebase;
	private List<BookInfo> myBookInfoList = new ArrayList<BookInfo>();
	
	private JPanel myFilterPanel;
	private JPanel myListPanel;
	
	private JTable myBookTable;
	private JScrollPane myListScrollPane;
	private String[] myColName = {"Title", "Author", "ISBN", "Year", "format", "Page Number",
			"Language", "Bookself Number", "Layer Number", "Publisher Name"};
	private Object[][] myBookData;
	
	private JLabel myYearLabel;
	private JRadioButton myYearBefore1990;
	private JRadioButton myYear1991_2000;
	private JRadioButton myYear2001_2010;
	private JRadioButton myYearAfter2011;
	
	private JLabel myPageLabel;
	private JRadioButton myPageLess500;
	private JRadioButton myPage501_1000;
	private JRadioButton myPage1001_2000;
	private JRadioButton myPageMore2000;
	
	private JLabel myFormatLabel;
	private JRadioButton myHardCover;
	private JRadioButton myPaperBack;
	
	private JLabel myLanguageLabel;
	private JRadioButton myEnglish;
	private JRadioButton mySpanish;
	private JRadioButton myFrench;
	private JRadioButton myChinese;
	private JRadioButton myRussian;
	private JRadioButton myOtherLanguage;
	
	private static Dimension LABEL_SIZE = new Dimension(200, 25);
	private static Dimension BUTTON_SIZE = new Dimension(200, 20);
	
	public BookListPanel(BookShelfDB aDatabase) {
		super();
		myDatebase = aDatabase;
		configButton();
		configFilter();
		configList();
		addComponents();
		myBookInfoList.add(new BookInfo("1234567890123", "Book Title", 2010,
                "Book Author", 1, 500, "English", 20, 3, "Book Publisher"));
	}
	
	private void configButton() {
		myYearLabel = new JLabel("Year: ");
		myYearLabel.setPreferredSize(LABEL_SIZE);
		myYearBefore1990 = new JRadioButton("Before 1990");
		myYearBefore1990.setPreferredSize(LABEL_SIZE);
		myYear1991_2000 = new JRadioButton("1991 ~ 2000");
		myYear1991_2000.setPreferredSize(LABEL_SIZE);
		myYear2001_2010 = new JRadioButton("2001 ~ 2010");
		myYear2001_2010.setPreferredSize(LABEL_SIZE);
		myYearAfter2011 = new JRadioButton("After 2010");
		myYearAfter2011.setPreferredSize(LABEL_SIZE);
		ButtonGroup yearGroup = new ButtonGroup();
		yearGroup.add(myYearBefore1990);
		yearGroup.add(myYear1991_2000);
		yearGroup.add(myYear2001_2010);
		yearGroup.add(myYearAfter2011);
		
		myPageLabel = new JLabel("Page Number: ");
		myPageLabel.setPreferredSize(LABEL_SIZE);
		myPageLess500 = new JRadioButton("Less than 500");
		myPageLess500.setPreferredSize(LABEL_SIZE);
		myPage501_1000 = new JRadioButton("501 ~ 1000");
		myPage501_1000.setPreferredSize(LABEL_SIZE);
		myPage1001_2000 = new JRadioButton("1001 ~ 2000");
		myPage1001_2000.setPreferredSize(LABEL_SIZE);
		myPageMore2000 = new JRadioButton("More than 2000");
		myPageMore2000.setPreferredSize(LABEL_SIZE);
		ButtonGroup pageGroup = new ButtonGroup();
		pageGroup.add(myPageLess500);
		pageGroup.add(myPage501_1000);
		pageGroup.add(myPage1001_2000);
		pageGroup.add(myPageMore2000);
		
		myFormatLabel = new JLabel("Format: ");
		myFormatLabel.setPreferredSize(LABEL_SIZE);
		myHardCover = new JRadioButton("Hard cover");
		myHardCover.setPreferredSize(LABEL_SIZE);
		myPaperBack = new JRadioButton("Paper back");
		myPaperBack.setPreferredSize(LABEL_SIZE);
		ButtonGroup formatGroup = new ButtonGroup();
		formatGroup.add(myHardCover);
		formatGroup.add(myPaperBack);
		
		myLanguageLabel = new JLabel("Lannguage: ");
		myLanguageLabel.setPreferredSize(LABEL_SIZE);
		myEnglish = new JRadioButton("English");
		myEnglish.setPreferredSize(LABEL_SIZE);
		mySpanish = new JRadioButton("Spanish");
		mySpanish.setPreferredSize(LABEL_SIZE);
		myFrench = new JRadioButton("French");
		myFrench.setPreferredSize(LABEL_SIZE);
		myChinese = new JRadioButton("Chinese");
		myChinese.setPreferredSize(LABEL_SIZE);
		myRussian = new JRadioButton("Russian");
		myRussian.setPreferredSize(LABEL_SIZE);
		myOtherLanguage = new JRadioButton("Other");
		myOtherLanguage.setPreferredSize(LABEL_SIZE);
		ButtonGroup languageGroup = new ButtonGroup();
		languageGroup.add(myEnglish);
		languageGroup.add(mySpanish);
		languageGroup.add(myFrench);
		languageGroup.add(myChinese);
		languageGroup.add(myRussian);
		languageGroup.add(myOtherLanguage);
	}
	
	private void configFilter() {
		myFilterPanel = new JPanel();
		myFilterPanel.setLayout(new GridLayout(20, 1));
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
	}
	
	private void configList() {
		myListPanel= new JPanel();
		myBookData = new Object[myBookInfoList.size()][myColName.length];
		for (int i=0; i<myBookInfoList.size(); i++) {
			myBookData[i][0] = myBookInfoList.get(i).getTitle();
			myBookData[i][1] = myBookInfoList.get(i).getAuthor();
			myBookData[i][2] = myBookInfoList.get(i).getISBN();
			myBookData[i][3] = myBookInfoList.get(i).getYear();
			myBookData[i][4] = myBookInfoList.get(i).getFormat() == 1?"Hard cover":"Paper Back";
			myBookData[i][5] = myBookInfoList.get(i).getPageNumber();
			myBookData[i][6] = myBookInfoList.get(i).getLanguage();
			myBookData[i][7] = myBookInfoList.get(i).getBookselfNumber();
			myBookData[i][8] = myBookInfoList.get(i).getLayerNumber();
			myBookData[i][9] = myBookInfoList.get(i).getPublisherName();
		}
		
		myBookTable = new JTable(myBookData, myColName);
		myListScrollPane = new JScrollPane(myBookTable);
		myListPanel.add(myListScrollPane);
	}
	
	private void addComponents() {
		add(myFilterPanel, BorderLayout.WEST);
		add(myListPanel, BorderLayout.CENTER);
	}
	
}
