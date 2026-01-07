package attendance.parser;

import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeInputParser {
    private static final LocalTime CAMPUS_START_TIME = LocalTime.of(8,0);
    private static final LocalTime CAMPUS_END_TIME = LocalTime.of(23,0);

    private TimeInputParser(){}

    public static LocalTime Parse(String line, LocalDateTime localDateTime){
        String[] input = line.replace(" ","").split(":");
        int hour = validateIntFormat(input[0]);
        int minute = validateIntFormat(input[1]);
        LocalTime localTime = LocalTime.of(hour,minute);
        validateTime(localTime, localDateTime);
        validateRange(localTime);

        return localTime;
    }
    private static void validateTime(LocalTime localTime, LocalDateTime localDateTime){
        if(localTime.isAfter(localDateTime.toLocalTime()))
            throw new CustomException(ExceptionMessage.CHANGE_ERROR);
    }
    private static int validateIntFormat(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            throw new CustomException(ExceptionMessage.INVALID_FORMAT);
        }
    }
    private static void validateRange(LocalTime localTime){
        if(!localTime.isAfter(CAMPUS_END_TIME) && !localTime.isBefore(CAMPUS_START_TIME))
            throw new CustomException(ExceptionMessage.NO_CHANGE_TIME);
    }

}
