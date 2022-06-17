package sd.hotelroomservice.exception;

public class InvalidNumberOfPeopleException extends Exception{

    public InvalidNumberOfPeopleException()
    {
        super(ErrorMessages.INVALID_NUMBER_OF_PEOPLE_MESSAGE);
    }
}
