package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Day {
    private final int day;
    private final DayOfWeek dayOfWeek;
    private final LocalTime localTime;
    private final AttendanceStatus attendanceStatus;

    public Day(int day, DayOfWeek dayOfWeek, LocalTime localTime, AttendanceStatus attendanceStatus) {
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.localTime = localTime;
        this.attendanceStatus = attendanceStatus;
    }
}
