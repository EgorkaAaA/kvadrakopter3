package kvadrakopter3.super_project.Controllers.ControllerInterfaces;

import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface UserControllerInterface {
    ResponseEntity<Boolean> userHaveRoleAdmin(UserEntity user) throws UserNotFoundException;
}
