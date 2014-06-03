import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel{
	
	private JButton myBookList;
	private JButton myPatronList;
	private JButton myBookSearch;
	private JButton myInformation;
	
	public ButtonPanel() {
		super();
		setLayout(new FlowLayout());
		configButton();
	}
	
	private void configButton(){
		myBookList = new JButton("Book List");
		myPatronList = new JButton("Patron List");
		myBookSearch = new JButton("Book Search");
		myInformation = new JButton("Information");
		
		add(myBookList);
		add(myPatronList);
		add(myBookSearch);
		add(myInformation);
	}
}
