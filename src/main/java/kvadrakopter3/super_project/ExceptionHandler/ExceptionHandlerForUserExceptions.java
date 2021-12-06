package kvadrakopter3.super_project.ExceptionHandler;

import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerForUserExceptions {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseBody> userNotFoundExceptionHandler(UserNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponseBody(ex,"error"), HttpStatus.NOT_FOUND);
    }
}
