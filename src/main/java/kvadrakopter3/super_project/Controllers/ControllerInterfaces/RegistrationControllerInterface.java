package kvadrakopter3.super_project.Controllers.ControllerInterfaces;

import com.google.gson.JsonObject;
import kvadrakopter3.super_project.Entityes.UserEntity;
import org.springframework.http.ResponseEntity;

public interface RegistrationControllerInterface {
    ResponseEntity<UserEntity> registrationEndPoint(UserEntity user);

    ResponseEntity<JsonObject> vkAuthEndPoint(JsonObject user);
}
