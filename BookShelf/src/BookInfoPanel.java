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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * this class is a book information panel
 * @author Kaiyuan Shi
 * @version Spr. 2014
 */
@SuppressWarnings("serial")
public class BookInfoPanel extends JPanel implements ActionListener{

	/** a book's information */
	BookInfo myBookInfo;
	
	/** the database */
	BookShelfDB myDatabase;

	/** the main frame */
	LibraryFrame myFrame;
	
	/** the label panel */
	private JPanel myLabelPanel;
	
	/** the text panel */
	private JPanel myTextPanel;
	
	/** the button panel */
	private JPanel myButtonPanel;
	
	/* all of the labels */
	private JLabel myISBNLabel;
	private JLabel myTitleLabel;
	private JLabel myYearLabel;
	private JLabel myAuthorLabel;
	private JLabel myFormatLabel;
	private JLabel myPageNumberLabel;
	private JLabel myLanguageLabel;
	private JLabel myBookshelfNumberLabel;
	private JLabel myLayerNumberLabel;
	private JLabel myPublisherLabel;
	
	/* all of the text fields */
	private JTextField myISBNText;
	private JTextField myTitleText;
	private JTextField myYearText;
	private JTextField myAuthorText;
	private JTextField myFormatText;
	private JTextField myPageNumberText;
	private JTextField myLanguageText;
	private JTextField myBookShelfNumberText;
	private JTextField myLayerNumberText;
	private JTextField myPublisherText;
	
	/** the "add" button */
	private JButton myAddButton;
	
	/* default size of the components */
	private static Dimension LABEL_SIZE = new Dimension(150, 45);
	private static Dimension TEXT_SIZE = new Dimension(500, 45);
	private static Dimension BUTTON_SIZE = new Dimension(150, 25);
	
	/**
	 * the constructor
	 * @param aDatabase the data base
	 * @param aFrame the main frame
	 */
	public BookInfoPanel(BookShelfDB aDatabase, LibraryFrame aFrame) {
		super();
		myDatabase = aDatabase;
		myFrame = aFrame;
		configLabel();
		configTextField();
		configButton();
		addComponents();
	}

	/**
	 * configure the labels
	 */
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
		myBookshelfNumberLabel = new JLabel("Bookshelf Number:");
		myBookshelfNumberLabel.setPreferredSize(LABEL_SIZE);
		myLayerNumberLabel = new JLabel("Layer Number:");
		myLayerNumberLabel.setPreferredSize(LABEL_SIZE);
		myPublisherLabel = new JLabel("Publisher:");
		myPublisherLabel.setPreferredSize(LABEL_SIZE);
		
	}

	/**
	 * configure the text fields
	 */
	private void configTextField() {
		myISBNText = new JTextField();
		myISBNText.setPreferredSize(TEXT_SIZE);
		myTitleText = new JTextField();
		myTitleText.setPreferredSize(TEXT_SIZE);
		myYearText = new JTextField();
		myYearText.setPreferredSize(TEXT_SIZE);
		myAuthorText = new JTextField();
		myAuthorText.setPreferredSize(TEXT_SIZE);
		myFormatText = new JTextField();
		myFormatText.setPreferredSize(TEXT_SIZE);
		myPageNumberText = new JTextField();
		myPageNumberText.setPreferredSize(TEXT_SIZE);
		myLanguageText = new JTextField();
		myLanguageText.setPreferredSize(TEXT_SIZE);
		myBookShelfNumberText = new JTextField();
		myBookShelfNumberText.setPreferredSize(TEXT_SIZE);
		myLayerNumberText = new JTextField();
		myLayerNumberText.setPreferredSize(TEXT_SIZE);
		myPublisherText = new JTextField();
		myPublisherText.setPreferredSize(TEXT_SIZE);
		
		myISBNText.setEditable(true);
		myTitleText.setEditable(true);
		myYearText.setEditable(true);
		myAuthorText.setEditable(true);
		myFormatText.setEditable(true);
		myPageNumberText.setEditable(true);
		myLanguageText.setEditable(true);
		myBookShelfNumberText.setEditable(true);
		myLayerNumberText.setEditable(true);
		myPublisherText.setEditable(true);
		
	}
	
	/**
	 * configure the buttons
	 */
	private void configButton() {
		myAddButton = new JButton("Add");
		myAddButton.setPreferredSize(BUTTON_SIZE);
		myAddButton.addActionListener(this);
	}
	
	/**
	 * add all the components into the panel
	 */
	private void addComponents() {
		myLabelPanel = new JPanel();
		myLabelPanel.setLayout(new GridLayout(10, 1));
		myTextPanel = new JPanel();
		myTextPanel.setLayout(new GridLayout(10, 1));
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
		myLabelPanel.add(myBookshelfNumberLabel);
		myTextPanel.add(myBookShelfNumberText);
		myLabelPanel.add(myLayerNumberLabel);
		myTextPanel.add(myLayerNumberText);
		myLabelPanel.add(myPublisherLabel);
		myTextPanel.add(myPublisherText);
		
		myButtonPanel.add(myAddButton);
		
		setLayout(new BorderLayout());
		add(myLabelPanel, BorderLayout.WEST);
		add(myTextPanel, BorderLayout.CENTER);
		add(myButtonPanel, BorderLayout.SOUTH);
	}

	/**
	 * add the new information when finished
	 */
	@Override
	public void actionPerformed(ActionEvent anEvent) {
		
		if (anEvent.getSource() == myAddButton) {
				List<String> publisherName = new ArrayList<String>();
				try {
					publisherName = myDatabase.getPublisherName();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if(!publisherName.contains(myPublisherText.getText().toLowerCase())) {
					JOptionPane.showMessageDialog(null, "the publisher do not extist!", "error",JOptionPane.ERROR_MESSAGE);
				} else {
				
					BookInfo newBookInfo = new BookInfo(myISBNText.getText(), myTitleText.getText(),
							Integer.valueOf(myYearText.getText()), myAuthorText.getText(),
							myFormatText.getText(), Integer.valueOf(myPageNumberText.getText()),
							myLanguageText.getText(), Integer.valueOf(myBookShelfNumberText.getText()),
							Integer.valueOf(myLayerNumberText.getText()), myPublisherText.getText());
					
					
					
					myDatabase.addBookInfo(newBookInfo);
					//JOptionPane.showMessageDialog(null, "Added Successfully!");
					myFrame.showBookInfoListPanel();
				}
		}
		
	}



	
}
