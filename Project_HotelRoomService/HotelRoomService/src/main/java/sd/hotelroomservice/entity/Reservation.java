package sd.hotelroomservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import sd.hotelroomservice.entity.enums.ReservationStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "reservation")
@Entity
@Getter
@Setter
public final class Reservation {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private LocalDate startDate;

    private LocalDate endDate;

    // a single User to one reservation
  //  private User customer;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "reservations")
    private User reservedBy;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reservation_room",
    joinColumns = @JoinColumn(name = "reservation_id"),
    inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> rooms;

    private ReservationStatus status;

}
