package pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pr.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLogin(String username);

}
