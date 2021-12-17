package kvadrakopter3.super_project.Seeders;

import kvadrakopter3.super_project.Entityes.KvadrokopterEntity;
import kvadrakopter3.super_project.Repositories.KvadrokopterRepo;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class KvadrakopterSeeder {
    public KvadrakopterSeeder(KvadrokopterRepo kvadrokopterRepo) {
        IntStream.range(1,21)
                .forEach(r -> kvadrokopterRepo.save(
                        new KvadrokopterEntity(
                                r,
                                "Kvadrakopter: " + r,
                                "https://i.ytimg.com/vi/SmshlLPtEgQ/mqdefault.jpg",
                                "https://www.youtube.com/watch?v=23v10-93Rq0",
                                "fuck"
                        )
                ));
    }
}

