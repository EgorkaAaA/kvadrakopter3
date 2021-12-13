package kvadrakopter3.super_project.ExceptionHandler;

import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import kvadrakopter3.super_project.SuperProjectApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ExceptionHandlerForAllExceptions {
    private final Logger log = LoggerFactory.getLogger(SuperProjectApplication.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponseBody> userNotFoundExceptionHandler(Exception ex) {
        log.error(ex.getMessage());
        Arrays.asList(ex.getStackTrace()).forEach(e -> log.info(String.valueOf(e)));
        return new ResponseEntity<>(new ExceptionResponseBody(ex,"error"), HttpStatus.NOT_FOUND);
    }
}
