/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Winter Melon
 */

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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

    private static boolean isFirstHalf(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date date = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            return day <= 15;
        } catch (Exception e) {
            return false;
        }
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

        // Half-month breakdown
        double totalHoursFirstHalf = 0, totalLatesFirstHalf = 0, totalOTFirstHalf = 0;
        double totalHoursSecondHalf = 0, totalLatesSecondHalf = 0, totalOTSecondHalf = 0;

        for (String[] row : attendanceRecords) {
            boolean isFirst = isFirstHalf(row[1]);
            double hours = convertToDecimal(row[3]);
            double late = convertToDecimal(row[4]);
            double ot = convertToDecimal(row[5]);
            if (isFirst) {
                totalHoursFirstHalf += hours;
                totalLatesFirstHalf += late;
                totalOTFirstHalf += ot;
            } else {
                totalHoursSecondHalf += hours;
                totalLatesSecondHalf += late;
                totalOTSecondHalf += ot;
            }
        }

        // Allowances and subsidies split evenly
        double halfBasic = basicSalary / 2.0;
        double halfRice = riceSubsidy / 2.0;
        double halfPhone = phoneAllowance / 2.0;
        double halfClothing = clothingAllowance / 2.0;
        double halfWithholdingTax = MandatoryDeductionsHelper.computeWithholdingTax(basicSalary, year) / 2.0;

        // First half computation
        double sss = MandatoryDeductionsHelper.computeSSS(basicSalary, year);
        double lateDeduction1 = totalLatesFirstHalf * hourlyRate;
        double overtimePay1 = totalOTFirstHalf * hourlyRate * 1.25;
        double gross1 = halfBasic + halfRice + halfPhone + halfClothing + overtimePay1;
        double deduction1 = sss + halfWithholdingTax + lateDeduction1;
        double net1 = gross1 - deduction1;

        // Second half computation
        double philhealth = MandatoryDeductionsHelper.computePhilhealth(basicSalary, year);
        double pagibig = MandatoryDeductionsHelper.computePagibig(basicSalary, year);
        double lateDeduction2 = totalLatesSecondHalf * hourlyRate;
        double overtimePay2 = totalOTSecondHalf * hourlyRate * 1.25;
        double gross2 = halfBasic + halfRice + halfPhone + halfClothing + overtimePay2;
        double deduction2 = philhealth + pagibig + halfWithholdingTax + lateDeduction2;
        double net2 = gross2 - deduction2;

        // Total
        double grossPay = gross1 + gross2;
        double totalDeductions = deduction1 + deduction2;
        double netPay = net1 + net2;

        NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.US);
        currencyFormat.setMinimumFractionDigits(2);
        currencyFormat.setMaximumFractionDigits(2);

        salarySummary.put("Employee ID", employeeId);
        salarySummary.put("Name", employee[2] + " " + employee[1]);
        salarySummary.put("Month-Year", month + "-" + year);

        salarySummary.put("--- First Half (1-15) ---", "");
        salarySummary.put("Basic Salary (1-15)", currencyFormat.format(halfBasic));
        salarySummary.put("Allowances (1-15)", currencyFormat.format(halfRice + halfPhone + halfClothing));
        salarySummary.put("Overtime (1-15)", currencyFormat.format(overtimePay1));
        salarySummary.put("Late Deduction (1-15)", currencyFormat.format(lateDeduction1));
        salarySummary.put("SSS", currencyFormat.format(sss));
        salarySummary.put("Withholding Tax (1-15)", currencyFormat.format(halfWithholdingTax));
        salarySummary.put("Net Pay (1-15)", currencyFormat.format(net1));

        salarySummary.put("--- Second Half (16-EOM) ---", "");
        salarySummary.put("Basic Salary (16-EOM)", currencyFormat.format(halfBasic));
        salarySummary.put("Allowances (16-EOM)", currencyFormat.format(halfRice + halfPhone + halfClothing));
        salarySummary.put("Overtime (16-EOM)", currencyFormat.format(overtimePay2));
        salarySummary.put("Late Deduction (16-EOM)", currencyFormat.format(lateDeduction2));
        salarySummary.put("PhilHealth", currencyFormat.format(philhealth));
        salarySummary.put("Pag-IBIG", currencyFormat.format(pagibig));
        salarySummary.put("Withholding Tax (16-EOM)", currencyFormat.format(halfWithholdingTax));
        salarySummary.put("Net Pay (16-EOM)", currencyFormat.format(net2));

        salarySummary.put("--- Monthly Total ---", "");
        salarySummary.put("Gross Pay", currencyFormat.format(grossPay));
        salarySummary.put("Total Deductions", currencyFormat.format(totalDeductions));
        salarySummary.put("Net Pay", currencyFormat.format(netPay));

        return salarySummary;
    }
}