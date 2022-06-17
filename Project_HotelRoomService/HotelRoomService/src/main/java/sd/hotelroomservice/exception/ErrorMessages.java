package sd.hotelroomservice.exception;

public final class ErrorMessages {

    public static String INVALID_USERNAME_MESSAGE = "Username is either empty or username it is taken!";
    public static String INVALID_EMAIL_MESSAGE = "Email format is invalid!";
    public static String INVALID_FIRSTNAME_MESSAGE = "Firstname format is invalid!";
    public static String INVALID_LASTNAME_MESSAGE = "Lastnme format is invalid!";
    public static String INVALID_PASSWORD_MESSAGE = "Password is invalid(should not be nulL)!";
    public static String MULTIPLE_RESTAURANTS_TO_CART_EXCEPTION = "You can not add food to cart from two different restaurants";
    public static String INVALID_CREDENTIALS_MESSAGE = "Wrong credentials";
    public static String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User not found !";
    public static String INVALID_PRICE_MESSAGE = "Room price is invalid!It should be an integer.";
    public static String INVALID_NUMBER_OF_PEOPLE_MESSAGE = "Number of people is not a valid number.It should be an integer.";

    private ErrorMessages()
    {

    }
}
