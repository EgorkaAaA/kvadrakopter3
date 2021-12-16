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
        try {
            roleRepo.saveAll(Arrays.asList(new RolesEntity(1,UserRoles.ROLE_USER.name()),
                    new RolesEntity(2,UserRoles.ROLE_ADMIN.name())));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        roleRepo.findAll().forEach(System.out::println);
    }
}
