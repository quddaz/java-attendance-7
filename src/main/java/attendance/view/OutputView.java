package attendance.view;

import attendance.domain.AttendanceStatus;
import attendance.domain.KrDayOfWeek;
import attendance.domain.Penalty;
import attendance.dto.AttendanceCountDTO;
import attendance.dto.UserInfoDTO;
import attendance.dto.UserPenaltyDTO;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class OutputView {
    private OutputView() {
    }

    public static void printExceptionMessage(String e) {
        System.out.println(e);
    }

    public static void printMenu(KrDayOfWeek krDayOfWeek, LocalDateTime localDateTime) {
        System.out.printf(OutputConfig.MENU_MESSAGE.get(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(),
                krDayOfWeek.name());
    }

    public static void printInputName() {
        System.out.println(OutputConfig.NICKNAME_INPUT_MESSAGE.get());
    }

    public static void printInputTime() {
        System.out.println(OutputConfig.TIME_INPUT_MESSAGE.get());
    }

    public static void printCheckResult(LocalDateTime localDateTime, LocalTime localTime,
                                        AttendanceStatus attendanceStatus, KrDayOfWeek krDayOfWeek) {
        System.out.printf(OutputConfig.ATTENDANCE_MESSAGE.get()
                , localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), krDayOfWeek.name()
                , localTime.getHour(), localTime.getMinute(), attendanceStatus.getName());
        System.out.println();
    }

    public static void printUpdateInputName() {
        System.out.println(OutputConfig.ATTENDANCE_CHANGE_MESSAGE.get());
    }

    public static void printUpdateInputDay() {
        System.out.println(OutputConfig.DATE_INPUT_CHANGE_MESSAGE.get());
    }

    public static void printUpdateInputTime() {
        System.out.println(OutputConfig.TIME_INPUT_CHANGE_MESSAGE.get());
    }

    public static void printUpdateResult(String print) {
        System.out.println(print);
    }

    public static void printCrewSelect(String name, UserInfoDTO userInfoDTO) {
        AttendanceCountDTO attendanceCountDTO = userInfoDTO.attendanceCountDTO();
        System.out.printf(OutputConfig.SELECT_ATTENDANCE_INFO_MESSAGE.get(), name);
        System.out.println(userInfoDTO.print());
        System.out.printf(OutputConfig.ATTENDANCE_INFO_MESSAGE.get()
                , attendanceCountDTO.getAttendance_count()
                , attendanceCountDTO.getLate_count()
                , attendanceCountDTO.getAbsence_count());

        String penalty = Penalty.from(attendanceCountDTO.getLate_count(), attendanceCountDTO.getAbsence_count())
                .getName();

        if (penalty.isEmpty()) {
            return;
        }

        System.out.printf(OutputConfig.USER_ATTENDANCE_STATUS_MESSAGE.get()
                , penalty);
    }

    public static void printPenalty(List<UserPenaltyDTO> userPenaltyDTOs) {
        System.out.println(OutputConfig.PENALTY_SELECT_MESSAGE);
        userPenaltyDTOs
                .forEach(
                        u -> System.out.printf(OutputConfig.PENALTY_SELECT_INFO_MESSAGE.get()
                                , u.name(), u.absence_count(), u.late_count(), u.penalty().getName()));
    }
}