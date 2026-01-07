package attendance.util;

import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    private CSVReader() {
    }

    public static BufferedReader readFile(String fileName) {
        try {
            return new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            throw new CustomException(ExceptionMessage.FILE_NOT_FOUNT);
        }
    }
}
