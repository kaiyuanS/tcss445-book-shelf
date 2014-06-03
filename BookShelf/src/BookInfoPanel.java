import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BookInfoPanel extends JPanel {

	BookInfo myBookInfo;
	
	private JPanel myLabelPanel;
	private JPanel myTextPanel;
	private JPanel myButtonPanel;
	
	private JLabel myISBNLabel;
	private JLabel myTitleLabel;
	private JLabel myYearLabel;
	private JLabel myAuthorLabel;
	private JLabel myFormatLabel;
	private JLabel myPageNumberLabel;
	private JLabel myLanguageLabel;
	private JLabel myBookselfNumberLabel;
	private JLabel myLayerNumberLabel;
	private JLabel myPublisherLabel;
	
	private JTextField myISBNText;
	private JTextField myTitleText;
	private JTextField myYearText;
	private JTextField myAuthorText;
	private JTextField myFormatText;
	private JTextField myPageNumberText;
	private JTextField myLanguageText;
	private JTextField myBookSelfNumberText;
	private JTextField myLayerNumberText;
	private JTextField myPublisherText;

	private JButton myRecordButton;
	private JButton myEditButton;
	
	private static Dimension LABEL_SIZE = new Dimension(100, 45);
	private static Dimension TEXT_SIZE = new Dimension(500, 45);
	private static Dimension BUTTON_SIZE = new Dimension(150, 25);
	
	public BookInfoPanel(BookInfo aBookInfo) {
		super();
		myBookInfo = aBookInfo;
		configLabel();
		configTextField();
		configButton();
		addComponents();
	}

	private void configLabel() {
		myISBNLabel = new JLabel("ISBN:");
		myISBNLabel.setPreferredSize(LABEL_SIZE);
		myTitleLabel = new JLabel("Title:");
		myTitleLabel.setPreferredSize(LABEL_SIZE);
		myYearLabel = new JLabel("Year:");
		myYearLabel.setPreferredSize(LABEL_SIZE);
		myAuthorLabel = new JLabel("Author:");
		myAuthorLabel.setPreferredSize(LABEL_SIZE);
		myFormatLabel = new JLabel("Format:");
		myFormatLabel.setPreferredSize(LABEL_SIZE);
		myPageNumberLabel = new JLabel("Page Number:");
		myPageNumberLabel.setPreferredSize(LABEL_SIZE);
		myLanguageLabel = new JLabel("Language:");
		myLanguageLabel.setPreferredSize(LABEL_SIZE);
		myBookselfNumberLabel = new JLabel("Bookself Number:");
		myBookselfNumberLabel.setPreferredSize(LABEL_SIZE);
		myLayerNumberLabel = new JLabel("Layer Number:");
		myLayerNumberLabel.setPreferredSize(LABEL_SIZE);
		myPublisherLabel = new JLabel("Publisher:");
		myPublisherLabel.setPreferredSize(LABEL_SIZE);
		
	}

	private void configTextField() {
		myISBNText = new JTextField(myBookInfo.getISBN());
		myISBNText.setPreferredSize(TEXT_SIZE);
		myTitleText = new JTextField(myBookInfo.getTitle());
		myTitleText.setPreferredSize(TEXT_SIZE);
		myYearText = new JTextField(Integer.valueOf(myBookInfo.getYear()).toString());
		myYearText.setPreferredSize(TEXT_SIZE);
		myAuthorText = new JTextField(myBookInfo.getAuthor());
		myAuthorText.setPreferredSize(TEXT_SIZE);
		myFormatText = new JTextField(myBookInfo.getFormat() == 1?"Hard cover":"Paper Back");
		myFormatText.setPreferredSize(TEXT_SIZE);
		myPageNumberText = new JTextField(Integer.valueOf(myBookInfo.getPageNumber()).toString());
		myPageNumberText.setPreferredSize(TEXT_SIZE);
		myLanguageText = new JTextField(myBookInfo.getLanguage());
		myLanguageText.setPreferredSize(TEXT_SIZE);
		myBookSelfNumberText = new JTextField(Integer.valueOf(myBookInfo.getBookselfNumber()).toString());
		myBookSelfNumberText.setPreferredSize(TEXT_SIZE);
		myLayerNumberText = new JTextField(Integer.valueOf(myBookInfo.getLayerNumber()).toString());
		myLayerNumberText.setPreferredSize(TEXT_SIZE);
		myPublisherText = new JTextField(myBookInfo.getPublisherName());
		myPublisherText.setPreferredSize(TEXT_SIZE);
		
		myISBNText.setEditable(false);
		myTitleText.setEditable(false);
		myYearText.setEditable(false);
		myAuthorText.setEditable(false);
		myFormatText.setEditable(false);
		myPageNumberText.setEditable(false);
		myLanguageText.setEditable(false);
		myBookSelfNumberText.setEditable(false);
		myLayerNumberText.setEditable(false);
		myPublisherText.setEditable(false);
		
	}
	private void configButton() {
		myRecordButton = new JButton("View Record");
		myRecordButton.setPreferredSize(BUTTON_SIZE);
		myEditButton = new JButton("Edit");
		myEditButton.setPreferredSize(BUTTON_SIZE);
	}
	private void addComponents() {
		myLabelPanel = new JPanel();
		myLabelPanel.setLayout(new GridLayout(10, 2));
		myTextPanel = new JPanel();
		myTextPanel.setLayout(new GridLayout(10, 2));
		myButtonPanel = new JPanel();
		myButtonPanel.setLayout(new FlowLayout());
		
		myLabelPanel.add(myISBNLabel);
		myTextPanel.add(myISBNText);
		myLabelPanel.add(myTitleLabel);
		myTextPanel.add(myTitleText);
		myLabelPanel.add(myYearLabel);
		myTextPanel.add(myYearText);
		myLabelPanel.add(myAuthorLabel);
		myTextPanel.add(myAuthorText);
		myLabelPanel.add(myFormatLabel);
		myTextPanel.add(myFormatText);
		myLabelPanel.add(myPageNumberLabel);
		myTextPanel.add(myPageNumberText);
		myLabelPanel.add(myLanguageLabel);
		myTextPanel.add(myLanguageText);
		myLabelPanel.add(myBookselfNumberLabel);
		myTextPanel.add(myBookSelfNumberText);
		myLabelPanel.add(myLayerNumberLabel);
		myTextPanel.add(myLayerNumberText);
		myLabelPanel.add(myPublisherLabel);
		myTextPanel.add(myPublisherText);
		
		myButtonPanel.add(myRecordButton);
		myButtonPanel.add(myEditButton);
		
		add(myLabelPanel, BorderLayout.WEST);
		add(myTextPanel, BorderLayout.CENTER);
		add(myButtonPanel, BorderLayout.SOUTH);
	}



	
}
