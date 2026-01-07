package attendance.domain;

import attendance.view.OutputConfig;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

    public LocalTime getLocalTime() {
        return LocalTime.of(hour, minute);
    }

    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    private AttendanceStatus initAttendanceStatus() {
        return AttendanceStatus.from(dayOfWeek.getStart(), getLocalTime());
    }
    public String getPrint(LocalDate localDate) {
        return String.format(OutputConfig.ATTENDANCE_MESSAGE.get()
                , localDate.getMonthValue()
                , localDate.getDayOfMonth()
                , dayOfWeek.name()
                , hour
                , minute
                , attendanceStatus.getName());
    }
}
