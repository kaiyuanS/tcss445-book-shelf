import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class PublisherListPanel extends JPanel implements ActionListener{
	
	BookShelfDB myDatebase;
	
	List<Publisher> myPublisherList;
	private Object[][] myPublisherData;
	String[] myPublisherColName = {"publisherName", "publisherStreet", "publisherCity", "publisherState",
			"publisherZip", "publisherCountry", "publisherFounded"};
	JTable myPublisherTable;
	
	JButton myAdd;
	
	public PublisherListPanel(BookShelfDB aDatabase) {
		super();
		myDatebase = aDatabase;
		setLayout(new BorderLayout());
		configList();
		addComponents();
	}

	private void configList() {
		myPublisherTable = new JTable(myPublisherData, myPublisherColName);
		refreshData();
	}
	
	private void refreshData() {
		try {
			myPublisherList = myDatebase.getPublishers();
			refreshTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addComponents() {
		
	}
	
	private void refreshTable() {
		myPublisherData = new Object[myPublisherList.size()][myPublisherColName.length];
		for (int i = 0; i < myPublisherList.size(); i++) {
			myPublisherData[i][0] = myPublisherList.get(i).getName();
			myPublisherData[i][1] = myPublisherList.get(i).getStreet();
			myPublisherData[i][2] = myPublisherList.get(i).getCity();
			myPublisherData[i][3] = myPublisherList.get(i).getState();
			myPublisherData[i][4] = myPublisherList.get(i).getZipCode();
			myPublisherData[i][5] = myPublisherList.get(i).getCountry();
			myPublisherData[i][6] = myPublisherList.get(i).getYearFounded();
		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	
	
}
