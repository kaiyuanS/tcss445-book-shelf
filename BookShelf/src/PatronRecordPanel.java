import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class PatronRecordPanel extends JPanel implements ActionListener {
	
	private static Dimension LABEL_SIZE = new Dimension(150, 45);
	
	private static Dimension TEXT_SIZE = new Dimension(500, 45);
	
	private static Dimension BUTTON_SIZE = new Dimension(150, 25);
	
	private JLabel myRecordIDLabel;
	
	private JLabel myBookIDLabel;
	
	private JLabel myPatronIDLabel;
	
	private JLabel myBorrowByLabel;
	
	private JLabel myReturnByLabel;
	
	private JTextField myRecordIDText;
	
	private JTextField myBookIDText;
	
	private JTextField myPatronIDText;
	
	private JTextField myBorrowByText;
	
	private JTextField myReturnByText;
	
	private LibraryFrame myFrame;
	
	private BookShelfDB myDB;
	
	private JButton myAddButton;
	
	private JButton myBackButton;
	
	private JPanel myLabelPanel;
	
	private JPanel myTextPanel;
	
	private JPanel myButtonPanel;
	
	public PatronRecordPanel(LibraryFrame theFrame, BookShelfDB theDB) {
		super();
		
		myFrame = theFrame;
		
		myDB = theDB;
		
		configLabel();
		
		configTextField();
		
		configButton();
		
		addComponents();
	}

	private void configLabel() {
		myRecordIDLabel = new JLabel("RecordID:");
		myRecordIDLabel.setPreferredSize(LABEL_SIZE);
		myBookIDLabel = new JLabel("BookID:");
		myBookIDLabel.setPreferredSize(LABEL_SIZE);
		myPatronIDLabel = new JLabel("PatronID:");
		myPatronIDLabel.setPreferredSize(LABEL_SIZE);
		myBorrowByLabel = new JLabel("Borrowed On:");
		myBorrowByLabel.setPreferredSize(LABEL_SIZE);
	}

	private void configTextField() {
		myRecordIDText = new JTextField();
		myRecordIDText.setPreferredSize(TEXT_SIZE);
		int recordID = -1;
		
		try {
			recordID = myDB.getNextOrderID();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		myRecordIDText.setText(String.valueOf(recordID));
		myRecordIDText.setEditable(false);
		
		myBookIDText = new JTextField();
		myBookIDText.setPreferredSize(TEXT_SIZE);
		myBookIDText.setEditable(true);
		
		myPatronIDText = new JTextField();
		myPatronIDText.setPreferredSize(TEXT_SIZE);
		myPatronIDText.setEditable(true);
		
		myBorrowByText = new JTextField();
		myBorrowByText.setPreferredSize(TEXT_SIZE);
		myBorrowByText.setEditable(true);
		
	}
	
	private void configButton() {
		myAddButton = new JButton("Add");
		myAddButton.setPreferredSize(BUTTON_SIZE);
		myAddButton.addActionListener(this);
		
		myBackButton = new JButton("Back");
		myBackButton.setPreferredSize(BUTTON_SIZE);
		myBackButton.addActionListener(this);
	}
	
	private void addComponents() {
		myLabelPanel = new JPanel();
		myLabelPanel.setLayout(new GridLayout(10, 1));
		myTextPanel = new JPanel();
		myTextPanel.setLayout(new GridLayout(10, 1));
		myButtonPanel = new JPanel();
		myButtonPanel.setLayout(new FlowLayout());
		
		myLabelPanel.add(myRecordIDLabel);
		myLabelPanel.add(myPatronIDLabel);
		myLabelPanel.add(myBookIDLabel);
		myLabelPanel.add(myBorrowByLabel);
		
		myTextPanel.add(myRecordIDText);
		myTextPanel.add(myPatronIDText);
		myTextPanel.add(myBookIDText);
		myTextPanel.add(myBorrowByText);
		
		myButtonPanel.add(myAddButton);
		//myButtonPanel.add(myBackButton);
		
		setLayout(new BorderLayout());
		add(myLabelPanel, BorderLayout.WEST);
		add(myTextPanel, BorderLayout.CENTER);
		add(myButtonPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent theEvent) {
		if (theEvent.getSource() == myAddButton) {
			Date borrowBy = null, returnBy = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				java.util.Date parsed = format.parse(myBorrowByText.getText());
				date = new Date(parsed.getTime());
				
			} catch (ParseException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			PatronRecord newPatronRecord = new PatronRecord(Integer.parseInt((myRecordIDText.getText())),
					Integer.parseInt(myPatronIDText.getText()), Integer.parseInt(myBookIDText.getText()),
					date, returnBy);
			List<PatronRecord> patronRecords = new ArrayList<PatronRecord>();
			Patron patron = null;
			Book book = null;
			try {
				patron = myDB.getPatronByID(Integer.parseInt(myPatronIDText.getText()));
				book = myDB.getBookByID(Integer.parseInt(myBookIDText.getText()));
				
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			if (book != null && patron != null) {
				myDB.placeOrder(newPatronRecord.getRecordID(), newPatronRecord.getBorrowByDate(), 
						        newPatronRecord.getPatronID(), newPatronRecord.getBookID());
				JOptionPane.showMessageDialog(null, "PatronRecord Successfully added. Book Is Due In 30 Days");
			} else {
				System.out.println("Book and or Patron Not Found in Database");
				if (book == null && patron == null) {
					JOptionPane.showMessageDialog(null, "Book and Patron Were Not Found In Database");
				}
				else if (book == null) {
					JOptionPane.showMessageDialog(null, "Book Was Not Found In Database");
				} else {
					JOptionPane.showMessageDialog(null, "Patron Was Not Found In Database");
				}
			}
		} else if (theEvent.getSource() == myBackButton) {
			myFrame.showPatronRecordListPanel(null);
		}
	}
}
