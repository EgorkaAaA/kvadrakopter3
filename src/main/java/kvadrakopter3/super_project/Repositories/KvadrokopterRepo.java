package kvadrakopter3.super_project.Repositories;

import kvadrakopter3.super_project.Entityes.KvadrokopterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KvadrokopterRepo extends JpaRepository<KvadrokopterEntity, Long> {
    KvadrokopterEntity findById(long id);
}
