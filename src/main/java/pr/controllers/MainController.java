package pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pr.forms.UserForm;
import pr.services.AuthenticationService;
import pr.services.UsersService;

import javax.print.MultiDoc;


@Controller
public class MainController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersService usersService;


    @GetMapping("/")
    public String mainPage(Authentication authentication) {
        if (authentication == null) {
            return signIn();
        }
        return "redirect:/tictactoe";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "SignUp";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "SignIn";
    }

    @PostMapping("/registration")
    public String registration(UserForm user) {
        usersService.addUser(user);
        return "redirect:/tictactoe";
    }

    @GetMapping("/tictactoe")
    public String ticTacToe(Authentication authentication, ModelMap user) {
        if (authentication == null) {
            return signIn();
        }
        user.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
        return "TicTacToe";
    }

    @PostMapping("/tttOnline")
    public String tttOnline(Authentication authentication) {
        usersService.updateOnlineTicTacToe(authenticationService.getUserByAuthentication(authentication));
        return "";
    }

    @PostMapping("/tttOffline")
    public String tttOffline(Authentication authentication) {
        usersService.updateOfflineTicTacToe(authenticationService.getUserByAuthentication(authentication));
        return "";
    }

    @PostMapping("/getOnlinePlayers")
    @ResponseBody
    public String getOnline() {
        return usersService.getOnlineTicTacToe();
    }


}
