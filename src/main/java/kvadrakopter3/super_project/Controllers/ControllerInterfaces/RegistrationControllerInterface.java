package kvadrakopter3.super_project.Controllers.ControllerInterfaces;

import kvadrakopter3.super_project.Controllers.Responses.AuthResponse;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.ExceptionHandler.UserPasswordHaveNoMatches;
import kvadrakopter3.super_project.Exceptions.UserAllReadyExistsException;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RegistrationControllerInterface {
    @PostMapping("/registration")
    ResponseEntity<UserEntity> registration(@RequestBody UserEntity user) throws UserAllReadyExistsException;

    @PostMapping
    ResponseEntity<AuthResponse> loginUserByLoginAndPassword(@RequestBody UserEntity user)
            throws UserNotFoundException, UserPasswordHaveNoMatches;
}
