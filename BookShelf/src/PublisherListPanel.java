import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class PublisherListPanel extends JPanel implements ActionListener, TableModelListener{
	
	BookShelfDB myDatabase;
	LibraryFrame myFrame;
	
	List<Publisher> myPublisherList;
	private JScrollPane myListScrollPane;
	private Object[][] myPublisherData;
	private String[] myColName = {"publisherName", "publisherStreet", "publisherCity", "publisherState",
			"publisherZip", "publisherCountry", "publisherFounded"};
	private JTable myPublisherTable;
	
	JPanel myListPanel;
	private JButton myAdd;
	private JButton myDelete;
	
	public PublisherListPanel(BookShelfDB aDatabase, LibraryFrame aFrame) {
		super();
		myDatabase = aDatabase;
		myFrame = aFrame;
		setLayout(new BorderLayout());
		configList();
		addComponents();
	}

	private void configList() {
		getData();
		myListPanel = new JPanel(new BorderLayout());
		myPublisherTable = new JTable(myPublisherData, myColName);
		myPublisherTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		myPublisherTable.setPreferredSize(new Dimension(765, 450));
		myPublisherTable.getModel().addTableModelListener(this);
		myListScrollPane = new JScrollPane(myPublisherTable);
		
		myListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		myListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		myListPanel.add(myListScrollPane, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel(new FlowLayout());
		myAdd = new JButton("Add");
		myDelete = new JButton("Delete");
		myAdd.addActionListener(this);
		myDelete.addActionListener(this);
		buttons.add(myAdd);
		buttons.add(myDelete);
		myListPanel.add(buttons, BorderLayout.SOUTH);
		
		
	}
	
	private void getData() {
		try {
			myPublisherList = myDatabase.getPublishers();
			myPublisherData = new Object[myPublisherList.size()][myColName.length];
			for (int i = 0; i < myPublisherList.size(); i++) {
				myPublisherData[i][0] = myPublisherList.get(i).getName();
				myPublisherData[i][1] = myPublisherList.get(i).getStreet();
				myPublisherData[i][2] = myPublisherList.get(i).getCity();
				myPublisherData[i][3] = myPublisherList.get(i).getState();
				myPublisherData[i][4] = myPublisherList.get(i).getZipCode();
				myPublisherData[i][5] = myPublisherList.get(i).getCountry();
				myPublisherData[i][6] = myPublisherList.get(i).getYearFounded();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addComponents() {
		add(myListPanel, BorderLayout.CENTER);
	}
	
	private void refreshTable() {
		try {
			myPublisherList = myDatabase.getPublishers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < myPublisherData.length; i++) {
			if (i < myPublisherList.size()) {
				myPublisherData[i][0] = myPublisherList.get(i).getName();
				myPublisherData[i][1] = myPublisherList.get(i).getStreet();
				myPublisherData[i][2] = myPublisherList.get(i).getCity();
				myPublisherData[i][3] = myPublisherList.get(i).getState();
				myPublisherData[i][4] = myPublisherList.get(i).getZipCode();
				myPublisherData[i][5] = myPublisherList.get(i).getCountry();
				myPublisherData[i][6] = myPublisherList.get(i).getYearFounded();
			} else {
				myPublisherData[i][0] = null;
				myPublisherData[i][1] = null;
				myPublisherData[i][2] = null;
				myPublisherData[i][3] = null;
				myPublisherData[i][4] = null;
				myPublisherData[i][5] = null;
				myPublisherData[i][6] = null;
			}
		}
		myPublisherTable.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent anEvent) {
		
		if (anEvent.getSource() == myAdd) {
			myFrame.showPublisherInfoPanel();
		} else {
			int row = myPublisherTable.getSelectedRow();
			if (row != -1) {
				String publisherName = (String)myPublisherData[row][0];
				myDatabase.removePublisher(publisherName);
			}
			refreshTable();
		}
		
	}

	@Override
	public void tableChanged(TableModelEvent anEvent) {
		int row = anEvent.getFirstRow();
        int column = anEvent.getColumn();
        TableModel model = (TableModel)anEvent.getSource();
        String columnName = model.getColumnName(column);
        String publisherName = (String)model.getValueAt(row, 0);
        Object data = model.getValueAt(row, column);

        myDatabase.upDatePublisher(publisherName, columnName, data);
		
	}
	
	
}
