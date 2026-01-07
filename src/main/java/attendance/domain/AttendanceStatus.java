package attendance.domain;

import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public enum AttendanceStatus {
    ATTENDANCE(5),
    LATE(30),
    ABSENCE(Integer.MAX_VALUE);

    private final int rate;

    AttendanceStatus(int rate) {
        this.rate = rate;
    }

    public static AttendanceStatus from(LocalTime start, LocalTime now){
        long result = ChronoUnit.MINUTES.between(start, now);
        return Arrays.stream(values())
                .filter(a -> a.rate >= result)
                .findFirst().orElse(ABSENCE);
    }
}
