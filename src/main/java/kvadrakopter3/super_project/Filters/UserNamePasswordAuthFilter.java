package kvadrakopter3.super_project.Filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import kvadrakopter3.super_project.Entityes.UserEntity;
import kvadrakopter3.super_project.Repositories.UserRepo;
import kvadrakopter3.super_project.WebSecurutyConfig.JsonParse;
import kvadrakopter3.super_project.WebSecurutyConfig.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UserNamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;
    private final UserRepo userRepo;


    public UserNamePasswordAuthFilter(AuthenticationManager authManager, UserRepo userRepo) {
        super();
        this.authManager = authManager;
        this.userRepo = userRepo;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            UserEntity userEntity = null; //getUserNamePasswordAuthenticationToken(request);

            System.out.println(userEntity.getUserName() + userEntity.getPassword()+ "1");
            Authentication auth = new UsernamePasswordAuthenticationToken(userEntity.getUserName(),
                    userEntity.getPassword());

            return authManager.authenticate(auth);
        } catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
                    + authResult);
        }

        SecurityContextHolder.getContext().setAuthentication(authResult);
    }

//    private UserEntity getUserNamePasswordAuthenticationToken(HttpServletRequest request)  throws IOException {
//        StringBuffer sb = new StringBuffer();
//        BufferedReader bufferedReader = null;
//        String content ;
//        LoginRequest sr;
//
//        try {
//            bufferedReader = request.getReader();
//            char[] charBuffer = new char[128];
//            int bytesRead;
//            while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
//                sb.append(charBuffer, 0, bytesRead);
//            }
//            content = sb.toString();
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                sr = objectMapper.readValue(content, LoginRequest.class);
//            } catch (Throwable t) {
//                throw new IOException(t.getMessage(), t);
//            }
//        } catch (IOException ex) {
//
//            throw ex;
//        } finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException ex) {
//                    throw ex;
//                }
//            }
//        }
//        return new UserEntity(sr.getUserName(),sr.getPassword());
//    }
}