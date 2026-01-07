package attendance.util;

import attendance.domain.KrDayOfWeek;
import attendance.domain.Time;
import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;
import attendance.parser.LocalDateTimeParser;
import attendance.view.OutputView;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileInitializer {
    private static final String ATTENDANCES_FILE_PATH = "src/main/resources/attendances.csv";

    private FileInitializer(){}

    public static Map<String, Map<Integer, Time>> getAttendances(LocalDateTime localDateTime){
        Map<String, Map<Integer, Time>> m = new HashMap<>();
        Map<Integer, Time> dayInfo = getMap(localDateTime);
        try(BufferedReader br = CSVReader.readFile(ATTENDANCES_FILE_PATH)){
            br.readLine(); //헤더 제거
            String line;
            while((line = br.readLine()) != null){
                String[] info = line.split(",");
                LocalDateTime local = LocalDateTimeParser.parse(info[1]);

                m.computeIfAbsent(info[0],k -> new LinkedHashMap<>(dayInfo))
                        .put(local.getDayOfMonth(), new Time(local.getHour(), local.getMinute(),local.getDayOfWeek()));
            }

            return m;
        }catch (Exception e){
            throw new CustomException(ExceptionMessage.FILE_NOT_FOUNT);
        }
    }

    private static Map<Integer, Time> getMap(LocalDateTime localDateTime){
        Map<Integer, Time> dayInfo = new LinkedHashMap<>();
        for(int i = 1; i < localDateTime.getDayOfMonth(); i++){
            if(KrDayOfWeek.from(
                    LocalDate.of(
                            localDateTime.getYear(),
                            localDateTime.getMonth(),
                            i).getDayOfWeek()).isAttendance()){
                dayInfo.put(i, null);
            }
        }
        return dayInfo;
    }
}
