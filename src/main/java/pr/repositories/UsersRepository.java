package pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pr.dtos.UserDto;
import pr.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLogin(String username);

    @Query(nativeQuery = true, value = "SELECT id FROM users WHERE login = ?1")
    Long getIdByLogin(String login);
}
