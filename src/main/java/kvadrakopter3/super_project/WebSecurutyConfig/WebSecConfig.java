package kvadrakopter3.super_project.WebSecurutyConfig;


import kvadrakopter3.super_project.Filters.JwtFilter;
import kvadrakopter3.super_project.Filters.UserNamePasswordAuthFilter;
import kvadrakopter3.super_project.Filters.csrfFilter;
import kvadrakopter3.super_project.Repositories.UserRepo;
import kvadrakopter3.super_project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter  {
    private final UserService userService;
    private final UserRepo userRepo;
    private final JsonParse jsonParse;
    private final JwtFilter jwtFilter;

    @Autowired
    public WebSecConfig(UserService userService, UserRepo userRepo, JsonParse jsonParse, JwtFilter jwtFilter) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.jsonParse = jsonParse;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();

        http.csrf().disable();

        http.httpBasic().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//        http.formLogin()
//                .loginProcessingUrl("/api/auth/login")
//                .usernameParameter("userName")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request,
//                                                        HttpServletResponse response,
//                                                        Authentication authentication)
//                            throws IOException, ServletException {
//                        //do nothing
//                    }
//                });

        http.addFilterBefore(new UserNamePasswordAuthFilter(authenticationManager(),userRepo),
                UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                    .antMatchers("/**/post/**").authenticated()
                    .antMatchers("/**/admins/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                    .addFilterAfter(new csrfFilter(),
                            BasicAuthenticationFilter.class)
//                    .addFilterBefore(new SameSiteFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authenticationProvider(authenticationProvider());

        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

}
