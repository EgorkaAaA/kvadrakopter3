package kvadrakopter3.super_project.Controllers;

import kvadrakopter3.super_project.Controllers.ControllerInterfaces.CsrfTokenControllerInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfTokenControllerImpl implements CsrfTokenControllerInterface {
    @GetMapping("/csrf")
    public ResponseEntity getCsrfToken(){
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
