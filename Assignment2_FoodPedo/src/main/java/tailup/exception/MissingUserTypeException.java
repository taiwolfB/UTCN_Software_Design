package tailup.exception;

public class MissingUserTypeException extends  Exception{

    public MissingUserTypeException()
    {
        super("Please select an user type!");
    }
}
