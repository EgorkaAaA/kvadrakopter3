package kvadrakopter3.super_project.Seeders;

import kvadrakopter3.super_project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class UserSeeder {
    private final UserService userService;

    @Autowired
    public UserSeeder(UserService userService) {
        IntStream.range(1,100).
        userService.saveUserInDataBase()
    }
}
