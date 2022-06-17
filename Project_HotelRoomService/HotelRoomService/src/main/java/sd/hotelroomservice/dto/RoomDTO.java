package sd.hotelroomservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoomDTO {

    private String pricePerNight;
    private String numberOfPeople;
    private String id;

}
