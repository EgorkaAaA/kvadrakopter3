package kvadrakopter3.super_project.Controllers;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import kvadrakopter3.super_project.Controllers.ControllerInterfaces.RegistrationControllerInterface;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Exceptions.UserAllReadyExistsException;
import kvadrakopter3.super_project.Services.UserService;
import kvadrakopter3.super_project.SuperProjectApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/auth")
public class RegistrationControllerImpl implements RegistrationControllerInterface {
    private final UserService userService;

    @Autowired
    public RegistrationControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @Override
    public ResponseEntity<UserEntity> registrationEndPoint(@ModelAttribute UserEntity user) throws UserAllReadyExistsException {
        return new ResponseEntity<>(userService.saveUserInDataBase(user), HttpStatus.OK);
    }
}
