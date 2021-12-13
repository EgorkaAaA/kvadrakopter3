package kvadrakopter3.super_project.WebSecurutyConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class LoginRequest {
    private String userName;

    private String password;
}
