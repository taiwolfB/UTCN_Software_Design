package sd.hotelroomservice.entity;



import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room")
@Getter
@Setter
public final class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int pricePerNight;

    @Column(nullable = false)
    private int numberOfPeople;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "rooms")
//    private User manager;

    @ManyToMany(mappedBy = "rooms")
    private List<Reservation> reservations;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "jobsInRoom")
    private Job job;

}
