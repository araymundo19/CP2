
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Winter Melon
 */

import java.util.List;

public class EmployeeDetailsHelper {
    
    public static String getEmployeeDetails(String employeeId) {
    List<String[]> employees = CSVHelper.loadEmployeeData();

        for (String[] employee : employees) {
            if (employee[0].equals(employeeId)) {
                String details =
                    "Employee ID   : " + employee[0] + "\n" +
                    "Full Name     : " + employee[2] + " " + employee[1] + "\n" +
                    "Birthday      : " + employee[3] + "\n" +
                    "\nMore Details:\n" +
                    "Address       : " + employee[4] + "\n" +
                    "Phone         : " + employee[5] + "\n" +
                    "SSS #         : " + employee[6] + "\n" +
                    "PhilHealth #  : " + employee[7] + "\n" +
                    "TIN #         : " + employee[8] + "\n" +
                    "Pag-ibig #    : " + employee[9] + "\n" +
                    "\nEmployment Details:\n" +
                    "Status        : " + employee[10] + "\n" +
                    "Position      : " + employee[11] + "\n" +
                    "Supervisor    : " + employee[12] + "\n" +
                    "\nSalary Information:\n" +
                    "Basic Salary  : PHP " + employee[13] + "\n" +
                    "Rice Subsidy  : PHP " + employee[14] + "\n" +
                    "Phone Allow.  : PHP " + employee[15] + "\n" +
                    "Clothing All. : PHP " + employee[16] + "\n" +
                    "Gross Rate    : PHP " + employee[17] + "\n" +
                    "Hourly Rate   : PHP " + employee[18];
                
                return details;
            }
        }
        
        return "Employee details not found.";
    }
}
