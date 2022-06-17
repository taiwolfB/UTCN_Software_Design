package sd.hotelroomservice.mappers;

import sd.hotelroomservice.dto.RoomDTO;
import sd.hotelroomservice.entity.Room;
import sd.hotelroomservice.entity.User;

public class RoomMapper {

    public static RoomDTO toDTO(Room room)
    {
        return RoomDTO
                .builder()
                .numberOfPeople(String.valueOf(room.getNumberOfPeople()))
                .pricePerNight(String.valueOf(room.getPricePerNight()))
                .id(Integer.toString(room.getId()))
                .build();
    }

    public static Room toEntity(RoomDTO roomDTO)
    {
        Room room = new Room();
        room.setNumberOfPeople(Integer.parseInt(roomDTO.getNumberOfPeople()));
        room.setPricePerNight(Integer.parseInt(roomDTO.getPricePerNight()));
        return room;
    }
}
