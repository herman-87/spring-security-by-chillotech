package com.herman87.demospringsecuritybychillotech.api;

import com.herman87.demospringsecuritybychillotech.DTO.AuthenticationDTO;
import com.herman87.demospringsecuritybychillotech.DTO.ValidationDTO;
import com.herman87.demospringsecuritybychillotech.domain.User;
import com.herman87.demospringsecuritybychillotech.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserResources {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PostMapping("/activate/user")
    public ResponseEntity<Void> activateUser(@RequestBody ValidationDTO validationData) {

        userService.activateUser(validationData);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, String>> connexion(@RequestBody AuthenticationDTO authenticationDTO) {


        String username = authenticationDTO.username();
        String password = authenticationDTO.password();
        log.info("username password {} {}: ", username, password);
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate( authentication);

        log.info("Current user is authenticate ?" + authentication.isAuthenticated());

        return ResponseEntity.accepted().build();
    }
}
