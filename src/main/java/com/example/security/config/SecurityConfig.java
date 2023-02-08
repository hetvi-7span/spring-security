package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     /*   http

                .authorizeHttpRequests((auth) -> auth
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();*/

        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/users/**").hasRole("USER")
                        .requestMatchers("/test/**").permitAll()
                        .anyRequest().authenticated()
                ).httpBasic(withDefaults());

        // To unable form login and it can be customized using our own html or jsp pages
        /*. formLogin();*/

        return http.build();

    }


    //create inMemory user which can be used to Log in the application
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        //encoding password
        UserDetails user = User.withUsername("password")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        //using default authentication which is not recommending for production
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("hetvi")
                .password("hetvi")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, user2);
    }

}

