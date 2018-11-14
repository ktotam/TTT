package pr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import pr.services.AuthenticationService;
import pr.services.UsersService;

@Controller
public class TicTacToeController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersService usersService;

    @MessageMapping("/hello")
    @SendTo("/topic/tictactoe")
    public String onlineUsers() throws InterruptedException {
        Thread.sleep(100);
        return "refresh";
    }

    @MessageMapping("/invite")
    @SendTo("/topic/tictactoe")
    public String sendInvite(Authentication authentication, String message) throws InterruptedException {
        Thread.sleep(100);

        if (authenticationService.getUserByAuthentication(authentication).getId().toString().equals(message))
            return "refresh";
        return authenticationService.getUserByAuthentication(authentication).getId() + " " + message;
    }

    @MessageMapping("/turn")
    @SendTo("/topic/tictactoe")
    public String turn(String message) throws InterruptedException {
        Thread.sleep(100);
        return message;
    }

    @MessageMapping("/answer")
    @SendTo("/topic/tictactoe")
    public String answer(String message) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(message);
        return message;
    }

    @MessageMapping("/win")
    @SendTo("/topic/tictactoe")
    public String win(Authentication authentication) throws InterruptedException {
        Thread.sleep(100);
        usersService.addWin(authenticationService.getUserByAuthentication(authentication));
        return "refresh";
    }
}
