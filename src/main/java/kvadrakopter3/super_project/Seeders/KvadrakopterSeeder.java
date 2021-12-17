package kvadrakopter3.super_project.Seeders;

import kvadrakopter3.super_project.Entityes.KvadrokopterEntity;
import kvadrakopter3.super_project.Repositories.KvadrokopterRepo;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class KvadrakopterSeeder {
    public KvadrakopterSeeder(KvadrokopterRepo kvadrokopterRepo) {
        Map<String,Integer> map = new HashMap<>();
        map.put("speed",100);
        map.put("workTime",100);
        map.put("maxHight",100);
        map.put("cost",1000);
        map.put("wight",500);
        map.put("iq",7);
        IntStream.range(1,21)
                .forEach(r -> kvadrokopterRepo.save(
                        new KvadrokopterEntity(
                                "Kvadrakopter: " + r,
                                Arrays.asList("https://i.ytimg.com/vi/SmshlLPtEgQ/mqdefault.jpg",
                                        "http://infocopter.ru/wp-content/uploads/2016/10/s2908-106-1-810x500.jpg",
                                        "https://cdn.fishki.net/upload/post/2021/05/26/3769721/tn/bezymjannyj-kollazh--29.jpg",
                                        "https://images.golos.io/DQmW7Mkv2bQBcpPownkv7xpGRGgy6aLtoH8DtF38SLzoYoh/maxresdefault.jpg",
                                        "https://i1.i.ua/prikol/pic/9/6/1047369.jpg"),
                                "https://www.youtube.com/watch?v=23v10-93Rq0",
                                "this is description be careful kvadrakopter is flight and fly",
                                map
                        )
                ));
    }
}

