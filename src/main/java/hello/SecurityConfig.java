package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        final PasswordEncoder sha256 = new StandardPasswordEncoder();

        auth.inMemoryAuthentication()
                .passwordEncoder(sha256)
                .withUser(username)
                .password(sha256.encode(password))
                .roles("USER");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        System.out.println(username);
        System.out.println(password);
        http.httpBasic();
    }

}
