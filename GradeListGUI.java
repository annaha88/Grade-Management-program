package grademanagement;

import java.awt.BorderLayout;
import javax.swing.*;

@SuppressWarnings("serial")
public class GradeListGUI extends javax.swing.JFrame {
	// Variables declaration                   
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton calculateButton;  // calculate eligibility
    
    private JTextField courseNameTextField;
    private JTextField courseNumberTextField;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JTextField gradeTextField;
    
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    
    private JPanel jPanel1;
    private JPanel jPanel2;	//contain JTable
    
    private JTable jtable1; // the table displayed on the GUI
    private GradeListTableController gradeListTableController; // glue between model and gui
    // End of variables declaration
    
    
    /**
     * Creates new form GradeListGUI
     */
    public GradeListGUI() {
    	initComponents();
        gradeListTableController = new GradeListTableController(this);
        addJTable();
    }
    
    // creates a table and adds it to the GUI. 
    // Attaches the controller as a listener to the table
    public void addJTable() {
    	// add the data and column names to a JTable
    	jtable1 = new JTable(gradeListTableController.getTableModel());
		// add a ListSelectionListener to the table
		jtable1.getSelectionModel().addListSelectionListener(gradeListTableController);
		
		// add the table to a scrollpane
		JScrollPane scrollpane = new JScrollPane(jtable1);
		// create a window
		jPanel2.setLayout(new BorderLayout());
		jPanel2.add(scrollpane, BorderLayout.CENTER);
    		

    }
    
    public void updateTable() {
    	jtable1.setModel(gradeListTableController.getTableModel());
    }
    
    // display data on the courseNameTextField
    public void setCourseNameTextField(String value) {
    	courseNameTextField.setText(value);
    }
    
    // display data on the courseNumberTextField
    public void setCourseNumberTextField(String value) {
    	courseNumberTextField.setText(value);
    }
    
    // display data on the enrollmentTextField
    public void setGradeTextField(String value) {
    	gradeTextField.setText(value);
    } 
    
    // display data on the startDateTextField
    public void setStartDateTextField(String value) {
    	startDateTextField.setText(value);
    }
    
    // display data on the endDateTextField
    public void setEndDateTextField(String value) {
    	endDateTextField.setText(value);
    }

    // initialize components in window
    private void initComponents() {

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        courseNameTextField = new JTextField();
        courseNumberTextField = new JTextField();
        gradeTextField = new JTextField();
        startDateTextField = new JTextField();
        endDateTextField = new JTextField();
        addButton = new JButton();
        deleteButton = new JButton();
        updateButton = new JButton();
        
        calculateButton = new JButton();	// calculate eligibility for the certificate based on course and grade in the database

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Course List"));

        jLabel1.setText("Course Name");
        jLabel2.setText("Course Number");
        jLabel3.setText("Grade");
        jLabel4.setText("Start Date");
        jLabel5.setText("End Date");


        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        
        calculateButton.setText("Check Eligibility");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });
        
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        setContentPane(jPanel1);	// set jPanel1 as a content pane
        

        // set layout
        // ** this is reused code from CourseListGUI class and modified for this program.
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61) 
                .addComponent(addButton)
                .addGap(91, 91, 91)
                .addComponent(deleteButton)
                .addGap(91, 91, 91)
                .addComponent(updateButton)
                .addGap(91, 91, 91)
                .addComponent(calculateButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(248, 248, 248)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(startDateTextField)
                        .addComponent(endDateTextField)
                        .addComponent(courseNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gradeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(214, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
         
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(courseNameTextField))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gradeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(deleteButton)
                    .addComponent(updateButton))
                	.addComponent(calculateButton)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(450, 450, 450))
        );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    // code for the Update button
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	//update the current row in the table
    	String[] array = new String[jtable1.getColumnCount()];
    	array[0] = courseNameTextField.getText();
    	array[1] = courseNumberTextField.getText();
    	array[2] = gradeTextField.getText();
    	array[3] = startDateTextField.getText();
    	array[4] = endDateTextField.getText();
    	
    	int selectedRow = jtable1.getSelectedRow();
    	gradeListTableController.updateRow(selectedRow, array);
    }                                            


    // code for the Delete button
     private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	int selectedRow = jtable1.getSelectedRow();
    	gradeListTableController.deleteRow(selectedRow);
    }                                            

    // code for the Add button  ** This code is from @author rgrover
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        //add a row to the table
    	String[] array = new String[jtable1.getColumnCount()];
    	array[0] = courseNameTextField.getText();
    	array[1] = courseNumberTextField.getText();
    	array[2] = gradeTextField.getText();
    	array[3] = startDateTextField.getText();
    	array[4] = endDateTextField.getText();
    	
   	    gradeListTableController.addRow(array);
    }
    
    // code for the calculate button to check eligibility
    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	// message dialog will display the result after check
    	JFrame dWindow = new JFrame();
    	String message = "";
    	boolean result = gradeListTableController.checkEligibility();	// check eligibility with the database
    	if (result)
    		message = "Congrats!\nYou are Eligible for the certificate.";
    	else
    		message = "Sorry!\nYou are Not eligible yet. Keep it up!";
    	JOptionPane.showMessageDialog(dWindow, message, "Eligibility check", JOptionPane.PLAIN_MESSAGE);
    }  

    
    // main
	public static void main(String[] args) {
		//start GUI by making a GUI object
		new GradeListGUI().setVisible(true);
	 }

}


