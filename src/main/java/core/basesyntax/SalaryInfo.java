package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder header = new StringBuilder();
        int[] totals = new int[names.length];

        for (String dataLine : data) {

            String[] splitData = dataLine.split(" ");
            if (splitData.length >= 4) {
                String dateToken = splitData[0];
                String nameToken = splitData[1];
                int hoursToken = Integer.parseInt(splitData[2]);
                int rateToken = Integer.parseInt(splitData[3]);
                int daySalary = hoursToken * rateToken;

                LocalDate properDateToken = LocalDate.parse(dateToken, formatter);

                for (int i = 0; i < names.length; i++) {
                    if (!properDateToken.isBefore(fromDate) && !properDateToken.isAfter(toDate)) {
                        if (nameToken.equals(names[i])) {
                            totals[i] += daySalary;
                        }
                    }
                }
            }
        }
        header.append("Report for period ");
        header.append(" ");
        header.append(dateFrom);
        header.append(" - ");
        header.append(dateTo);
        header.append(System.lineSeparator());
        for (int j = 0; j < names.length; j++) {
            header.append(names[j]);
            header.append(" - ");
            header.append(totals[j]);
            header.append(System.lineSeparator());
        }
        return header.toString();
    }
}
