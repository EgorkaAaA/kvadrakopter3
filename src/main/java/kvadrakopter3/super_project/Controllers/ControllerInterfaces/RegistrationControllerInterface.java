package kvadrakopter3.super_project.Controllers.ControllerInterfaces;

import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Exceptions.UserAllReadyExistsException;
import org.springframework.http.ResponseEntity;

public interface RegistrationControllerInterface {
    ResponseEntity<UserEntity> registrationEndPoint(UserEntity user) throws UserAllReadyExistsException;
}
