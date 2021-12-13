package kvadrakopter3.super_project.Seeders;

import kvadrakopter3.super_project.Entityes.RolesEntity;
import kvadrakopter3.super_project.Enums.UserRoles;
import kvadrakopter3.super_project.Repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleSeeder {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleSeeder(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;

        roleRepo.saveAll(Arrays.asList(new RolesEntity(UserRoles.ROLE_USER.name()),
                new RolesEntity(UserRoles.ROLE_ADMIN.name())));
    }
}
