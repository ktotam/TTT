package pr.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pr.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String login;
    private Integer points;

    public User from() {
        return User.builder()
                .id(this.id)
                .login(this.login)
               // .points(this.points)
                .build();
    }
}
