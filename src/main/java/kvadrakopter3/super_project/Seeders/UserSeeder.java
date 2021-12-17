package kvadrakopter3.super_project.Seeders;

import kvadrakopter3.super_project.Entityes.RolesEntity;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Enums.UserRoles;
import kvadrakopter3.super_project.Repositories.RoleRepo;
import kvadrakopter3.super_project.Repositories.UserRepo;
import kvadrakopter3.super_project.Services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class UserSeeder {
    @SneakyThrows
    @Autowired
    public UserSeeder(UserRepo userRepo, RoleRepo roleRepo) {
        userRepo.saveAll(Arrays.asList(new UserEntity("admin",
                "admin",
                roleRepo.findAll()),
                new UserEntity("user",
                        "user",
                        Collections.singletonList(roleRepo.findByRoleName(UserRoles.ROLE_USER.name())))));
    }
}
