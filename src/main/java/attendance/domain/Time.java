package attendance.domain;

import attendance.view.OutputConfig;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Time {
    private final int hour;
    private final int minute;
    private final KrDayOfWeek dayOfWeek;

    private final AttendanceStatus attendanceStatus;

    public Time(int hour, int minute, DayOfWeek dayOfWeek) {
        this.hour = hour;
        this.minute = minute;
        this.dayOfWeek = KrDayOfWeek.from(dayOfWeek);
        this.attendanceStatus = initAttendanceStatus();
    }

    public LocalTime getLocalTime(){
        return LocalTime.of(hour,minute);
    }

    public KrDayOfWeek getDayOfWeek(){
        return dayOfWeek;
    }

    public AttendanceStatus getAttendanceStatus(){
        return attendanceStatus;
    }
    private AttendanceStatus initAttendanceStatus(){
        return AttendanceStatus.from(dayOfWeek.getStart(), getLocalTime());
    }

    public String getPrint(LocalDateTime localDateTime, int day){
        return String.format(OutputConfig.ATTENDANCE_MESSAGE.get()
                , localDateTime.getMonth()
                , day
                , hour
                , minute
                , attendanceStatus.getName());
    }
}
