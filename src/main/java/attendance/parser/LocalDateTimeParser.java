package attendance.parser;

import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeParser {
    private static final DateTimeFormatter date_formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter time_formatter =
            DateTimeFormatter.ofPattern("HH:mm");

    private LocalDateTimeParser() {
    }

    public static LocalDateTime parse(String input) {

        return LocalDateTime.parse(input, date_formatter);
    }

    public static LocalTime parseLocalTime(String input) {
        try {
            return LocalTime.parse(input, time_formatter);
        } catch (DateTimeParseException e) {
            throw new CustomException(ExceptionMessage.INVALID_FORMAT);
        }
    }
}
