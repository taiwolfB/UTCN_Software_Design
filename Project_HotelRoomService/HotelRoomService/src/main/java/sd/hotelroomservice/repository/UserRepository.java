package sd.hotelroomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.hotelroomservice.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    List<User> findAllById(String id);

    User findByUsername(String username);

    User findByEmail(String eMail);
}
