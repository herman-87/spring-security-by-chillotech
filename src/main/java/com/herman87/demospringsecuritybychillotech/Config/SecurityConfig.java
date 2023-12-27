package com.herman87.demospringsecuritybychillotech.Config;

import com.herman87.demospringsecuritybychillotech.domain.RoleName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers(
                                    HttpMethod.GET,
                                    "/allow/user"
                            ).hasRole(RoleName.USER.name())
                            .requestMatchers(
                                    HttpMethod.GET,
                                    "/allow/admin"
                            ).hasRole(RoleName.ADMIN.name())
                            .requestMatchers(
                                    HttpMethod.POST,
                                    "/sign-up"
                            ).permitAll()
                            .requestMatchers(
                                    HttpMethod.GET,
                                    "/public"
                            ).permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer ignoreResources() {
        return web -> web.ignoring().requestMatchers("/h2-console/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user@gmail.com")
                        .password(passwordEncoder().encode("1234"))
                        .roles(RoleName.USER.name())
                        .build()
        );
    }
}
