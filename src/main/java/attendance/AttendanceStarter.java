package attendance;

import attendance.domain.AttendanceInfo;
import attendance.domain.AttendanceStatus;
import attendance.domain.KrDayOfWeek;
import attendance.dto.UserInfoDTO;
import attendance.dto.UserPenaltyDTO;
import attendance.exception.CustomException;
import attendance.exception.ExceptionMessage;
import attendance.parser.IntParser;
import attendance.parser.LocalDateTimeParser;
import attendance.parser.MenuInputParser;
import attendance.util.DateTimesGenerator;
import attendance.util.FileInitializer;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AttendanceStarter {
    private static final String ATTENDANCE_CHECK = "1";
    private static final String ATTENDANCE_UPDATE = "2";
    private static final String ATTENDANCE_CREW_SELECT = "3";
    private static final String ATTENDANCE_ABSENCE_SELECT = "4";
    private final LocalDateTime now;
    private final KrDayOfWeek nowWeek;
    private final DateTimesGenerator dateTimesGenerator;


    public AttendanceStarter(DateTimesGenerator dateTimesGenerator) {
        this.dateTimesGenerator = dateTimesGenerator;
        this.now = this.dateTimesGenerator.generator();
        this.nowWeek = KrDayOfWeek.from(now.getDayOfWeek());
    }

    public void run() {
        AttendanceInfo attendanceInfo = new AttendanceInfo(FileInitializer.getAttendances(now));
        try {
            mainProcess(attendanceInfo);
        } catch (CustomException e) {
            OutputView.printExceptionMessage(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    private void mainProcess(AttendanceInfo attendanceInfo) {
        while (true) {

            OutputView.printMenu(nowWeek, now);
            String input = MenuInputParser.parse(InputView.readInput());
            if (!processSelector(input, attendanceInfo)) {
                break;
            }

        }
    }

    private boolean processSelector(String input, AttendanceInfo attendanceInfo) {
        switch (input) {
            case ATTENDANCE_CHECK:
                processAttendanceCheck(attendanceInfo);
                return true;
            case ATTENDANCE_UPDATE:
                processAttendanceUpdate(attendanceInfo);
                return true;
            case ATTENDANCE_CREW_SELECT:
                processAttendanceCrewSelect(attendanceInfo);
                return true;
            case ATTENDANCE_ABSENCE_SELECT:
                processAttendancePenalty(attendanceInfo);
                return true;
        }

        return false;
    }

    private void processAttendanceCheck(AttendanceInfo attendanceInfo) {
        validateHoliday();
        OutputView.printInputName();
        String name = InputView.readInput();
        attendanceInfo.validateName(name);
        OutputView.printInputTime();
        LocalTime localTime = LocalDateTimeParser.parseLocalTime(InputView.readInput());

        attendanceInfo.attendance(name, localTime, now);

        OutputView.printCheckResult(now, localTime, AttendanceStatus.from(nowWeek.getStart(), localTime), nowWeek);
    }

    private void processAttendanceUpdate(AttendanceInfo attendanceInfo) {
        OutputView.printUpdateInputName();
        String name = InputView.readInput();
        OutputView.printUpdateInputDay();
        int day = IntParser.parse(InputView.readInput());
        OutputView.printUpdateInputTime();
        LocalTime localTime = LocalDateTimeParser.parseLocalTime(InputView.readInput());

        String result = attendanceInfo.update(name, day, localTime, now);
        OutputView.printUpdateResult(result);
    }

    private void processAttendanceCrewSelect(AttendanceInfo attendanceInfo) {
        OutputView.printInputName();
        String name = InputView.readInput();

        UserInfoDTO userInfoDTO = attendanceInfo.getUserInfo(name, now);
        OutputView.printCrewSelect(name, userInfoDTO);
    }

    private void processAttendancePenalty(AttendanceInfo attendanceInfo) {
        List<UserPenaltyDTO> result = attendanceInfo.getPenalty();
        OutputView.printPenalty(result);
    }

    private void validateHoliday() {
        if (!nowWeek.isAttendance()) {
            throw new CustomException(String.format(ExceptionMessage.NON_WEEKDAY.message(),
                    now.getMonthValue(), now.getDayOfMonth(), nowWeek.name()));
        }
    }
}
