package pl.javastart.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class Main {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        LocalDateTime dateTime = dateTime(scanner);
        printTimeZones(dateTime);
        //ZoneId.getAvailableZoneIds().forEach(System.out::println);
    }

    private void printTimeZones(LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        System.out.println("Czas lokalny: " + zonedDateTime.format(FORMATTER));

        ZonedDateTime utsDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("UTC: " + utsDateTime.format(FORMATTER));

        ZonedDateTime londonDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println("Londyn: " + londonDateTime.format(FORMATTER));

        ZonedDateTime losAngelesDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println("Los Angeles: " + losAngelesDateTime.format(FORMATTER));

        ZonedDateTime sydneyAngelesDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Australia/Sydney"));
        System.out.println("Sydney: " + sydneyAngelesDateTime.format(FORMATTER));
    }

    private static LocalDateTime dateTime(Scanner scanner) {
        LocalDateTime localDateTime = null;
        System.out.println("Podaj datę:");
        String dateTime = scanner.nextLine();

        List<String> dataTimePatterns = Arrays.asList("yyyy-MM-dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss", "dd-MM-yyyy HH:mm:ss", "dd.MM.yyyy HH:mm:ss");
        String approvedDateTime = implementTimeForNoTimeDate(dateTime);

        for (String pat : dataTimePatterns) {
            try {
                DateTimeFormatter pattern = DateTimeFormatter.ofPattern(pat);
                TemporalAccessor temporalAccessor = pattern.parse(approvedDateTime);
                return localDateTime = LocalDateTime.from(temporalAccessor);
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
        return localDateTime;
    }

    private static String implementTimeForNoTimeDate(String dateTime) {
        int length = dateTime.length();
        if (length == 10) {
            return dateTime + " 00:00:00";
        } else {
            return dateTime;
        }
    }
}
