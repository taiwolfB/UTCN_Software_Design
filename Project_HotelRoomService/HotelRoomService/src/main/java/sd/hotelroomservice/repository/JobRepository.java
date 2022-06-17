package sd.hotelroomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.hotelroomservice.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,String> {
}
