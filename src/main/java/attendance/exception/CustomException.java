package attendance.exception;

public class CustomException extends IllegalArgumentException {

    public CustomException(ExceptionMessage message) {
        super(message.message());
    }
    public CustomException(String message) { super(message);}
}