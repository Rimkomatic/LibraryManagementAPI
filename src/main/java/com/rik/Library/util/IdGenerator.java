package com.rik.Library.util;

import java.security.SecureRandom;

public class IdGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static long generateSevenDigitId() {
        return 1_000_000L + random.nextInt(9_000_000); // ensures it's always 7 digits
    }
}
