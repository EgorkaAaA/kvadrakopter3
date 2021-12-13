package kvadrakopter3.super_project.Controllers.Responses;

import kvadrakopter3.super_project.Entityes.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private UserEntity user;

    private String token;
}
