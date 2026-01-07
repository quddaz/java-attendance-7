package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

public enum KrDayOfWeek {
    월요일(DayOfWeek.MONDAY, true, LocalTime.of(13, 0), LocalTime.of(18, 0)),
    화요일(DayOfWeek.TUESDAY, true, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    수요일(DayOfWeek.WEDNESDAY, true, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    목요일(DayOfWeek.THURSDAY, true, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    금요일(DayOfWeek.FRIDAY, true, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    토요일(DayOfWeek.SATURDAY, false, LocalTime.of(13, 0), LocalTime.of(13, 0)),
    일요일(DayOfWeek.SUNDAY, false, LocalTime.of(13, 0), LocalTime.of(13, 0));


    private final DayOfWeek dayOfWeek;
    private final boolean isAttendance;
    private final LocalTime start;
    private final LocalTime end;

    KrDayOfWeek(DayOfWeek dayOfWeek, boolean isAttendance, LocalTime start, LocalTime end) {
        this.dayOfWeek = dayOfWeek;
        this.isAttendance = isAttendance;
        this.start = start;
        this.end = end;
    }

    public static KrDayOfWeek from(DayOfWeek dayOfWeek) {
        return Arrays.stream(values())
                .filter(k -> k.dayOfWeek == dayOfWeek)
                .findAny()
                .orElseThrow();
    }

    public boolean isAttendance() {
        return isAttendance;
    }

    public LocalTime getStart() {
        return start;
    }

}
