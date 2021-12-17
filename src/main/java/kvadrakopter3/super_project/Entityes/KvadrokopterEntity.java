package kvadrakopter3.super_project.Entityes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class KvadrokopterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> photoUrl;

    private String videoUrl;

    private String description;

    @Column
    @ElementCollection(targetClass = Integer.class)
    @MapKeyColumn(name="Employee_Position")
    private Map<String, Integer> specs;

    public KvadrokopterEntity(String name, List<String> photoUrl, String videoUrl, String description, Map<String, Integer> specs) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.videoUrl = videoUrl;
        this.description = description;
        this.specs = specs;
    }
}
