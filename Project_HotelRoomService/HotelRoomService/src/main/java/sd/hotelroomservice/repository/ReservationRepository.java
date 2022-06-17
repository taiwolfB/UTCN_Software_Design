package sd.hotelroomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sd.hotelroomservice.entity.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {

    @Query("SELECT r FROM Reservation r join r.reservedBy user where user.username = :username")
    List<Reservation> findReservationsForUsername(String username);
    void deleteById(String id);
}
