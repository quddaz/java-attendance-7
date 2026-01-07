package attendance.parser;

import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;

public class MenuInputParser {
    private MenuInputParser() {
    }


    public static String parse(String input) {
        String line = input.trim();

        if (line.equals("1") || line.equals("2")
                || line.equals("3") || line.equals("4") || line.equals("Q")) {
            return line;
        }

        throw new CustomException(ExceptionMessage.INVALID_FORMAT);
    }
}
