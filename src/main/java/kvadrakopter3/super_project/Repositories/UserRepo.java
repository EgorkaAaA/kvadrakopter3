package kvadrakopter3.super_project.Repositories;

import egor.mega.project.kvadrakopter.Entityes.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
}
