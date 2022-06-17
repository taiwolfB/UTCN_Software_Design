package sd.hotelroomservice.exception;

public class InvalidCredentialsException extends Exception{

    public InvalidCredentialsException()
    {
        super(ErrorMessages.INVALID_CREDENTIALS_MESSAGE);
    }
    public InvalidCredentialsException(String message)
    {
        super(message);
    }
}
