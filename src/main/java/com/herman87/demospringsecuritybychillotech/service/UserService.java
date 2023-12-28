package com.herman87.demospringsecuritybychillotech.service;

import com.herman87.demospringsecuritybychillotech.DTO.ValidationData;
import com.herman87.demospringsecuritybychillotech.domain.User;
import com.herman87.demospringsecuritybychillotech.domain.Validation;
import com.herman87.demospringsecuritybychillotech.repository.UserRepository;
import com.herman87.demospringsecuritybychillotech.repository.ValidationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

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
}
