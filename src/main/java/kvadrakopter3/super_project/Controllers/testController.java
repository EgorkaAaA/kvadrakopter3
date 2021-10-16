package kvadrakopter3.super_project.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/")
    public String testEndPoint(){
        return "All ok";
    }
}
