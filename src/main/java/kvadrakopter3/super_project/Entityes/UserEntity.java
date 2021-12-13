package kvadrakopter3.super_project.Entityes;

import kvadrakopter3.super_project.Enums.UserRoles;
import lombok.*;


import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "UserEntity")
public class UserEntity  {
    public UserEntity(long id, String userName, String password, Collection<RolesEntity> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.deleteDate = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String password;

    @OneToMany
    private Collection<RolesEntity> roles;

    private Date deleteDate;

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
