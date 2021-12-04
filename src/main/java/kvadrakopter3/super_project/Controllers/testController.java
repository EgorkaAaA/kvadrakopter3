package kvadrakopter3.super_project.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/")
    public ResponseEntity<String> testEndPoint(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
