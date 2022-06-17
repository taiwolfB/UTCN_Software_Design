package sd.hotelroomservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import sd.hotelroomservice.entity.enums.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "user")
@Getter
@Setter
public final class User
{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, unique = true)
    private String email;


    @OneToOne(mappedBy = "user")
    private JobCalendar jobCalendar;

//    @OneToMany(mappedBy = "manager")
//    private List<Room> rooms;

    @OneToMany(mappedBy = "reservedBy")
    private List<Reservation> reservations;

}
