package kvadrakopter3.super_project.Seeders;

import kvadrakopter3.super_project.Entityes.RolesEntity;
import kvadrakopter3.super_project.Enums.UserRoles;
import kvadrakopter3.super_project.Repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleSeeder {

    @Autowired
    public RoleSeeder(RoleRepo roleRepo) {
        roleRepo.saveAll(Arrays.asList(new RolesEntity(UserRoles.ROLE_USER.name()),
                new RolesEntity(UserRoles.ROLE_ADMIN.name())));

        roleRepo.findAll().forEach(System.out::println);
    }
}
