package com.lefortdesigns.furnitarium_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lefortdesigns.furnitarium_backend.services.PersonDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final PersonDetailsService personDetailsService;
        private final JwtFilter jwtFilter;

        @Autowired
        public SecurityConfig(PersonDetailsService personDetailsService, JwtFilter jwtFilter) {
                this.personDetailsService = personDetailsService;
                this.jwtFilter = jwtFilter;
        }

        @Bean
        public AuthenticationManager authManager(HttpSecurity http) throws Exception {
                AuthenticationManagerBuilder authenticationManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);
                authenticationManagerBuilder.userDetailsService(personDetailsService)
                                .passwordEncoder(getPasswordEncoder());
                return authenticationManagerBuilder.build();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .csrf(csrf -> csrf
                                                .disable())
                                .authorizeHttpRequests(request -> request
                                                .requestMatchers("/auth/login", "/error", "/process_login",
                                                                "/auth/registration")
                                                .permitAll()
                                                .anyRequest()
                                                .authenticated())
                                .formLogin(login -> login
                                                .loginPage("/auth/login")
                                                .loginProcessingUrl("/process_login")
                                                .defaultSuccessUrl("/hello")
                                                .failureForwardUrl("/auth/login?error"))
                                .logout(logout -> logout
                                                .logoutUrl("/logout"))
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();

        }

        @Bean
        public PasswordEncoder getPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        // @Bean
        // @Override
        // public AuthenticationManager authenticationManagerBean() throws Exception {
        // return super.authenticationManagerBean();
        // }
}
