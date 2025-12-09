package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_CHECK = 4;
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder header = new StringBuilder();
        int[] totals = new int[names.length];

        for (String dataLine : data) {

            String[] splitData = dataLine.split(" ");
            if (splitData.length >= DATE_CHECK) {
                String dateToken = splitData[DATE_INDEX];
                String nameToken = splitData[NAME_INDEX];
                int hoursToken = Integer.parseInt(splitData[HOURS_INDEX]);
                int rateToken = Integer.parseInt(splitData[RATE_INDEX]);
                int daySalary = hoursToken * rateToken;

                LocalDate properDateToken = LocalDate.parse(dateToken, formatter);

                if (!properDateToken.isBefore(fromDate) && !properDateToken.isAfter(toDate)) {
                    for (int i = 0; i < names.length; i++) {
                        if (nameToken.equals(names[i])) {
                            totals[i] += daySalary;
                        }
                    }
                }
            }
        }
        header.append("Report for period");
        header.append(" - ");
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
