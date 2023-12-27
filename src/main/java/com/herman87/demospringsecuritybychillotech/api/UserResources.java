package com.herman87.demospringsecuritybychillotech.api;

import com.herman87.demospringsecuritybychillotech.domain.User;
import com.herman87.demospringsecuritybychillotech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserResources {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/public")
    public ResponseEntity<String> helloEveryOne() {
        return ResponseEntity.ok("hello world");
    }

    @GetMapping("/allow/user")
    public ResponseEntity<String> helloUser() {
        return ResponseEntity.ok("hello user");
    }

    @GetMapping("/allow/admin")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("hello admin");
    }
}
