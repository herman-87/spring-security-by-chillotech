package com.herman87.demospringsecuritybychillotech.Config;

import com.herman87.demospringsecuritybychillotech.domain.RoleName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request ->
                                request
                                        .requestMatchers(HttpMethod.POST, "/signup").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/activate/user").permitAll()
                                        .anyRequest()
                                        .authenticated()
                ).build();
    }

    @Bean
    public WebSecurityCustomizer ignoreResources() {return web -> web.ignoring().requestMatchers("/h2-console/**");}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
