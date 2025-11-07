package com.rik.Library.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.isBlank()) {
            throw new RuntimeException("Date cannot be null or empty");
        }

        try {
            return LocalDate.parse(dateString, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date format: " + dateString);
        }
    }
}
