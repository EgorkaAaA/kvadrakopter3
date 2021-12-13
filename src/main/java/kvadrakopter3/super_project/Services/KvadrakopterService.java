package kvadrakopter3.super_project.Services;

import kvadrakopter3.super_project.Entityes.KvadrokopterEntity;
import kvadrakopter3.super_project.Repositories.KvadrokopterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KvadrakopterService {
    private final KvadrokopterRepo kvadroRepo;

    @Autowired
    public KvadrakopterService(KvadrokopterRepo kvadroRepo) {
        this.kvadroRepo = kvadroRepo;
    }

    public KvadrokopterEntity saveKvadrakopter(KvadrokopterEntity kvadrakopter) {
        return kvadroRepo.save(kvadrakopter);
    }

    public List<KvadrokopterEntity> getAllKvadrakopter() {
        return kvadroRepo.findAll();
    }

    public KvadrokopterEntity getKvadrakopterById(long id) {
        return kvadroRepo.findById(id);
    }
}
