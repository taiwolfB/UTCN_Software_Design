package tailup.exception;

public final class ErrorMessages {

    public static String INVALID_USERNAME_MESSAGE = "Username is either empty or username it is taken!";
    public static String INVALID_EMAIL_MESSAGE = "Email format is invalid!";
    public static String INVALID_FIRSTNAME_MESSAGE = "Firstname format is invalid!";
    public static String INVALID_LASTNAME_MESSAGE = "Lastnme format is invalid!";
    public static String INVALID_PASSWORD_MESSAGE = "Password is invalid(should not be nulL)!";
    public static String INVALID_DESTINATION_NAME_MESSAGE = "Destination name is invalid. It should be unique and not empty";
    public static String WRONG_CREDENTIALS_MESSAGE = "Username or password is incorrect!";
    public static String INVALID_VACATION_PACKAGE_NAME_MESSAGE = "Vacation Package name is invalid!";
    public static String INVALID_PRICE_MESSAGE = "Vactation Package price is invalid!";
    public static String INVALID_NUMBER_OF_RESERVATIONS_MESSAGE = "Vacation Package number of reservations is invalid!";
    public static String INVALID_START_DATE_MESSAGE = "Start date is Invalid. It can not be  before the current date and it can not be null!";
    public static String INVALID_END_DATE_MESSAGE = "End date can not be before the start date";

    private ErrorMessages()
    {

    }
}
