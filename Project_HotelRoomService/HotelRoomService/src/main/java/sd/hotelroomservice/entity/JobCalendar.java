package sd.hotelroomservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jobcalendar")
@Getter
@Setter
public final class JobCalendar {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    //one user -> one job calendar
    // one job calendar -> many jobs / one job -> one jobCalendar
    //private List<Job> jobs;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "jobCalendar")
    private List<Job> jobs;
}
