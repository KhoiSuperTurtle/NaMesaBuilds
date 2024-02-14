package com.example.namesa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private DataSource dataSource;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/reg/**", "/list/**", "/login/**", "/profile").permitAll()
                        .requestMatchers("addrecipe**", "myrecipes").hasAuthority("Author")
                        .requestMatchers("/users**","dishcategories**", "dishtypes", "editroles").hasAuthority("Admin")
                        .requestMatchers("recipelist", "addlicense", "favourites").hasAuthority("Guest")
                        .requestMatchers("/list/**", "recipe").authenticated()
                        .anyRequest().permitAll()

                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {

            auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                    .usersByUsernameQuery("select visitor_login as username, visitor_password as password, 'true' from visitor where visitor_login=?")
                    .authoritiesByUsernameQuery("select visitor_login as username, 'Visitor' from visitor where visitor_login=?");


    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
