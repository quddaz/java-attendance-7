package attendance.view;

public enum OutputConfig {
    MENU_MESSAGE("오늘은 %d월 %d일 %s입니다. 기능을 선택해주세요. \n"
    + "1. 출석 확인\n" + "2. 출석 수정\n" + "3. 크루별 출석 기록 확인\n"
    + "4. 제적 위험자 확인\n" + "Q. 종료\n"),
    NICKNAME_INPUT_MESSAGE("닉네임을 입력해 주세요."),
    TIME_INPUT_MESSAGE("등교 시간을 입력해 주세요."),
    ATTENDANCE_MESSAGE("%d월 %d일 %s %s:%s (%s)\n"),
    ATTENDANCE_CHANGE_MESSAGE("출석을 수정하려는 크루의 닉네임을 입력해 주세요."),
    DATE_INPUT_CHANGE_MESSAGE("수정하려는 날짜(일)를 입력해 주세요."),
    TIME_INPUT_CHANGE_MESSAGE("언제로 변경하겠습니까?"),
    ATTENDANCE_CHANGE_SUCCESS_MESSAGE("%s -> %s:%s (%s) 수정 완료!\n"),
    SELECT_ATTENDANCE_INFO_MESSAGE("이번 달 %s의 출석 기록입니다.\n"),
    ATTENDANCE_INFO_MESSAGE("출석: %d회\n" + "지각: %d회\n" + "결석: %d회\n"),
    USER_ATTENDANCE_STATUS_MESSAGE("%s 대상자입니다.\n");

    private final String format;

    OutputConfig(String format) {
        this.format = format;
    }

    public String get() {
        return format;
    }
    }