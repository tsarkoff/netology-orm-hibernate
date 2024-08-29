package ru.netology.ormhibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    private final static class Authority {
        public final static String READ_AGE = "READ_AGE";
        public final static String READ_ADDRESS = "READ_ADDRESS";
        public final static String READ_ALL = "READ_ALL";
        public final static String UPDATE = "UPDATE";
        public final static String WRITE = "WRITE";
        public final static String DELETE = "DELETE";
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails teacher = User.withUsername("teacher")
                .password(encoder.encode("pwd"))
                .authorities(Authority.READ_AGE)
                .build();
        UserDetails postman = User.withUsername("postman")
                .password(encoder.encode("pwd"))
                .authorities(Authority.READ_ADDRESS)
                .build();
        UserDetails policeman = User.withUsername("policeman")
                .password(encoder.encode("pwd"))
                .authorities(Authority.READ_ADDRESS, Authority.READ_AGE, Authority.READ_ALL)
                .build();
        UserDetails government = User.withUsername("gov")
                .password(encoder.encode("pwd"))
                .authorities(Authority.UPDATE)
                .build();
        UserDetails security = User.withUsername("security")
                .password(encoder.encode("pwd"))
                .authorities(Authority.WRITE)
                .build();
        UserDetails God = User.withUsername("God")
                .password(encoder.encode("pwd"))
                .authorities(Authority.READ_AGE, Authority.READ_ADDRESS, Authority.READ_ALL, Authority.UPDATE, Authority.WRITE, Authority.DELETE)
                .build();
        return new InMemoryUserDetailsManager(teacher, postman, policeman, government, security, God);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "person/by-age").permitAll()
                        .requestMatchers(HttpMethod.GET, "person/by-city").hasAuthority(Authority.READ_ADDRESS)
                        .requestMatchers(HttpMethod.GET, "person/**").hasAuthority(Authority.READ_ALL)
                        .requestMatchers(HttpMethod.PUT, "person/").hasAuthority(Authority.UPDATE)
                        .requestMatchers(HttpMethod.POST, "person/").hasAuthority(Authority.WRITE)
                        .requestMatchers(HttpMethod.DELETE, "person/**").hasAuthority(Authority.DELETE)
                        .anyRequest()
                        .permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}