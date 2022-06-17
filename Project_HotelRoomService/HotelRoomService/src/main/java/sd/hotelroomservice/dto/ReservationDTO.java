package sd.hotelroomservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sd.hotelroomservice.entity.Room;
import sd.hotelroomservice.entity.User;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@Setter
public class ReservationDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private String  userId;

    private List<String> rooms;

    private String id;

    private String status;
}
