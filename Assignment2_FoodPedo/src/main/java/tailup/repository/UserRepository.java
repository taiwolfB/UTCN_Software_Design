package tailup.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import tailup.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String userName);
}
