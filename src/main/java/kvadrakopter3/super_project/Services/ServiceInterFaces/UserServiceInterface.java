package kvadrakopter3.super_project.Services.ServiceInterFaces;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Exceptions.UserAllReadyExistsException;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface UserServiceInterface {
    ResponseEntity<UserEntity> saveUserInDataBase(UserEntity user) throws UserAllReadyExistsException;

    UserEntity findUserByUserName(String userName) throws UserNotFoundException;

    UserEntity createUserFromVkAuth(String code) throws ClientException, ApiException;

    boolean userHaveRoleAdmin(UserEntity user) throws UserNotFoundException;
}
