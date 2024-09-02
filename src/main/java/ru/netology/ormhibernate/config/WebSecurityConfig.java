package ru.netology.ormhibernate.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    final private DataSource dataSource;

    private final static class Authority {
        public final static String READ_AGE = "READ_AGE";
        public final static String READ_ADDRESS = "READ_ADDRESS";
        public final static String READ_ALL = "READ_ALL";
        public final static String UPDATE_ONE = "UPDATE";
        public final static String WRITE_MANY = "WRITE_MANY";
        public final static String DELETE_ANY = "DELETE_ANY";
    }

    private final static class Roles {
        public final static String READ = "READ";
        public final static String WRITE = "WRITE";
        public final static String DELETE = "DELETE";
    }

    @Bean
    public UserDetailsManager userJdbcDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "person-secure").permitAll()
                        .requestMatchers(HttpMethod.GET, "person/by-age").permitAll()
                        .requestMatchers(HttpMethod.GET, "person/by-city").hasAuthority(Authority.READ_ADDRESS)
                        .requestMatchers(HttpMethod.GET, "person/**").hasAuthority(Authority.READ_ALL)
                        .requestMatchers(HttpMethod.PUT, "person/").hasAuthority(Authority.UPDATE_ONE)
                        .requestMatchers(HttpMethod.POST, "person/").hasAuthority(Authority.WRITE_MANY)
                        .requestMatchers(HttpMethod.DELETE, "person/**").hasAuthority(Authority.DELETE_ANY)
                        .anyRequest()
                        .permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}