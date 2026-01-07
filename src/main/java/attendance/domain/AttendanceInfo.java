package attendance.domain;

import attendance.dto.AttendanceCountDTO;
import attendance.dto.UserInfoDTO;
import attendance.dto.UserPenaltyDTO;
import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;
import attendance.view.OutputConfig;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AttendanceInfo {
    private final Map<String, Map<Integer, Time>> info;

    public AttendanceInfo(Map<String, Map<Integer, Time>> info) {
        this.info = new LinkedHashMap<>(info);
    }

    //갱신
    public void attendance(String name, LocalTime localTime, LocalDateTime localDateTime) {
        validateName(name);

        if (validateDuplicate(name, localDateTime.getDayOfMonth())) {
            throw new CustomException(ExceptionMessage.DUPLICATE_ATTENDANCE);
        }

        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        Time time = new Time(localTime.getHour(), localTime.getMinute(), dayOfWeek);
        info.get(name).put(localDateTime.getDayOfMonth(), time);
    }

    // 수정
    public String update(String name, int day, LocalTime localTime, LocalDateTime localDateTime) {
        validateName(name);
        if (!validateDuplicate(name, day)) {
            throw new CustomException(ExceptionMessage.DUPLICATE_ATTENDANCE);
        }
        LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), day);
        Time snapShot = info.get(name).get(day);
        Time time = new Time(localTime.getHour(), localTime.getMinute(), localDate.getDayOfWeek());
        info.get(name).put(day, time);

        return String.format(OutputConfig.ATTENDANCE_CHANGE_SUCCESS_MESSAGE.get()
                , snapShot.getPrint(localDate)
                , localTime.getHour(), localTime.getMinute(), time.getAttendanceStatus().getName());
    }

    private boolean validateDuplicate(String name, int day) {
        return info.get(name).containsKey(day);
    }

    public void validateName(String name) {
        if (!info.containsKey(name)) {
            throw new CustomException(ExceptionMessage.NOT_FOUNT_NAME);
        }
    }

    // 조회
    public UserInfoDTO getUserInfo(String name, LocalDateTime localDateTime) {
        validateName(name);

        StringBuilder sb = new StringBuilder();
        Map<Integer, Time> m = info.get(name);

        AttendanceCountDTO attendanceCountDTO = new AttendanceCountDTO();
        for (Map.Entry<Integer, Time> e : m.entrySet()) {
            LocalDate localDate = LocalDate.of(localDateTime.getYear(),
                    localDateTime.getMonth(), e.getKey());
            if (e.getValue() == null) {
                attendanceCountDTO.plus(AttendanceStatus.ABSENCE);
                sb.append(getNullMessage(localDate));
                continue;
            }

            sb.append(e.getValue().getPrint(localDate)).append("\n");
            attendanceCountDTO.plus(e.getValue().getAttendanceStatus());
        }

        return new UserInfoDTO(sb.toString(), attendanceCountDTO);
    }

    public List<UserPenaltyDTO> getPenalty() {
        List<UserPenaltyDTO> result = new ArrayList<>();
        for (Map.Entry<String, Map<Integer, Time>> e : info.entrySet()) {
            int lateCount = 0;
            int absenceCount = 0;

            for (Time time : e.getValue().values()) {
                if (time == null) {
                    absenceCount++;
                    continue;
                }
                if (time.getAttendanceStatus() == AttendanceStatus.LATE) {
                    lateCount++;
                }
                if (time.getAttendanceStatus() == AttendanceStatus.ABSENCE) {
                    absenceCount++;
                }

            }
            Penalty penalty = Penalty.from(lateCount, absenceCount);
            result.add(new UserPenaltyDTO(e.getKey(), lateCount, absenceCount, penalty));
        }
        return sort(result);
    }

    private List<UserPenaltyDTO> sort(List<UserPenaltyDTO> result) {
        result.sort((a, b) -> {
            int ax = a.absence_count() + (a.late_count() / 3);
            int bx = b.absence_count() + (b.late_count() / 3);
            if (ax == bx) {
                return a.name().compareTo(b.name());
            }
            return ax - bx;
        });

        return result;
    }

    public String getNullMessage(LocalDate localDate) {
        return String.format(OutputConfig.ATTENDANCE_NULL_MESSAGE.get()
                , localDate.getMonthValue()
                , localDate.getDayOfMonth()
                , KrDayOfWeek.from(localDate.getDayOfWeek()).name()) + "\n";
    }
}
