/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Winter Melon
 */

import java.text.NumberFormat;
import java.util.Locale;
import java.util.*;

public class SalaryComputationHelper {

    // Method to convert HH:mm time to decimal for math
    private static double convertToDecimal(String time) {
        if (time == null || time.isEmpty()) return 0.0;
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour + (minute / 60.0);
    }
    
    public static Map<String, String> computeMonthlySalary(
        String employeeId,
        String month,
        String year,
        List<String[]> attendanceRecords
    ) {
        Map<String, String> salarySummary = new LinkedHashMap<>();

        // Load employee details
        List<String[]> employees = CSVHelper.loadEmployeeData();
        String[] employee = employees.stream()
            .filter(emp -> emp[0].equals(employeeId))
            .findFirst()
            .orElse(null);

        if (employee == null) {
            salarySummary.put("Error", "Employee not found.");
            return salarySummary;
        }
                
        // Parse salary values to remove commas
        double basicSalary = Double.parseDouble(employee[13].replace(",", ""));
        double riceSubsidy = Double.parseDouble(employee[14].replace(",", ""));
        double phoneAllowance = Double.parseDouble(employee[15].replace(",", ""));
        double clothingAllowance = Double.parseDouble(employee[16].replace(",", ""));
        double hourlyRate = Double.parseDouble(employee[18].replace(",", ""));

        // Use passed-in attendance records
        double totalHoursWorked = 0;
        double totalLateTime = 0;
        double totalOvertimeHours = 0;

        // Parse attendance records for computation
        for (String[] row : attendanceRecords) {
            totalHoursWorked += convertToDecimal(row[3]); // Hours Worked
            totalLateTime += convertToDecimal(row[4]); // Late Time
            totalOvertimeHours += convertToDecimal(row[5]); // Overtime
        }

        // Compute late and OT
        double lateDeduction = (totalLateTime) * hourlyRate;
        double overtimePay = totalOvertimeHours * hourlyRate * 1.25;

        // Mandatory deductions helper
        double sss = MandatoryDeductionsHelper.computeSSS(basicSalary, year);
        double philhealth = MandatoryDeductionsHelper.computePhilhealth(basicSalary, year);
        double pagibig = MandatoryDeductionsHelper.computePagibig(basicSalary, year);
        double withholdingTax = MandatoryDeductionsHelper.computeWithholdingTax(basicSalary, year);

        // Salary Computation
        double grossPay = basicSalary + riceSubsidy + phoneAllowance + clothingAllowance + overtimePay;
        double totalDeductions = sss + philhealth + pagibig + withholdingTax + lateDeduction;
        double netPay = grossPay - totalDeductions;

        // Placeholder format <UNFORMATTED> D:
        NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.US);
        currencyFormat.setMinimumFractionDigits(2);
        currencyFormat.setMaximumFractionDigits(2);
        
        salarySummary.put("Employee ID", employeeId);
        salarySummary.put("Name", employee[2] + " " + employee[1]);
        salarySummary.put("Month-Year", month + "-" + year);
        
        salarySummary.put("Basic Salary", currencyFormat.format(basicSalary));
        salarySummary.put("Rice Subsidy", currencyFormat.format(riceSubsidy));
        salarySummary.put("Phone Allowance", currencyFormat.format(phoneAllowance));
        salarySummary.put("Clothing Allowance", currencyFormat.format(clothingAllowance));
        salarySummary.put("Overtime Pay", currencyFormat.format(overtimePay));
        salarySummary.put("Late Deduction", currencyFormat.format(lateDeduction));
        salarySummary.put("SSS", currencyFormat.format(sss));
        salarySummary.put("PhilHealth", currencyFormat.format(philhealth));
        salarySummary.put("Pag-ibig", currencyFormat.format(pagibig));
        salarySummary.put("Withholding Tax", currencyFormat.format(withholdingTax));

        salarySummary.put("Gross Pay", currencyFormat.format(grossPay));
        salarySummary.put("Total Deductions", currencyFormat.format(totalDeductions));
        salarySummary.put("Net Pay", currencyFormat.format(netPay));

        return salarySummary;
    }
}

