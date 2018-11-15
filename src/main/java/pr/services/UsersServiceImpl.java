package pr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pr.forms.UserForm;
import pr.models.TicTacToe;
import pr.models.User;
import pr.repositories.TicTacToeRepository;
import pr.repositories.UsersRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TicTacToeRepository ticTacToeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserForm user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        User newUser = User.builder()
                .login(user.getLogin())
                .hashPassword(hashPassword)
                .build();
        usersRepository.save(newUser);


        TicTacToe toe = TicTacToe.builder()
                .online(false)
                .points(0)
                .lastOnline(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
        ticTacToeRepository.save(toe);

    }

    @Override
    public void updateOnlineTicTacToe(User user) {
        ticTacToeRepository.updateTicTacToeLastOnline(user.getId(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        ticTacToeRepository.updateTicTacToeUserOn(user.getId());
    }

    @Override
    public void updateOfflineTicTacToe(User user) {
        ticTacToeRepository.updateTicTacToeUserOff(user.getId());
    }

    @Override
    public String getOnlineTicTacToe() {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonUsersList = new JSONObject();

        for (TicTacToe tempUser: ticTacToeRepository.getOnlineTicTacToe()) {
            JSONObject jsonUser = new JSONObject();
            jsonUser.put("id", tempUser.getId());
            jsonUser.put("login", tempUser.getLogin());
            jsonUser.put("points", tempUser.getPoints());
            jsonArray.add(jsonUser);
        }
        jsonUsersList.put("users", jsonArray);
        return jsonUsersList.toJSONString();
    }

    @Override
    public void addWin(User user) {
        ticTacToeRepository.addWin(user.getId());
    }


}
