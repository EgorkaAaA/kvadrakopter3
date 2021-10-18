package kvadrakopter3.super_project.Controllers;


import com.google.gson.JsonObject;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegistrationControllerImpl implements RegistrationControllerInterface {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(SuperProjectApplication.class);

    @Autowired
    public RegistrationControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @Override
    public ResponseEntity<UserEntity> registrationEndPoint(@ModelAttribute UserEntity user) {
        try {
            userService.saveUserInDataBase(user);
            logger.info("User created");
        } catch (UserAllReadyExistsException e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    @PostMapping("/registration/vk-auth")
    public ResponseEntity<JsonObject> vkAuthEndPoint(JsonObject object) {
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
