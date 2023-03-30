package grademanagement;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;


public class GradeListTableController implements ListSelectionListener, RowSetListener {
	private GradeListTableModel tableModel;
	private GradeListGUI gui;
	
	public GradeListTableController(GradeListGUI gui) {
		this.gui = gui;
         // create the tableModel using the data in the cachedRowSet
    	tableModel = new GradeListTableModel(); 
    	tableModel.getRowSet().addRowSetListener(this);
	}
	
	public TableModel getTableModel() {
		return tableModel;
	}
	
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();
		
		// read the data in each column using getValueAt and display it on corresponding textfield
		gui.setCourseNameTextField( (String) tableModel.getValueAt(firstIndex, 0));
		gui.setCourseNumberTextField( (String) tableModel.getValueAt(firstIndex, 1));
		gui.setGradeTextField( (String) tableModel.getValueAt(firstIndex, 2));
		gui.setStartDateTextField( (String) tableModel.getValueAt(firstIndex, 3));
		gui.setEndDateTextField( (String) tableModel.getValueAt(firstIndex, 4));
	}
	
	
	public void cursorMoved(RowSetEvent event) {
		
	}
	
	public void rowSetChanged(RowSetEvent event) {
		
	}
	
	public void rowChanged(RowSetEvent event) {
		try {
		    	// get the index of the inserted row
		        tableModel.getRowSet().moveToCurrentRow();
		    	int firstIndex =  tableModel.getRowSet().getRow();
		    	
		    	// create a new table model with the new data
		        tableModel = new GradeListTableModel(tableModel.getRowSet(), tableModel.getConnection());
		        // update the JTable with the data
		    	gui.updateTable();
		    
		        // read the data in each column using getValueAt and display it on corresponding textfield
				gui.setCourseNameTextField( (String) tableModel.getValueAt(firstIndex, 0));
				gui.setCourseNumberTextField( (String) tableModel.getValueAt(firstIndex, 1));
				gui.setGradeTextField( (String) tableModel.getValueAt(firstIndex, 2));
				gui.setStartDateTextField( (String) tableModel.getValueAt(firstIndex, 3));
				gui.setEndDateTextField( (String) tableModel.getValueAt(firstIndex, 4));
		} catch(Exception exp) {
			exp.getMessage();
			exp.printStackTrace();
		}
	}

	public void addRow(String[] array) {
		tableModel.addRow(array);
	}
	
	public void updateRow(int index, String[] array) {
		tableModel.updateRow(index, array);
	}

	public void deleteRow(int row) {
		tableModel.deleteRow(row);
    	// create a new table model with the new data
        tableModel = new GradeListTableModel(tableModel.getRowSet(), tableModel.getConnection());
        // update the JTable with the data
    	gui.updateTable();
	}
	
	// check eligibility based on the current database
	// return true if the user is eligible else return false.
	public boolean checkEligibility() {
		return tableModel.checkEligibility();
	}
}
