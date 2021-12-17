package kvadrakopter3.super_project.Controllers;

import kvadrakopter3.super_project.Entityes.KvadrokopterEntity;
import kvadrakopter3.super_project.Services.KvadrakopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kvadrakopters")
public class KvadrakopterController {
    private final KvadrakopterService kvadrakopterService;

    @Autowired
    public KvadrakopterController(KvadrakopterService kvadrakopterService) {
        this.kvadrakopterService = kvadrakopterService;
    }

    @GetMapping
    public ResponseEntity<List<KvadrokopterEntity>> getAllKvadrakopters(){
        return new ResponseEntity<>(kvadrakopterService.getAllKvadrakopter(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KvadrokopterEntity> getKvadrakopterById(@PathVariable long id) {
        return new ResponseEntity<>(kvadrakopterService.getKvadrakopterById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KvadrokopterEntity> saveKvadrakopter(@ModelAttribute KvadrokopterEntity kvadrat) {
        return new ResponseEntity<>(kvadrakopterService.saveKvadrakopter(kvadrat),HttpStatus.OK);
    }
}
