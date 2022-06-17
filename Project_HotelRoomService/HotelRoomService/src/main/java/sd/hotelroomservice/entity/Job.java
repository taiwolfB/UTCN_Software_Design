package sd.hotelroomservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import sd.hotelroomservice.entity.enums.JobStatus;
import sd.hotelroomservice.entity.enums.JobType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "job")
@Getter
@Setter
public final class Job {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    private JobType job;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @Column(name = "startDate")
    private LocalDate startDate;

    @OneToMany(mappedBy = "job")
    private List<Room> jobsInRoom;
    //@Column(name = "postedByUser")
    //private User user;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "jobs")
    private JobCalendar jobCalendar;


}
