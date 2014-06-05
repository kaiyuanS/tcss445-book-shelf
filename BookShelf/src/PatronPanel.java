import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import java.sql.SQLException;

@SuppressWarnings("serial")
/**
 * The PatronPanel panel holds the labels and textfields necessary for a user
 * to create a new Patron.
 * 
 * @author Kevin Alexander
 * @version June 4, 2014
 *
 */
public class PatronPanel extends JPanel implements ActionListener { 

	private static Dimension LABEL_SIZE = new Dimension(150, 45);
	
	private static Dimension TEXT_SIZE = new Dimension(500, 45);
	
	private static Dimension BUTTON_SIZE = new Dimension(150, 25);
	
	private JLabel myPatronIDLabel;
	
	private JLabel myFirstNameLabel;
	
	private JLabel myLastNameLabel;
	
	private JLabel myEmailLabel;
	
	private JLabel myPhoneNumberLabel;
	
	private JLabel myStreetLabel;
	
	private JLabel myCityLabel;
	
	private JLabel myStateLabel;
	
	private JLabel myZipLabel;
	
	private JTextField myPatronIDText;
	
	private JTextField myFirstNameText;
	
	private JTextField myLastNameText;
	
	private JTextField myEmailText;
	
	private JTextField myPhoneNumberText;
	
	private JTextField myStreetText;
	
	private JTextField myCityText;
	
	private JTextField myStateText;
	
	private JTextField myZipText;
	
	private LibraryFrame myFrame;
	
	private BookShelfDB myDB;
	
	private JButton myAddButton;
	
	private JPanel myLabelPanel;
	
	private JPanel myTextPanel;
	
	private JPanel myButtonPanel;
	
	/**
	 * The PatronPanel constructor takes in a LibraryFrame and BookShelfDB as parameters and
	 * assigns myFrame and myDB to them accordingly. It then calls the necessary methods to 
	 * initialize the labels, textfields, and button.
	 * 
	 * @param theFrame The LibraryFrame parameter.
	 * @param theDB The BookShelfDB parameter.
	 */
	public PatronPanel (LibraryFrame theFrame, BookShelfDB theDB) {
		super();
		
		myFrame = theFrame;
		
		myDB = theDB;
		
		configLabel();
		
		configTextField();
		
		configButton();
		
		addComponents();
	}

	/**
	 * Configures the labels.
	 */
	private void configLabel() {
		myPatronIDLabel = new JLabel("PatronID:");
		myPatronIDLabel.setPreferredSize(LABEL_SIZE);
		myFirstNameLabel = new JLabel("First Name:");
		myFirstNameLabel.setPreferredSize(LABEL_SIZE);
		myLastNameLabel = new JLabel("Last Name:");
		myLastNameLabel.setPreferredSize(LABEL_SIZE);
		myEmailLabel = new JLabel("Email:");
		myEmailLabel.setPreferredSize(LABEL_SIZE);
		myPhoneNumberLabel = new JLabel("Phone Number:");
		myPhoneNumberLabel.setPreferredSize(LABEL_SIZE);
		myStreetLabel = new JLabel("Street:");
		myCityLabel = new JLabel("City:");
		myStateLabel = new JLabel("State:");
		myZipLabel = new JLabel("ZipCode:");
	}

	/**
	 * Configures the textfields.
	 */
	private void configTextField() {
		myPatronIDText = new JTextField();
		myPatronIDText.setPreferredSize(TEXT_SIZE);
		
		int patronID = -1;
		try {
			patronID = myDB.getNextPatronID();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		myPatronIDText.setText(String.valueOf(String.valueOf(patronID)));
		myPatronIDText.setEditable(false);
		
		myFirstNameText = new JTextField();
		myFirstNameText.setPreferredSize(TEXT_SIZE);
		myFirstNameText.setEditable(true);
		
		myLastNameText = new JTextField();
		myLastNameText.setPreferredSize(TEXT_SIZE);
		myLastNameText.setEditable(true);
		
		myEmailText = new JTextField();
		myEmailText.setPreferredSize(TEXT_SIZE);
		myEmailText.setEditable(true);
		
		myPhoneNumberText = new JTextField();
		myPhoneNumberText.setPreferredSize(TEXT_SIZE);
		myPhoneNumberText.setEditable(true);
		
		myStreetText = new JTextField();
		myStreetText.setPreferredSize(TEXT_SIZE);
		myStreetText.setEditable(true);
		
		myCityText = new JTextField();
		myCityText.setPreferredSize(TEXT_SIZE);
		myCityText.setEditable(true);
		
		myStateText = new JTextField();
		myStateText.setPreferredSize(TEXT_SIZE);
		myStateText.setEditable(true);
		
		myZipText = new JTextField();
		myZipText.setPreferredSize(TEXT_SIZE);
		myZipText.setEditable(true);	
	}
	
	/**
	 * Configures the button.
	 */
	private void configButton() {
		myAddButton = new JButton("Add");
		myAddButton.setPreferredSize(BUTTON_SIZE);
		myAddButton.addActionListener(this);
		/*
		myBackButton = new JButton("Back");
		myBackButton.setPreferredSize(BUTTON_SIZE);
		myBackButton.addActionListener(this);
		*/
	}
	
	/**
	 * Adds all the labels, textfields, and button to the PatronPanel.
	 */
	private void addComponents() {
		myLabelPanel = new JPanel();
		myLabelPanel.setLayout(new GridLayout(10, 1));
		myTextPanel = new JPanel();
		myTextPanel.setLayout(new GridLayout(10, 1));
		myButtonPanel = new JPanel();
		myButtonPanel.setLayout(new FlowLayout());
		
		myTextPanel.add(myPatronIDText);
		myTextPanel.add(myFirstNameText);
		myTextPanel.add(myLastNameText);
		myTextPanel.add(myEmailText);
		myTextPanel.add(myPhoneNumberText);
		myTextPanel.add(myStreetText);
		myTextPanel.add(myCityText);
		myTextPanel.add(myStateText);
		myTextPanel.add(myZipText);
		
		myLabelPanel.add(myPatronIDLabel);
		myLabelPanel.add(myFirstNameLabel);
		myLabelPanel.add(myLastNameLabel);
		myLabelPanel.add(myEmailLabel);
		myLabelPanel.add(myPhoneNumberLabel);
		myLabelPanel.add(myStreetLabel);
		myLabelPanel.add(myCityLabel);
		myLabelPanel.add(myStateLabel);
		myLabelPanel.add(myZipLabel);
		
		myButtonPanel.add(myAddButton);
		//myButtonPanel.add(myBackButton);
		
		setLayout(new BorderLayout());
		add(myLabelPanel, BorderLayout.WEST);
		add(myTextPanel, BorderLayout.CENTER);
		add(myButtonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Called when the button is clicked.
	 */
	public void actionPerformed(ActionEvent theEvent) {
		if (theEvent.getSource() == myAddButton) {
			Patron newPatron = new Patron(Integer.parseInt(myPatronIDText.getText()), myFirstNameText.getText(),
					       myLastNameText.getText(), myEmailText.getText(), myPhoneNumberText.getText(),
					       myStreetText.getText(), myCityText.getText(), myStateText.getText(),
					       myZipText.getText());
			
			myDB.addPatron(newPatron);
			JOptionPane.showMessageDialog(null, "Patron Successfully Added");
			myFrame.showPatronListPanel();
			
		} 
	}

}
