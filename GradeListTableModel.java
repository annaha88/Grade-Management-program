package grademanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GradeListTableModel extends AbstractTableModel{

		private CachedRowSet gradeListRowSet; 	// contains data from database
	    private ResultSetMetaData metadata;     // additional information about the data
	    private Connection connection;
	    private int numcols, numrows;           // number of rows and columns
	    private HashSet<String> coreCourse;		// core course list for certificate
	    
	    public GradeListTableModel()  {
	         try {
	            connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/test1", "root", "1234");
	        
	         } catch(SQLException err) {
	            System.out.println(err.getMessage());
	            err.printStackTrace();
	         }
	         
	        System.out.println("Connected successfully");
	    	   try {
	    		        connection.setAutoCommit(false);
	    	        	//courseListRowSet = new CachedRowSetImpl();
	    	        	gradeListRowSet = RowSetProvider.newFactory().createCachedRowSet();
	    	        	gradeListRowSet.setCommand("SELECT * FROM coursegrade");
	    	        	gradeListRowSet.execute(connection);
	    	           
	    	            metadata = gradeListRowSet.getMetaData();
	    	            numcols = metadata.getColumnCount();
	    	            numrows = gradeListRowSet.size(); 
	    	            gradeListRowSet.first();
	    	        }  catch(SQLException exp) { 
	 	        	   exp.printStackTrace();        	  
	 	        }	    	  
	    	   setCoreCourse();		// set core courses list for certificates
	    }
	    
	    public GradeListTableModel(RowSet rowset, Connection conn)  {
	    	try {
	    		gradeListRowSet = (CachedRowSet) rowset;
	    	   metadata = gradeListRowSet.getMetaData();
		       numcols = metadata.getColumnCount();
		       numrows = gradeListRowSet.size();
		       connection = conn;
		       
	    	} catch(SQLException exp) {
	    		exp.printStackTrace();
	    	}
	    }

	    public Connection getConnection() {
	    	return connection;
	    }
	    
	    public int getRowCount() {
			return numrows;
		}
		
		public int getColumnCount() {
			return numcols;
		}
		
		public Object getValueAt(int row, int col) {
			 try {
	               gradeListRowSet.absolute(row + 1);
	               Object obj = gradeListRowSet.getObject(col + 1);
	               if (obj == null)
	                  return null;
	               else
	                  return obj.toString();
	     } catch (SQLException err) {
	             return err.toString();
	     }
		}
		
		public boolean isCellEditable(int row, int col) {
			return false;
		}
		
		public String getColumnName(int col) {
		   try {
				return metadata.getColumnLabel(col+1);
			} catch (SQLException err) {
	             return err.toString();
	        }             
		}
		
		public void setValueAt(Object aValue, int row, int col) {
			try {
				gradeListRowSet.moveToInsertRow();
				System.out.println(aValue+"setvalueAt "+(row+1)+" " +(col+1));
				gradeListRowSet.updateObject(col+1, (String) aValue);	
			} catch(SQLException err) {
				err.getMessage();
				err.printStackTrace();
			}
			
		}
		
		public RowSet getRowSet() {
			return (RowSet) gradeListRowSet;
		}
		
		
		public void setCoreCourse() {
			// add core course numbers into a hashset
			 coreCourse = new HashSet<String>();
			 coreCourse.add("CO100");
			 coreCourse.add("CO200");
		}
		
		
		public void addRow(Object[] array) {
			
		  try {
			   gradeListRowSet.last(); // set the current row to rowIndex
			   gradeListRowSet.moveToInsertRow(); // move the cursor to insert row
			   gradeListRowSet.updateString("course_name", (String) array[0]);
			   gradeListRowSet.updateString("course_number", (String) array[1]);
			   gradeListRowSet.updateString("grade",  (String) array[2]);
			   gradeListRowSet.updateString("start_date", (String) array[3]);
			   gradeListRowSet.updateString("end_date", (String) array[4]);
	        
			   gradeListRowSet.insertRow();
			   gradeListRowSet.moveToCurrentRow();
			   gradeListRowSet.acceptChanges(connection);	// propagates all the changes to database
				 
			} catch(SQLException err) {
				err.getMessage();
				err.printStackTrace();
			}
		}
		
		// update the selected row with new information in the text fields of the window
		public void updateRow(int index, Object[] array) {
			if (index != -1) {
				try {
				   gradeListRowSet.updateString("course_name", (String) array[0]);
				   gradeListRowSet.updateString("course_number", (String) array[1]);
				   gradeListRowSet.updateString("grade",  (String) array[2]);
				   gradeListRowSet.updateString("start_date", (String) array[3]);
				   gradeListRowSet.updateString("end_date", (String) array[4]);
		        
				   gradeListRowSet.updateRow();
				   gradeListRowSet.acceptChanges(connection);	 
				} catch(SQLException err) {
					err.getMessage();
					err.printStackTrace();
				}
			}
		}

		// delete selected row in jtable from database
		public void deleteRow(int index) {
			if (index != -1) { // delete a selected row from gradeListRowSet   
				try {
					gradeListRowSet.deleteRow();
					gradeListRowSet.acceptChanges(connection);
				} catch (SQLException e) {
					System.out.println("Fail to delete");
					e.printStackTrace();
				}
			}
		}
		
	
		
		// check whether the user is eligible for a certificate based on the database
		// 1. check core courses and the grade
		// 2. check the total number of course (need to be >=5 with >= B grade)
		public boolean checkEligibility()  {
			 try {
				// check core courses and the grade
				 gradeListRowSet.first(); // make sure to move the cursor to the first row
				 HashSet<String> courseNumberSet = new HashSet<String>();
				// add course numbers from database into a hashset.
				 for (int i=0;i<numrows;i++) {
					 if ("AB".contains(gradeListRowSet.getString("grade")))	// only A,B grade counts
						 courseNumberSet.add(gradeListRowSet.getString("course_number"));
					 gradeListRowSet.next();
				 }
				 
				 this.setCoreCourse(); // set core course list
				 int coreSize = coreCourse.size();
				 coreCourse.retainAll(courseNumberSet);	// get the intersection				
				 // examine whether all core courses are taken by checking the size 
				 if (coreCourse.size() != coreSize) {
					 return false;
				 }
				 
				// check the total number of course if it is at least 5.
				 if (courseNumberSet.size() < 5) {
					 return false;
				 }
				     
			} catch (SQLException e) {
				System.out.println("A SQLException occurred.");
				e.printStackTrace();
			}
			// return true if it pass all conditions
			return true;
		}
		
	}