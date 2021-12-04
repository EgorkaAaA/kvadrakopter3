package kvadrakopter3.super_project.Repositories;

import kvadrakopter3.super_project.Entityes.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);

    UserEntity findById(long id);
}
