package attendance.parser;

import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;

public class IntParser {
    private IntParser() {
    }

    public static int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new CustomException(ExceptionMessage.INVALID_FORMAT);
        }
    }
}
