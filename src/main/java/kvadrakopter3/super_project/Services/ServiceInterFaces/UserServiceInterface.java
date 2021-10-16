package kvadrakopter3.super_project.Services.ServiceInterFaces;

import egor.mega.project.kvadrakopter.Entityes.UserEntity;
import egor.mega.project.kvadrakopter.Exceptions.UserAllReadyExistsException;
import egor.mega.project.kvadrakopter.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface UserServiceInterface {
    ResponseEntity<UserEntity> saveUserInDataBase(UserEntity user) throws UserAllReadyExistsException;

    UserEntity findUserByUserName(String userName) throws UserNotFoundException;

    UserEntity createUserFromVkAuth(String userName);
}
