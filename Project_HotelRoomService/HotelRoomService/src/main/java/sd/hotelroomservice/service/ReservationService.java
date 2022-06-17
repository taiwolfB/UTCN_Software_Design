package sd.hotelroomservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.hotelroomservice.dto.ReservationDTO;
import sd.hotelroomservice.entity.Reservation;
import sd.hotelroomservice.entity.Room;
import sd.hotelroomservice.entity.User;
import sd.hotelroomservice.entity.enums.ReservationStatus;
import sd.hotelroomservice.mappers.ReservationMapper;
import sd.hotelroomservice.repository.ReservationRepository;
import sd.hotelroomservice.repository.RoomRepository;
import sd.hotelroomservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public double findIncomeForAllReservations()
    {
        List<Reservation> reservations = reservationRepository.findAll();
        double totalPrice = 0;
        for(Reservation reservation : reservations)
            for(Room room : reservation.getRooms())
                totalPrice+= room.getPricePerNight();
        return  totalPrice;
    }


    public void addReservation(ReservationDTO reservationDTO)
    {
        List<Integer> roomIds = reservationDTO.getRooms().stream().map(Integer::parseInt).collect(Collectors.toList());;
        List<Room> rooms = roomRepository.findByIdIn(roomIds);
        User user = userRepository.findByUsername(reservationDTO.getUserId());
        Reservation reservation = new Reservation();
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setStatus(ReservationStatus.WAITING_FOR_PAYMENT);
        reservation.setRooms(rooms);
        reservation.setReservedBy(user);
        reservationRepository.save(reservation);
    }

    public List<ReservationDTO> findReservationsForUsername(String username)
    {
        return this.reservationRepository.findReservationsForUsername(username).stream().map(ReservationMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteReservationByIdAndUsername(String id, String username)
    {
        User user = userRepository.findByUsername(username);
        user.setReservations(
                user.getReservations()
                        .stream()
                        .filter(
                                r -> r.getId() != id)
                        .collect(Collectors.toList()));
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        Reservation reservation = new Reservation();
        if(reservationOpt.isPresent())
            reservation = reservationOpt.get();

        reservation.setRooms(new ArrayList<>());
        reservationRepository.delete(reservation);
        userRepository.save(user);
    }
}
