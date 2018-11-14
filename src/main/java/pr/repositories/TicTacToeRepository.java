package pr.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pr.dtos.UserDto;
import pr.models.TicTacToe;
import pr.models.User;

import java.util.ArrayList;

public interface TicTacToeRepository extends JpaRepository<TicTacToe, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE tic_tac_toe SET online = true WHERE id = ?1")
    void updateTicTacToeUserOn(Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE tic_tac_toe SET online = false WHERE id = ?1")
    void updateTicTacToeUserOff(Long userId);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM tic_tac_toe WHERE from_id = ?1)")
    boolean checkTicTacToeUser(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM tic_tac_toe WHERE online = true")
    ArrayList<TicTacToe> getOnlineTicTacToe();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE tic_tac_toe SET points = points + 1 WHERE id = ?1")
    void addWin(Long id);
}
