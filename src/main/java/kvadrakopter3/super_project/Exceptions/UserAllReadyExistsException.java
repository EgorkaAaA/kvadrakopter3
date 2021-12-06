package kvadrakopter3.super_project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UserAllReadyExistsException extends Exception {
    public UserAllReadyExistsException(String message) {
        super(message);
    }
}
