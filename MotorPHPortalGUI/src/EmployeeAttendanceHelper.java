/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Winter Melon
 */

import java.io.FileReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class EmployeeAttendanceHelper {
    private static final String ATTENDANCE_CSV = "data/MotorPH-Employee-Data-AttendanceRecord.csv";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");

    public static List<String[]> getAttendanceRecords(String employeeId, String month, String year) {
        List<String[]> records = new ArrayList<>();

        try (FileReader reader = new FileReader(ATTENDANCE_CSV)) {
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();

            CSVParser csvParser = new CSVParser(reader, format);

            for (CSVRecord record : csvParser) {
                if (!record.get("Employee #").equals(employeeId)) continue;

                LocalDate date = LocalDate.parse(record.get("Date"), DATE_FORMAT);
                if (!String.valueOf(date.getYear()).equals(year) || !String.format("%02d", date.getMonthValue()).equals(month)) continue;

                String timeInRaw = record.get("Log In");
                String timeOutRaw = record.get("Log Out");

                String timeIn = formatTime(timeInRaw);
                String timeOut = formatTime(timeOutRaw);

                String dateStr = date.format(DATE_FORMAT);
                String hoursWorked = "00:00", late = "00", overtime = "00";

                try {
                    LocalTime inTime = LocalTime.parse(timeIn, TIME_FORMAT);
                    LocalTime outTime = LocalTime.parse(timeOut, TIME_FORMAT);

                    Duration work = Duration.between(inTime, outTime).minusHours(1);
                    Duration lateDur = inTime.isAfter(LocalTime.of(8, 10)) ? Duration.between(LocalTime.of(8, 0), inTime) : Duration.ZERO;
                    Duration otDur = outTime.isAfter(LocalTime.of(17, 0)) ? Duration.between(LocalTime.of(17, 0), outTime) : Duration.ZERO;

                    hoursWorked = formatDuration(work);
                    late = formatDuration(lateDur);
                    overtime = formatDuration(otDur);

                } catch (Exception e) {
                    // ignore malformed time entries
                }

                records.add(new String[] {
                    dateStr, timeIn, timeOut, hoursWorked, late, overtime
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }

    private static String formatTime(String timeStr) {
        try {
            LocalTime time = LocalTime.parse(timeStr, TIME_FORMAT);
            return time.format(DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            return "-";
        }
    }

    private static String formatDuration(Duration d) {
        long hours = d.toMinutes() / 60;
        long minutes = d.toMinutes() % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
