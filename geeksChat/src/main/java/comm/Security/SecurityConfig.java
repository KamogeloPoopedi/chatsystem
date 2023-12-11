package comm.Security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http  .cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .securityMatcher("/api/**")
                .authorizeHttpRequests(c -> c.requestMatchers("/api/**").permitAll())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic().disable();
        return http.build();
}
@Bean
public PasswordEncoder passwordEncoder(){
    return  new BCryptPasswordEncoder();//this bean class is used to encrypt the password
}
}
