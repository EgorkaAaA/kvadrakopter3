package kvadrakopter3.super_project.Controllers.ControllerInterfaces;

import kvadrakopter3.super_project.Entityes.UserEntity;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface GetsUpdateDeleteEndPointsUserInterface {
    ResponseEntity<UserEntity> getUserById(long id);

    ResponseEntity<List<UserEntity>> getAllUsers();

    ResponseEntity<UserEntity> updateUserById(long id);

    ResponseEntity<UserEntity> deleteUserBuId(long id);
}
