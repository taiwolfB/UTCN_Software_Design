package sd.hotelroomservice.exception;

public class InvalidPriceException extends Exception{

    public InvalidPriceException()
    {
        super(ErrorMessages.INVALID_PRICE_MESSAGE);
    }
}

