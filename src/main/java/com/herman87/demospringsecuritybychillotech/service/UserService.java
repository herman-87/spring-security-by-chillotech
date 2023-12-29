package com.herman87.demospringsecuritybychillotech.service;

import com.herman87.demospringsecuritybychillotech.DTO.ValidationDTO;
import com.herman87.demospringsecuritybychillotech.domain.User;
import com.herman87.demospringsecuritybychillotech.domain.Validation;
import com.herman87.demospringsecuritybychillotech.repository.UserRepository;
import com.herman87.demospringsecuritybychillotech.repository.ValidationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;
    private final ValidationRepository validationRepository;

    @Transactional
    public int createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        validationService.createValidation(createdUser);
        return createdUser.getId();
    }

    public void activateUser(ValidationDTO validationData) {

        Validation validation = validationRepository
                .findByIdAndOtpCode(validationData.userId(), validationData.otpCode())
                .orElseThrow(() -> new Error("Validation Not Found"));
        userRepository.save(validation.getUser().activate());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userNotFound = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        log.info("inside userService {} {}", userNotFound.getUsername(), userNotFound.getPassword());

        return userNotFound;
    }
}
