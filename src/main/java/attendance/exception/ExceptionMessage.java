package attendance.exception;

public enum ExceptionMessage {
    INVALID_FORMAT("[ERROR] 잘못된 형식을 입력하였습니다."),
    NOT_FOUNT_NAME("[ERROR] 등록되지 않은 닉네임입니다."),
    NON_WEEKDAY("[ERROR] %d월 %d일 %s은 등교일이 아닙니다.\n"),
    CHANGE_ERROR("[ERROR] 아직 수정할 수 없습니다."),
    NO_CHANGE_TIME("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다."),
    DUPLICATE_ATTENDANCE("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
