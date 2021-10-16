package kvadrakopter3.super_project.Services;

import egor.mega.project.kvadrakopter.Entityes.RolesEntity;
import egor.mega.project.kvadrakopter.Entityes.UserEntity;
import egor.mega.project.kvadrakopter.Exceptions.UserAllReadyExistsException;
import egor.mega.project.kvadrakopter.Exceptions.UserNotFoundException;
import egor.mega.project.kvadrakopter.Repositories.UserRepo;
import egor.mega.project.kvadrakopter.Services.ServiceInterFaces.UserServiceInterface;
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
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService, UserServiceInterface {
    private final UserRepo userRepo;
    //private final TransportClient transportClient = HttpTransportClient.getInstance();
    //private final VkApiClient vk = new VkApiClient(transportClient);

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
    public UserEntity createUserFromVkAuth(String userName) {
//        UserAuthResponse authResponse = vk.oAuth()
//                .userAuthorizationCodeFlow(7957025, CLIENT_SECRET, , code)
//                .execute();

        //UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        
        return null;
    }

    //Help methods
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Collection<? extends GrantedAuthority> mapRolesToGrantedAuthority(Collection<RolesEntity> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
