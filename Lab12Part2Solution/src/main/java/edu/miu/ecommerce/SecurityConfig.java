package edu.miu.ecommerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails bob = User.withDefaultPasswordEncoder()
                .username("bob")
                .password("password")
                .roles("EMPLOYEE", "SALES")
                .build();

        UserDetails mary = User.withDefaultPasswordEncoder()
                .username("mary")
                .password("password")
                .roles("EMPLOYEE", "FINANCE")
                .build();

        UserDetails manager = User.withDefaultPasswordEncoder()
                .username("manager")
                .password("password")
                .roles("MANAGER")
                .build();

        UserDetails topManager = User.withDefaultPasswordEncoder()
                .username("topmanager")
                .password("password")
                .roles("TOPMANAGER")
                .build();

        return new InMemoryUserDetailsManager(bob, mary, manager, topManager);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/shop").permitAll()
                                .requestMatchers("/orders").hasRole("EMPLOYEE")
                                .requestMatchers("/payments").hasRole("FINANCE")
                                .requestMatchers("/manager").hasRole("MANAGER")
                                .requestMatchers("/topmanager").hasRole("TOPMANAGER")
                                .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable()); // for Postman testing

        return http.build();
    }

}
