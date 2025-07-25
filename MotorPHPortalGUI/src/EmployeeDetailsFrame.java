/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Winter Melon
 */
import java.util.Map;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class EmployeeDetailsFrame extends javax.swing.JFrame {

    private String employeeId;
    
    // Method to call for Showing Full Employee Details - Moved to Helper -- will just call method for organization
    private JPanel loadEmployeeDetails(String employeeId) {
    return new EmployeeInfoPanel(employeeId);
}

    // Stores attendance records for viewing and salary computation -- so no need to load again from csv
    private List<String[]> currentAttendanceRecords; 
    
    /**
     * Creates new form EmployeeDetailsFrame
     * Panels + Details + Dropdown
     * @param employeeId the ID of employee to display xD
     **/
    public EmployeeDetailsFrame(String employeeId) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        this.employeeId = employeeId;
        
        loadEmployeeDetails(employeeId);

        panelDetails = new JPanel(new java.awt.BorderLayout());
        panelAttendance = new JPanel(new java.awt.BorderLayout());
        panelSalary = new JPanel(new java.awt.BorderLayout());

        // Add components to each panel
        panelAttendance.add(new JScrollPane(tblAttendance), java.awt.BorderLayout.CENTER);
        panelSalary.add(new JLabel("Click 'Compute Salary' to generate details."), BorderLayout.CENTER);

        // Add panels to CardLayout container
        contentPanel.add(loadEmployeeDetails(employeeId), "Details");
        contentPanel.add(panelAttendance, "Attendance");
        contentPanel.add(panelSalary, "Salary");

        // Show default
        ((java.awt.CardLayout) contentPanel.getLayout()).show(contentPanel, "Details");
        
        
        // Disables [Month] and [Year] to be selected
        drpMonth.setSelectedIndex(0);
        drpMonth.setEnabled(true);
        drpYear.setSelectedIndex(0);
        drpYear.setEnabled(true);
        }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAttendance = new javax.swing.JButton();
        btnSalary = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        drpMonth = new javax.swing.JComboBox<>();
        drpYear = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        panelDetails = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelAttendance = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAttendance = new javax.swing.JTable();
        panelSalary = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAttendance.setText("View Attendance");
        btnAttendance.setMaximumSize(new java.awt.Dimension(140, 30));
        btnAttendance.setMinimumSize(new java.awt.Dimension(140, 30));
        btnAttendance.setPreferredSize(new java.awt.Dimension(140, 30));
        btnAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttendanceActionPerformed(evt);
            }
        });
        jPanel1.add(btnAttendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 19, 166, -1));

        btnSalary.setText("Compute Salary");
        btnSalary.setMaximumSize(new java.awt.Dimension(140, 30));
        btnSalary.setMinimumSize(new java.awt.Dimension(140, 30));
        btnSalary.setPreferredSize(new java.awt.Dimension(140, 30));
        btnSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalaryActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 19, -1, -1));

        btnDetails.setText("Employee Details");
        btnDetails.setMaximumSize(new java.awt.Dimension(140, 30));
        btnDetails.setMinimumSize(new java.awt.Dimension(140, 30));
        btnDetails.setPreferredSize(new java.awt.Dimension(140, 30));
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });
        jPanel1.add(btnDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 19, -1, -1));

        drpMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Month]", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        drpMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drpMonthActionPerformed(evt);
            }
        });
        jPanel1.add(drpMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 61, 166, -1));
        drpMonth.getAccessibleContext().setAccessibleName("Month");

        drpYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Year]", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        jPanel1.add(drpYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 61, 140, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/login-bg.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 500, 100));

        contentPanel.setLayout(new java.awt.CardLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(300, 700));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 856, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelDetailsLayout = new javax.swing.GroupLayout(panelDetails);
        panelDetails.setLayout(panelDetailsLayout);
        panelDetailsLayout.setHorizontalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
            .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetailsLayout.createSequentialGroup()
                    .addContainerGap(50, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(50, Short.MAX_VALUE)))
        );
        panelDetailsLayout.setVerticalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
            .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetailsLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        contentPanel.add(panelDetails, "card4");

        tblAttendance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblAttendance);

        javax.swing.GroupLayout panelAttendanceLayout = new javax.swing.GroupLayout(panelAttendance);
        panelAttendance.setLayout(panelAttendanceLayout);
        panelAttendanceLayout.setHorizontalGroup(
            panelAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAttendanceLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelAttendanceLayout.setVerticalGroup(
            panelAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAttendanceLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        contentPanel.add(panelAttendance, "card4");

        javax.swing.GroupLayout panelSalaryLayout = new javax.swing.GroupLayout(panelSalary);
        panelSalary.setLayout(panelSalaryLayout);
        panelSalaryLayout.setHorizontalGroup(
            panelSalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );
        panelSalaryLayout.setVerticalGroup(
            panelSalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );

        contentPanel.add(panelSalary, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttendanceActionPerformed
    // Uses combo box selection to filter out attendance
    String selectedMonth = drpMonth.getSelectedItem().toString();
    String selectedYear = drpYear.getSelectedItem().toString();
    
    // Error dialog if index 0 or label is selected
    if (selectedMonth.equals("[Month]") || selectedYear.equals("[Year]")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Please select a valid month and year.");
        return;
    }
        
    // Convert month name to number format
    String[] months = { "", "January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December" };
    int monthIndex = java.util.Arrays.asList(months).indexOf(selectedMonth);
    String monthNumber = String.format("%02d", monthIndex);

    // Get attendance records saved
    currentAttendanceRecords = EmployeeAttendanceHelper.getAttendanceRecords(employeeId, monthNumber, selectedYear);

    // Set to table
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[] {
        "Date", "Time In", "Time Out", "Hours Worked", "Late", "Overtime"
    });

    for (String[] row : currentAttendanceRecords) {
        model.addRow(row);
    }

    tblAttendance.setModel(model);

    // Show Attendance Panel
    ((java.awt.CardLayout) contentPanel.getLayout()).show(contentPanel, "Attendance");
    }//GEN-LAST:event_btnAttendanceActionPerformed

    private void btnSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalaryActionPerformed
    // Load attendance record first
    if (currentAttendanceRecords == null || currentAttendanceRecords.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Please view attendance first before computing salary.");
        return;
    }

    // Uses combo box selection to filter out attendance
    String selectedMonth = drpMonth.getSelectedItem().toString();
    String selectedYear = drpYear.getSelectedItem().toString();
    
    // Error dialog if index 0 or label is selected
    if (selectedMonth.equals("[Month]") || selectedYear.equals("[Year]")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Please select a valid month and year.");
        return;
    }

    int monthIndex = drpMonth.getSelectedIndex();
    String formattedMonth = String.format("%02d", monthIndex);
    
    Map<String, String> result = SalaryComputationHelper.computeMonthlySalary(employeeId, formattedMonth, selectedYear, currentAttendanceRecords);

    SalaryDetailsPanel panel = new SalaryDetailsPanel(result);

    // Replace old scroll panel with new panel
    panelSalary.removeAll();
    panelSalary.add(panel, BorderLayout.CENTER);
    panelSalary.revalidate();
    panelSalary.repaint();
    
    // Shw Salary Panel
    ((java.awt.CardLayout) contentPanel.getLayout()).show(contentPanel, "Salary");
    }//GEN-LAST:event_btnSalaryActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        ((java.awt.CardLayout) contentPanel.getLayout()).show(contentPanel, "Details");
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void drpMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drpMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_drpMonthActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttendance;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnSalary;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JComboBox<String> drpMonth;
    private javax.swing.JComboBox<String> drpYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelAttendance;
    private javax.swing.JPanel panelDetails;
    private javax.swing.JPanel panelSalary;
    private javax.swing.JTable tblAttendance;
    // End of variables declaration//GEN-END:variables
}
