package sd.hotelroomservice.mappers;

import org.springframework.stereotype.Component;
import sd.hotelroomservice.dto.ReservationDTO;
import sd.hotelroomservice.entity.Reservation;
import sd.hotelroomservice.repository.UserRepository;

import java.util.stream.Collectors;

@Component
public class ReservationMapper {


    public static ReservationDTO toDTO(Reservation reservation)
    {
        return ReservationDTO
                .builder()
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .rooms(reservation
                        .getRooms()
                        .stream()
                        .map(r -> String.valueOf(r.getId())).collect(Collectors.toList()))
                .userId(reservation.getReservedBy().getId())
                .id(reservation.getId())
                .status(reservation.getStatus().toString())
                .build();
    }

    public static Reservation toEntity(ReservationDTO reservationDTO, UserRepository userRepository)
    {
        Reservation reservation = new Reservation();
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservation.getEndDate());
        reservation.setId(reservationDTO.getId());
        reservation.setReservedBy(userRepository.findAllById(reservationDTO.getUserId()).get(0));
//        reservation.setRooms(reservationDTO
//                .getRooms()
//                .stream().map(r -> RoomMapper.toEntity(r))
//                .collect(Collectors.toList()));
        return reservation;
    }
}
