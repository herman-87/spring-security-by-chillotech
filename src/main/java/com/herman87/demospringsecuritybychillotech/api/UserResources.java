package com.herman87.demospringsecuritybychillotech.api;

import com.herman87.demospringsecuritybychillotech.DTO.ValidationData;
import com.herman87.demospringsecuritybychillotech.domain.User;
import com.herman87.demospringsecuritybychillotech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserResources {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PostMapping("/activate/user")
    public ResponseEntity<Void> activateUser(@RequestBody ValidationData validationData) {

        userService.activateUser(validationData);
        return ResponseEntity.accepted().build();
    }
}
