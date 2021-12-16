package kvadrakopter3.super_project.Services;


import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import kvadrakopter3.super_project.Entityes.RolesEntity;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Enums.UserRoles;
import kvadrakopter3.super_project.ExceptionHandler.UserPasswordHaveNoMatches;
import kvadrakopter3.super_project.Exceptions.UserAllReadyExistsException;
import kvadrakopter3.super_project.Exceptions.UserNotFoundException;
import kvadrakopter3.super_project.Repositories.RoleRepo;
import kvadrakopter3.super_project.Repositories.UserRepo;
import kvadrakopter3.super_project.Services.ServiceInterFaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService, UserServiceInterface {
    private final UserRepo userRepo;
    private final TransportClient transportClient = HttpTransportClient.getInstance();
    private final VkApiClient vk = new VkApiClient(transportClient);
    private final String CLIENT_SECRET = "fA2bwDddC161AtKpXxYb";
    private final String URI = "git@heroku.com:project.git";
    private final RoleRepo roleRepo;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
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
    public UserEntity saveUserInDataBase(UserEntity user) throws UserAllReadyExistsException {
        UserEntity userFromDb = userRepo.findByUserName(user.getUserName());
        if(userFromDb != null) {
            throw new UserAllReadyExistsException(String.format("User with name %s all ready exists", user.getUserName()));
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(Collections.singletonList(roleRepo.getById(UserRoles.ROLE_USER.name())));
        return userRepo.save(user);
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
//        UserAuthResponse authResponse = vk.oAuth()
//                .userAuthorizationCodeFlow(7957025,
//                        CLIENT_SECRET,
//                        "https://kvadrakopter3.herokuapp.com/api/auth/registration/vk-auth" ,
//                        code)
//                .execute();
//
//        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
//
//        String a =  vk.users().get(actor).fields(Fields.FIRST_NAME_ABL, Fields.LAST_NAME_ABL).executeAsString();
//        JsonParser parser = new JsonParser();
//        JsonObject obj = (JsonObject) parser.parse(a);
//        JsonElement first_name = obj.get("first_name");

        return null;//new UserEntity(first_name.toString(),null,null);
    }

    @Override
    public UserEntity updateUserById(long id, UserEntity user) throws UserNotFoundException {
        UserEntity userFromDb = userExists(id);
        user.setId(userFromDb.getId());
        return userRepo.save(user);
    }


    @Override
    public UserEntity deleteUserById(long id) throws UserNotFoundException {
        UserEntity userFromDb = userExists(id);
        userFromDb.setDeleteDate(new Date());
        return userRepo.save(userFromDb);
    }

    @Override
    public UserEntity getUserById(long id) throws UserNotFoundException {
        return userExists(id);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public boolean userHaveRoleAdmin(long id) throws UserNotFoundException {
        UserEntity userFromDb = userRepo.findById(id);
        if(userFromDb == null) {
            throw new UserNotFoundException(id);
        }
        return userFromDb.getRoles().stream().anyMatch(r -> r.getRoleName().equals(UserRoles.ROLE_ADMIN.name()));
    }

    @Override
    public UserEntity loginUser(UserEntity user) throws UserNotFoundException, UserPasswordHaveNoMatches {
        UserEntity userFromDb = userRepo.findByUserName(user.getUserName());
        if(userFromDb == null) {
            throw new UserNotFoundException(user.getUserName());
        }

        if(!passwordEncoder().matches(user.getPassword(), userFromDb.getPassword())) {
            throw new UserPasswordHaveNoMatches();
        }

        return userFromDb;
    }

    //Help methods
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Collection<? extends GrantedAuthority> mapRolesToGrantedAuthority(Collection<RolesEntity> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    private UserEntity userExists(long id) throws UserNotFoundException {
        UserEntity userFromDb = userRepo.findById(id);
        if(userFromDb == null) {
            throw new UserNotFoundException(id);
        }
        return userFromDb;
    }
}
