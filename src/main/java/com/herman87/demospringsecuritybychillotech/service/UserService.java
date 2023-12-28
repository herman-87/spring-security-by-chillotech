package com.herman87.demospringsecuritybychillotech.service;

import com.herman87.demospringsecuritybychillotech.DTO.ValidationData;
import com.herman87.demospringsecuritybychillotech.domain.User;
import com.herman87.demospringsecuritybychillotech.domain.Validation;
import com.herman87.demospringsecuritybychillotech.repository.UserRepository;
import com.herman87.demospringsecuritybychillotech.repository.ValidationRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@NoArgsConstructor(force = true)
@Builder
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

    public void activateUser(ValidationData validationData) {

        Validation validation = validationRepository
                .findByIdAndOtpCode(validationData.getUserId(), validationData.getOtpCode())
                .orElseThrow(() -> new Error("Validation Not Found"));
        userRepository.save(validation.getUser().activate());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
