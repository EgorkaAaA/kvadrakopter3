package kvadrakopter3.super_project.Controllers.ControllerInterfaces;

import com.google.gson.JsonObject;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Exceptions.UserAllReadyExistsException;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface CrudUserInterface {
    ResponseEntity<UserEntity> getUserById(long id) throws UserNotFoundException;

    ResponseEntity<List<UserEntity>> getAllUsers();

    ResponseEntity<UserEntity> updateUserById(long id, UserEntity user) throws UserNotFoundException;

    ResponseEntity<UserEntity> deleteUserBuId(long id) throws UserNotFoundException;
}
