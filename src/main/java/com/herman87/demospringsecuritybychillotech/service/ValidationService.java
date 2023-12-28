package com.herman87.demospringsecuritybychillotech.service;

import com.herman87.demospringsecuritybychillotech.domain.User;
import com.herman87.demospringsecuritybychillotech.domain.Validation;
import com.herman87.demospringsecuritybychillotech.repository.ValidationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationService {

    private final ValidationRepository validationRepository;

    @Transactional
    public int createValidation(User user) {
        Random random = new Random();
        int nextInt = random.nextInt(999999);
        String otpCode = String.format("%06d", nextInt);
        log.info("This is the otp code generated :" + otpCode);

        Validation validation = Validation.builder()
                .expirationDate(LocalDateTime.now().plusMinutes(5L))
                .user(user)
                .otpCode(otpCode)
                .build();
        return validationRepository.save(validation).getId();
    }
}
