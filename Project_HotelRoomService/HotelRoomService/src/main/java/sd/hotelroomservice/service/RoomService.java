package sd.hotelroomservice.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.hotelroomservice.dto.RoomDTO;
import sd.hotelroomservice.entity.Reservation;
import sd.hotelroomservice.entity.Room;
import sd.hotelroomservice.mappers.RoomMapper;
import sd.hotelroomservice.repository.RoomRepository;
import sd.hotelroomservice.validators.RoomValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomValidator roomValidator;

    @SneakyThrows
    public void save(RoomDTO roomDTO)
    {
        roomValidator.validate(roomDTO);
        roomRepository.save(RoomMapper.toEntity(roomDTO));
    }

    public List<RoomDTO> findAllRooms()
    {
        return roomRepository.findAll().stream().map(RoomMapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public RoomDTO editRoom(RoomDTO roomDTO)
    {
        roomValidator.validate(roomDTO);
        Optional<Room> room = roomRepository.findById(Integer.parseInt(roomDTO.getId()));
        Room room1 = room.orElse(null);
        room1.setPricePerNight(Integer.parseInt(roomDTO.getPricePerNight()));
        room1.setNumberOfPeople(Integer.parseInt(roomDTO.getNumberOfPeople()));
        roomRepository.save(room1);
        Optional<Room> newRoom = roomRepository.findById(Integer.parseInt(roomDTO.getId()));
        Room room2 = newRoom.orElse(null);
        return RoomMapper.toDTO(room2);
    }

    public List<RoomDTO> findAllAvailableRoomsByDate(LocalDate startDate, LocalDate endDate)
    {
       List<Room> rooms = roomRepository.findAll();
       List<Room> finalRooms = new ArrayList<>();
       for(Room r : rooms)
       {
          List<Reservation> reservations = r.getReservations();
          if(reservations.isEmpty())
          {
              finalRooms.add(r);
          }
          else
          {
              for (Reservation res : reservations)
              {
                  if(!   ((res.getStartDate().isBefore(endDate) ||
                          res.getStartDate().isEqual(endDate)) &&
                                  (res.getEndDate().isAfter(startDate) ||
                                  res.getEndDate().isEqual(startDate)) ))
                  {
                      finalRooms.add(r);
                  }
              }

          }
       }
       return finalRooms.stream().map(RoomMapper::toDTO).collect(Collectors.toList());
    }

}

