package attendance.exception;

public enum ExceptionMessage {
    INVALID_FORMAT_NUMBER("[ERROR] 구매 금액은 숫자여야 합니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
