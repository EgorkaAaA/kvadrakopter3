package kvadrakopter3.super_project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userName) {
        super(String.format("User with name: %s not found", userName));
    }

    public UserNotFoundException(long id) {
        super(String.format("User with id: %d not found", id));
    }
}
