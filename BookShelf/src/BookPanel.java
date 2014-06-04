import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class BookPanel extends JPanel implements ActionListener {
	
	private static Dimension LABEL_SIZE = new Dimension(150, 45);
	
	private static Dimension TEXT_SIZE = new Dimension(500, 45);
	
	private static Dimension BUTTON_SIZE = new Dimension(150, 25);
	
	private JLabel myBookIDLabel;
	
	private JLabel myISBNLabel;
	
	private JTextField myBookIDText;
	
	private JTextField myISBNText;
	
	private LibraryFrame myFrame;
	
	private BookShelfDB myDB;
	
	private JButton myAddButton;
	
	private JButton myBackButton;
	
	private JPanel myLabelPanel;
	
	private JPanel myTextPanel;
	
	private JPanel myButtonPanel;
	
	public BookPanel(LibraryFrame theFrame, BookShelfDB theDB) {
		super();
		
		myFrame = theFrame;
		
		myDB = theDB;
		
		configLabel();
		
		configTextField();
		
		configButton();
		
		addComponents();
	}

	private void configLabel() {
		myBookIDLabel = new JLabel("BookID:");
		myBookIDLabel.setPreferredSize(LABEL_SIZE);
		myISBNLabel = new JLabel("ISBN:");
		myISBNLabel.setPreferredSize(LABEL_SIZE);
	}

	private void configTextField() {
		myBookIDText = new JTextField();
		myBookIDText.setPreferredSize(TEXT_SIZE);
		int bookID = -1;
		try {
			bookID = myDB.getNextBookID();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		myBookIDText.setText(String.valueOf(String.valueOf(bookID)));
		myISBNText = new JTextField();
		myISBNText.setPreferredSize(TEXT_SIZE);
		
		myBookIDText.setEditable(false);
		myISBNText.setEditable(true);
		
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
		
		myLabelPanel.add(myBookIDLabel);
		myTextPanel.add(myBookIDText);
		myLabelPanel.add(myISBNLabel);
		myTextPanel.add(myISBNText);
		
		myButtonPanel.add(myAddButton);
		myButtonPanel.add(myBackButton);
		
		setLayout(new BorderLayout());
		add(myLabelPanel, BorderLayout.WEST);
		add(myTextPanel, BorderLayout.CENTER);
		add(myButtonPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent theEvent) {
		if (theEvent.getSource() == myAddButton) {
			Book newBook = new Book(Integer.parseInt(myBookIDText.getText()), myISBNText.getText());
			List<BookInfo> myBooks = new ArrayList<BookInfo>();
			try {
				myBooks = myDB.getBookInfo();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
			BookInfo tmp = null;
			
			for (BookInfo book : myBooks) {
				if (book.getISBN().equals(newBook.getISBN())) {
					tmp = book;
				}
			}
			
			if (tmp == null) {
				myDB.addBook(newBook);
			} else {
				System.out.println("ISBN Not Found In BookInfo");
			}
		} else if (theEvent.getSource() == myBackButton) {
			myFrame.showBookListPanel();
		}
	}
}
