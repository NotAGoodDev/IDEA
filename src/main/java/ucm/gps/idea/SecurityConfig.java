package ucm.gps.idea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ucm.gps.idea.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors()
            .and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(  "/js/**", "/css/**", "/images/**", "/webjars/**").permitAll()
                    .antMatchers( "/api/auth/register").permitAll()
                    .antMatchers( "/", "/home").permitAll()
                    .antMatchers( "/who").permitAll()
                    .antMatchers( "/login").permitAll()
                    .antMatchers( "/header").permitAll()
                    .antMatchers( "/footer").permitAll()
                    .antMatchers( "/register").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .httpBasic();

        http.formLogin()
                .loginProcessingUrl("/api/auth/login")
                .usernameParameter("username")
                .passwordParameter("password");

        http.logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutUrl("/api/auth/logout")
                .deleteCookies("JSESSIONID");
    }


}