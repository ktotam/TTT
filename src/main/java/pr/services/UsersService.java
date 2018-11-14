package pr.services;

import pr.forms.UserForm;
import pr.models.User;

import java.util.ArrayList;

public interface UsersService {

    void addUser(UserForm user);

    void updateOnlineTicTacToe(User user);

    void updateOfflineTicTacToe(User user);

    String getOnlineTicTacToe();

    void addWin(User user);
}
