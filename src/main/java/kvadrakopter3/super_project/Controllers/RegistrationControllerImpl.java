package kvadrakopter3.super_project.Controllers;

import egor.mega.project.kvadrakopter.Controllers.ControllerInterfaces.RegistrationControllerInterface;
import egor.mega.project.kvadrakopter.Entityes.UserEntity;
import egor.mega.project.kvadrakopter.Exceptions.UserAllReadyExistsException;
import egor.mega.project.kvadrakopter.KvadrakopterApplication;
import egor.mega.project.kvadrakopter.Services.UserService;
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
    private final Logger logger = LoggerFactory.getLogger(KvadrakopterApplication.class);

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
    @PostMapping("/post/vk-auth")
    public ResponseEntity<UserEntity> vkAuthEndPoint(UserEntity user) {
        return null;
    }
}
