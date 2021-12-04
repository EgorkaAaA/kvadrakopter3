package kvadrakopter3.super_project.Controllers;

import kvadrakopter3.super_project.Controllers.ControllerInterfaces.UserControllerInterface;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import kvadrakopter3.super_project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements UserControllerInterface {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("{id}/admins/role")
    public ResponseEntity<Boolean> userHaveRoleAdmin(@PathVariable long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.userHaveRoleAdmin(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById( @PathVariable long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<UserEntity> updateUserById(@PathVariable long id,@ModelAttribute UserEntity user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUserById(id,user),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<UserEntity> deleteUserBuId(@PathVariable long id) throws UserNotFoundException {
        return null;
    }
}
