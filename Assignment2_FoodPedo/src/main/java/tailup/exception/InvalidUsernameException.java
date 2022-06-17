package tailup.exception;

public class InvalidUsernameException extends  Exception{

    public InvalidUsernameException()
    {
        super(ErrorMessages.INVALID_USERNAME_MESSAGE);
    }
}
