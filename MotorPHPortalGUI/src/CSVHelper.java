/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Winter Melon
 */

import org.apache.commons.csv.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVHelper {

    // Local writable paths /data/ folder
    private static final String EMPLOYEE_FILE = "data/MotorPH-Employee-Data-Details.csv";
    private static final String ATTENDANCE_FILE = "data/MotorPH-Employee-Data-AttendanceRecord.csv";
    private static final String ACCOUNTS_FILE = "data/MotorPH-Employee-Data-Accounts.csv";

    // Fallback /resource/ paths
    private static final String EMPLOYEE_RESOURCE = "MotorPH-Employee-Data-Details.csv";
    private static final String ATTENDANCE_RESOURCE = "MotorPH-Employee-Data-AttendanceRecord.csv";
    private static final String ACCOUNTS_RESOURCE = "MotorPH-Employee-Data-Accounts.csv";

    // Reads from /data/ or /resources/ fallback
    private static List<String[]> loadCSV(String localPath, String resourceName) {
        List<String[]> data = new ArrayList<>();
        Reader reader = null;

        try {
            File localFile = new File(localPath);
            if (localFile.exists()) {
                reader = new FileReader(localFile);
            } else {
                InputStream is = CSVHelper.class.getResourceAsStream("/resources/" + resourceName);
                reader = new InputStreamReader(is, StandardCharsets.UTF_8);
            }

            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get());
            for (CSVRecord record : parser) {
                String[] row = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    row[i] = record.get(i);
                }
                data.add(row);
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV: " + resourceName);
            e.printStackTrace();
        }

        return data;
    }

    // Saves ONLY to local file
    private static void writeCSV(String filePath, List<String[]> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            // HEADER!!! very very important
            // Write will delete first row if this is removed xD
            printer.printRecord("Employee #", "Last Name", "First Name", "Birthday", "Address",
                                "Phone Number", "SSS #", "Philhealth #", "TIN #", "Pag-ibig #",
                                "Status", "Position", "Immediate Supervisor", "Basic Salary",
                                "Rice Subsidy", "Phone Allowance", "Clothing Allowance",
                                "Gross Semi-monthly Rate", "Hourly Rate");
            // Write data rows
            for (String[] row : data) {
                printer.printRecord((Object[]) row);
            }

        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + filePath);
            e.printStackTrace();
        }
    }
    
    // CSV Logic
    // Load priority /data/ - /resource/
    // Writes in /data/ only and not in /resource/
    
    // Employee Data CSV
    public static List<String[]> loadEmployeeData() {
        return loadCSV(EMPLOYEE_FILE, EMPLOYEE_RESOURCE);
    }

    public static void saveEmployeeData(List<String[]> data) {
        writeCSV(EMPLOYEE_FILE, data);
    }

    // Attendance Record CSV
    public static List<String[]> loadAttendanceData() {
        return loadCSV(ATTENDANCE_FILE, ATTENDANCE_RESOURCE);
    }

    public static void saveAttendanceData(List<String[]> data) {
        writeCSV(ATTENDANCE_FILE, data);
    }

    // Login Accounts CSV
    public static List<String[]> loadAccountData() {
        return loadCSV(ACCOUNTS_FILE, ACCOUNTS_RESOURCE);
    }

    public static void saveAccountData(List<String[]> data) {
        writeCSV(ACCOUNTS_FILE, data);
    }
}
