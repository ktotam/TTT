package pr.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pr.repositories.TicTacToeRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private TicTacToeRepository ticTacToeRepository;

    @Override
    public void run(String...args) throws Exception {

        while (true){
            Thread.sleep(600000);
            ticTacToeRepository.offlineUsers(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).minusMinutes(10));
        }

    }

}