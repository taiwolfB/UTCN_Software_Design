package sd.hotelroomservice.exception;

public class MultipleRestaurantsToCartException extends  Exception{

    public MultipleRestaurantsToCartException()
    {
        super(ErrorMessages.MULTIPLE_RESTAURANTS_TO_CART_EXCEPTION);
    }
}
