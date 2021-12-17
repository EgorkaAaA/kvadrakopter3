package kvadrakopter3.super_project.Controllers;


import kvadrakopter3.super_project.Controllers.ControllerInterfaces.RegistrationControllerInterface;
import kvadrakopter3.super_project.Controllers.Responses.AuthResponse;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.ExceptionHandler.UserPasswordHaveNoMatches;
import kvadrakopter3.super_project.Exceptions.UserAllReadyExistsException;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import kvadrakopter3.super_project.Services.UserService;
import kvadrakopter3.super_project.WebSecurutyConfig.JwtProvider;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Log
public class RegistrationControllerImpl implements RegistrationControllerInterface {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public RegistrationControllerImpl(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/registration")
    @Override
    public ResponseEntity<UserEntity> registrationEndPoint(@RequestBody UserEntity user) throws UserAllReadyExistsException {
        log.info("Registration is ok");
        return new ResponseEntity<>(userService.saveUserInDataBase(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUserByLoginAndPassword(
            @RequestBody UserEntity user)
            throws UserNotFoundException, UserPasswordHaveNoMatches {
        UserEntity userFromDb = userService.loginUser(user);
        log.info("Login is ok");
        return new ResponseEntity<>(
                new AuthResponse(userFromDb,
                        jwtProvider.generateToken(userFromDb.getUserName())),HttpStatus.OK);
    }
}
