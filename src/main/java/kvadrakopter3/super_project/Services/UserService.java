package kvadrakopter3.super_project.Services;


import com.google.gson.JsonObject;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.queries.users.UsersGetQuery;
import kvadrakopter3.super_project.Entityes.RolesEntity;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Exceptions.UserAllReadyExistsException;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import kvadrakopter3.super_project.Repositories.UserRepo;
import kvadrakopter3.super_project.Services.ServiceInterFaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService, UserServiceInterface {
    private final UserRepo userRepo;
    private final TransportClient transportClient = HttpTransportClient.getInstance();
    private final VkApiClient vk = new VkApiClient(transportClient);
    private final String CLIENT_SECRET = "fA2bwDddC161AtKpXxYb";

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUserName(userName);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User with name %s not found", userName));
        }
        return new User(user.getUserName(), user.getPassword(),mapRolesToGrantedAuthority(user.getRoles()));
    }


    @Override
    public ResponseEntity<UserEntity> saveUserInDataBase(UserEntity user) throws UserAllReadyExistsException {
        UserEntity userFromDb = userRepo.findByUserName(user.getUserName());
        if(userFromDb != null) {
            throw new UserAllReadyExistsException(String.format("User with name %s all ready exists", user.getUserName()));
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public UserEntity findUserByUserName(String userName) throws UserNotFoundException {
        UserEntity userFromDb = userRepo.findByUserName(userName);
        if(userFromDb == null) {
            throw new UserNotFoundException(String.format("User with name %s not found",userName));
        }
        return userFromDb;
    }

    @Override
    public UserEntity createUserFromVkAuth(String code) throws ClientException, ApiException {
        UserAuthResponse authResponse = vk.oAuth()
                .userAuthorizationCodeFlow(7957025,
                        CLIENT_SECRET,
                        "https://kvadrakopter3.herokuapp.com/api/auth/registration/vk-auth" ,
                        code)
                .execute();

        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

        String a = vk.users().get(actor).fields(Fields.FIRST_NAME_ABL, Fields.LAST_NAME_ABL).toString();
        return new UserEntity(0,a,null,null);
    }

    //Help methods
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Collection<? extends GrantedAuthority> mapRolesToGrantedAuthority(Collection<RolesEntity> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
