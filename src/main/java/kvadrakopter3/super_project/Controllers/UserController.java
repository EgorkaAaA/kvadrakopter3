package kvadrakopter3.super_project.Controllers;

import kvadrakopter3.super_project.Controllers.ControllerInterfaces.UserControllerInterface;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import kvadrakopter3.super_project.Services.UserService;
import org.graalvm.compiler.hotspot.nodes.profiling.RandomSeedNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserControllerInterface {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Boolean> userHaveRoleAdmin(UserEntity user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.userHaveRoleAdmin(user), HttpStatus.OK);
    }
}
