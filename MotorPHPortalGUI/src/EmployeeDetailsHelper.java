import java.util.*;

public class EmployeeDetailsHelper {

    public static Map<String, String> getEmployeeDetailsMap(String employeeId) {
        List<String[]> employees = CSVHelper.loadEmployeeData();
        Map<String, String> details = new LinkedHashMap<>(); // maintains order

        for (String[] employee : employees) {
            if (employee[0].equals(employeeId)) {
                details.put("Employee ID", employee[0]);
                details.put("Full Name", employee[2] + " " + employee[1]);
                details.put("Birthday", employee[3]);

                // Contact Details
                details.put("Address", employee[4]);
                details.put("Phone", employee[5]);
                details.put("SSS #", employee[6]);
                details.put("PhilHealth #", employee[7]);
                details.put("TIN #", employee[8]);
                details.put("Pag-IBIG #", employee[9]);

                // Employment Info
                details.put("Status", employee[10]);
                details.put("Position", employee[11]);
                details.put("Supervisor", employee[12]);

                // Salary Info
                details.put("Basic Salary", "PHP " + formatAmount(employee[13]));
                details.put("Rice Subsidy", "PHP " + formatAmount(employee[14]));
                details.put("Phone Allowance", "PHP " + formatAmount(employee[15]));
                details.put("Clothing Allowance", "PHP " + formatAmount(employee[16]));
                details.put("Gross Rate", "PHP " + formatAmount(employee[17]));
                details.put("Hourly Rate", "PHP " + formatAmount(employee[18]));

                return details;
            }
        }

        return Collections.singletonMap("Error", "Employee details not found.");
    }

    private static String formatAmount(String value) {
        try {
            double amount = Double.parseDouble(value);
            return String.format("%,.2f", amount); // adds commas and 2 decimals
        } catch (NumberFormatException e) {
            return value;
        }
    }
}