package sd.hotelroomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.hotelroomservice.entity.JobCalendar;

@Repository
public interface JobCalendarRepository extends JpaRepository<JobCalendar,String> {
}
