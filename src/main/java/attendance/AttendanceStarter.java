package attendance;

import attendance.util.DateTimesGenerator;
import java.time.LocalDateTime;

public class AttendanceStarter {
    private final LocalDateTime now;
    private final DateTimesGenerator dateTimesGenerator;

    public AttendanceStarter( DateTimesGenerator dateTimesGenerator) {
        this.dateTimesGenerator = dateTimesGenerator;
        this.now = dateTimesGenerator.generator();
    }

    public void run(){

    }
}
