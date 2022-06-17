package sd.hotelroomservice.validators;

import org.springframework.stereotype.Component;
import sd.hotelroomservice.dto.RoomDTO;
import sd.hotelroomservice.dto.UserDTO;
import sd.hotelroomservice.exception.*;

import javax.naming.InvalidNameException;

@Component
public class RoomValidator {

    public void validate(RoomDTO roomDTO) throws  Exception
    {
        if(!roomDTO.getNumberOfPeople().matches("^[1-9][0-9]*$"))
            throw new InvalidNumberOfPeopleException();
        if(!roomDTO.getPricePerNight().matches("^[1-9][0-9]*$"))
            throw new InvalidPriceException();
    }

}
