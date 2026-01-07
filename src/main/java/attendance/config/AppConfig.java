package attendance.config;

import attendance.AttendanceStarter;
import attendance.generator.DateTimesGenerator;
import attendance.generator.LocalDateTimesGenerator;

public class AppConfig {
    private final DateTimesGenerator dateTimesGenerator;
    private final AttendanceStarter attendanceStarter;


    public AppConfig() {
        this.dateTimesGenerator = new LocalDateTimesGenerator();
        this.attendanceStarter = new AttendanceStarter(dateTimesGenerator);
    }

    public AttendanceStarter attendanceStarter() {
        return attendanceStarter;
    }
}
