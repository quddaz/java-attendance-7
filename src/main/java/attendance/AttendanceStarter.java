package attendance;

import attendance.domain.AttendanceInfo;
import attendance.exception.CustomException;
import attendance.util.DateTimesGenerator;
import attendance.util.FileInitializer;
import attendance.view.OutputView;
import java.time.LocalDateTime;
import java.util.function.Supplier;

public class AttendanceStarter {
    private final LocalDateTime now;
    private final DateTimesGenerator dateTimesGenerator;

    public AttendanceStarter( DateTimesGenerator dateTimesGenerator) {
        this.dateTimesGenerator = dateTimesGenerator;
        this.now = dateTimesGenerator.generator();
    }

    public void run(){
        AttendanceInfo attendanceInfo = new AttendanceInfo(FileInitializer.getAttendances(now));
        try {
            while (true) {

            }
        } catch (CustomException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
    private void mainProcess(){
        OutputView.
    }

    private void processSelector(String input){
        switch ()
    }
}
