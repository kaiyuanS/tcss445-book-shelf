import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * the panel used to add a new publisher
 * @author Kaiyuan Shi
 * @version Spr. 2014
 */
@SuppressWarnings("serial")
public class PublisherInfoPanel extends JPanel implements ActionListener{
	
	/** the syatem database */
	private BookShelfDB myDatebase;
	
	/** the main frame */
	private LibraryFrame myFrame;
	
	/** the Label panel */
	private JPanel myLabelPanel;
	
	/** the text panel */
	private JPanel myTextPanel;
	
	/** the butyon panel */
	private JPanel myButtonPanel;
	
	/* the labels */
	private JLabel myNameLabel;
	private JLabel myStreetLabel;
	private JLabel myCityLabel;
	private JLabel myStateLabel;
	private JLabel myZipLabel;
	private JLabel myCountryLabel;
	private JLabel myFoundedLabel;

	/* text fields */
	private JTextField myNameText;
	private JTextField myStreetText;
	private JTextField myCityText;
	private JTextField myStateText;
	private JTextField myZipText;
	private JTextField myCountryText;
	private JTextField myFoundedText;

	/* the add button */
	private JButton myAddButton;
	
	/* default components size */
	private static Dimension LABEL_SIZE = new Dimension(150, 45);
	private static Dimension TEXT_SIZE = new Dimension(500, 45);
	private static Dimension BUTTON_SIZE = new Dimension(150, 25);
	
	/**
	 * the constructor, create the panel
	 * @param aDatabase the system database
	 * @param aFrame he main frame of the application
	 */
	public PublisherInfoPanel(BookShelfDB aDatabase, LibraryFrame aFrame) {
		super();
		myDatebase = aDatabase;
		myFrame = aFrame;
		setLayout(new BorderLayout());

		configLabel();
		configTextField();
		configButton();
		addComponents();
	}
	
	/**
	 * configure all of the labels
	 */
	private void configLabel() {
		myNameLabel = new JLabel("Publisher Name:");
		myNameLabel.setPreferredSize(LABEL_SIZE);
		myStreetLabel = new JLabel("Publisher Street:");
		myStreetLabel.setPreferredSize(LABEL_SIZE);
		myCityLabel = new JLabel("Publisher City:");
		myCityLabel.setPreferredSize(LABEL_SIZE);
		myStateLabel = new JLabel("Publisher State:");
		myStateLabel.setPreferredSize(LABEL_SIZE);
		myZipLabel = new JLabel("Publisher Zip:");
		myZipLabel.setPreferredSize(LABEL_SIZE);
		myCountryLabel = new JLabel("Publisher Country:");
		myCountryLabel.setPreferredSize(LABEL_SIZE);
		myFoundedLabel = new JLabel("Publisher Year Founded:");
		myFoundedLabel.setPreferredSize(LABEL_SIZE);
	}
	
	/**
	 * configure all of the textfields
	 */
	private void configTextField() {
		myNameText = new JTextField();
		myNameText.setPreferredSize(TEXT_SIZE);
		myStreetText = new JTextField();
		myStreetText.setPreferredSize(TEXT_SIZE);
		myCityText = new JTextField();
		myCityText.setPreferredSize(TEXT_SIZE);
		myStateText = new JTextField();
		myStateText.setPreferredSize(TEXT_SIZE);
		myZipText = new JTextField();
		myZipText.setPreferredSize(TEXT_SIZE);
		myCountryText = new JTextField();
		myCountryText.setPreferredSize(TEXT_SIZE);
		myFoundedText = new JTextField();
		myFoundedText.setPreferredSize(TEXT_SIZE);

		myNameText.setEditable(true);
		myStreetText.setEditable(true);
		myCityText.setEditable(true);
		myStateText.setEditable(true);
		myZipText.setEditable(true);
		myCountryText.setEditable(true);
		myFoundedText.setEditable(true);
		
	}
	
	/**
	 * configure the buttons
	 */
	private void configButton() {
		myAddButton = new JButton("Add");
		myAddButton.setPreferredSize(BUTTON_SIZE);
		myAddButton.addActionListener(this);
	}
	
	private void addComponents() {
		myLabelPanel = new JPanel();
		myLabelPanel.setLayout(new GridLayout(7, 1));
		myTextPanel = new JPanel();
		myTextPanel.setLayout(new GridLayout(7, 1));
		myButtonPanel = new JPanel();
		myButtonPanel.setLayout(new FlowLayout());
		
		myLabelPanel.add(myNameLabel);
		myTextPanel.add(myNameText);
		myLabelPanel.add(myStreetLabel);
		myTextPanel.add(myStreetText);
		myLabelPanel.add(myCityLabel);
		myTextPanel.add(myCityText);
		myLabelPanel.add(myStateLabel);
		myTextPanel.add(myStateText);
		myLabelPanel.add(myZipLabel);
		myTextPanel.add(myZipText);
		myLabelPanel.add(myCountryLabel);
		myTextPanel.add(myCountryText);
		myLabelPanel.add(myFoundedLabel);
		myTextPanel.add(myFoundedText);

		
		myButtonPanel.add(myAddButton);
		
		setLayout(new BorderLayout());
		add(myLabelPanel, BorderLayout.WEST);
		add(myTextPanel, BorderLayout.CENTER);
		add(myButtonPanel, BorderLayout.SOUTH);
	}
	
	/** 
	 * add a new publisher into the system after click add button
	 */
	@Override
	public void actionPerformed(ActionEvent anEvent) {
		
		if (anEvent.getSource() == myAddButton) {
			
			Publisher newPublisher = new Publisher(myNameText.getText().toLowerCase(), myStreetText.getText(),
					myCityText.getText(), myStateText.getText(), myZipText.getText(),
					myCountryText.getText(), Integer.valueOf(myFoundedText.getText()));
			
			myDatebase.addPublisher(newPublisher);
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			myFrame.showPublisherListPanel();
		}
		
	}
	
	
}
