package kvadrakopter3.super_project.Entityes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "UserEntity")
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String password;

    @ManyToMany
    private Collection<RolesEntity> roles;

    private Date deleteDate;

//    public UserEntity(long id, String userName, String password, Collection<RolesEntity> roles) {
//        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.roles = roles;
//        this.deleteDate = null;
//    }

    public UserEntity( String userName, String password, Collection<RolesEntity> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.deleteDate = null;
    }

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
