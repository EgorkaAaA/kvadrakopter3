package kvadrakopter3.super_project.Repositories;

import kvadrakopter3.super_project.Entityes.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RolesEntity, Long> {
    RolesEntity findByRoleName(String roleName);
}
