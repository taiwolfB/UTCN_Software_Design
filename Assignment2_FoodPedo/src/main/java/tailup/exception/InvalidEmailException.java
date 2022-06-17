package tailup.exception;

public class InvalidEmailException extends  Exception{

    public InvalidEmailException()
    {
        super(ErrorMessages.INVALID_EMAIL_MESSAGE);
    }
}
