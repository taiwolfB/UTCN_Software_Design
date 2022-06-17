package sd.hotelroomservice.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException()
    {
        super(ErrorMessages.USER_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    public UserNotFoundException(String message)
    {
        super(message);
    }
}
