package kvadrakopter3.super_project.WebSecurutyConfig;


import kvadrakopter3.super_project.Filters.SameSiteFilter;
import kvadrakopter3.super_project.Filters.csrfFilter;
import kvadrakopter3.super_project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
    private final UserService userService;

    @Autowired
    public WebSecConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .authorizeRequests()
                    .antMatchers("/**/post/**").authenticated()
                    .antMatchers("/**/admins/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                    .csrf().disable()
//                    .addFilterAfter(new csrfFilter(),
//                            BasicAuthenticationFilter.class)
//                    .addFilterBefore(new SameSiteFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authenticationProvider(authenticationProvider())
                    .formLogin()
                    .loginProcessingUrl("/api/auth/login")
                .and()
                    .httpBasic()
                    .disable();
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("https://localhost:3000", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

//    @Override
//    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
//        event.
//    }
}
