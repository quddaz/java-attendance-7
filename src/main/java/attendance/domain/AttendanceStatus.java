package attendance.domain;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public enum AttendanceStatus {
    ATTENDANCE(5, "출석"),
    LATE(30, "지각"),
    ABSENCE(Integer.MAX_VALUE, "결석");

    private final int rate;
    private final String name;

    AttendanceStatus(int rate, String name) {
        this.rate = rate;
        this.name = name;
    }

    public static AttendanceStatus from(LocalTime start, LocalTime now) {
        long result = ChronoUnit.MINUTES.between(start, now);
        return Arrays.stream(values())
                .filter(a -> a.rate >= result)
                .findFirst().orElse(ABSENCE);
    }

    public String getName() {
        return name;
    }
}
