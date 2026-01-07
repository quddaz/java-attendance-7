package attendance.util;

import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;
import attendance.parser.LocalDateTimeParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileInitializer {
    private static final String ATTENDANCES_FILE_PATH = "src/main/resources/attendances.csv";

    private FileInitializer(){}

    public static Map<String, List<LocalDateTime>> getAttendances(){
        Map<String, List<LocalDateTime>> m = new HashMap<>();
        try(BufferedReader br = CSVReader.readFile(ATTENDANCES_FILE_PATH)){
            br.readLine(); //헤더 제거
            String line;
            while((line = br.readLine()) != null){
                String[] info = line.split(",");
                LocalDateTime localDateTime = LocalDateTimeParser.parse(info[2]);

                m.computeIfAbsent(info[0],k -> new ArrayList<>()).add(localDateTime);
            }

            return m;
        }catch (Exception e){
            throw new CustomException(ExceptionMessage.FILE_NOT_FOUNT);
        }
    }
}
