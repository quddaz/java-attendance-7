package attendance.domain;

import attendance.dto.AttendanceCountDTO;
import attendance.dto.UserInfoDTO;
import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;
import attendance.view.OutputConfig;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class AttendanceInfo {
    private final Map<String, Map<Integer, Time>> info;

    public AttendanceInfo(Map<String, Map<Integer, Time>> info) {
        this.info = new LinkedHashMap<>(info);
    }

    //갱신
    public void Attendance(String name, LocalTime localTime, LocalDateTime localDateTime){
        validateName(name);

        if(validateDuplicate(name, localDateTime))
            throw new CustomException(ExceptionMessage.DUPLICATE_ATTENDANCE);

        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        Time time = new Time(localTime.getHour(), localTime.getMinute(), dayOfWeek);
        info.get(name).put(localDateTime.getDayOfMonth(), time);
    }
    // 수정
    public String update(String name, int day, LocalTime localTime, LocalDateTime localDateTime){
        validateName(name);
        if(!validateDuplicate(name, localDateTime))
            throw new CustomException(ExceptionMessage.DUPLICATE_ATTENDANCE);

        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        Time snapShot = info.get(name).get(day);
        Time time = new Time(localTime.getHour(), localTime.getMinute(), dayOfWeek);
        info.get(name).put(day, time);

        return String.format(OutputConfig.ATTENDANCE_CHANGE_SUCCESS_MESSAGE.get()
        , snapShot.getPrint(localDateTime, day)
        , localTime.getHour(), localTime.getMinute(), time.getAttendanceStatus().getName());
    }
    private boolean validateDuplicate(String name,LocalDateTime localDateTime){
        if(info.get(name).containsKey(localDateTime.getDayOfMonth()))
            return true;
        return false;
    }
    private void validateName(String name) {
        if (!info.containsKey(name))
            throw new CustomException(ExceptionMessage.NOT_FOUNT_NAME);
    }

    // 조회
    public UserInfoDTO getUserInfo(String name, LocalDateTime localDateTime){
        validateName(name);

        StringBuilder sb = new StringBuilder();
        Map<Integer, Time> m = info.get(name);

        AttendanceCountDTO attendanceCountDTO = new AttendanceCountDTO();
        for(Map.Entry<Integer, Time> e : m.entrySet()){
            if(e.getKey() == localDateTime.getDayOfMonth())
                continue;
            if(e.getValue() == null){
                attendanceCountDTO.plus(AttendanceStatus.ABSENCE);
                sb.append(getNullMessage(localDateTime, e.getKey()));
                continue;
            }

            sb.append(e.getValue().getPrint(localDateTime, e.getKey()));
            attendanceCountDTO.plus(e.getValue().getAttendanceStatus());
        }

        return new UserInfoDTO(sb.toString(), attendanceCountDTO);
    }

    public String getNullMessage(LocalDateTime localDateTime, int day){
            return String.format(OutputConfig.ATTENDANCE_MESSAGE.get()
                    , localDateTime.getMonth()
                    , day
                    , "--"
                    , "--"
                    , "결석");
    }
}
