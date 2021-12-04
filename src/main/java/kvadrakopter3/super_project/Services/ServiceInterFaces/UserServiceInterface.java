package kvadrakopter3.super_project.Services.ServiceInterFaces;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Exceptions.UserAllReadyExistsException;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInterface {
    ResponseEntity<UserEntity> saveUserInDataBase(UserEntity user) throws UserAllReadyExistsException;

    UserEntity findUserByUserName(String userName) throws UserNotFoundException;

    UserEntity createUserFromVkAuth(String code) throws ClientException, ApiException;

    UserEntity updateUserById(long id,UserEntity user) throws UserNotFoundException;

    UserEntity deleteUserById(long id) throws UserNotFoundException;

    UserEntity getUserById(long id) throws UserNotFoundException;

    List<UserEntity> getUsers();

    boolean userHaveRoleAdmin(long user) throws UserNotFoundException;
}
