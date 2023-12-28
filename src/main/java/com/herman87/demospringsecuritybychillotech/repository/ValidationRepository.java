package com.herman87.demospringsecuritybychillotech.repository;

import com.herman87.demospringsecuritybychillotech.domain.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Integer> {

    Optional<Validation> findByIdAndOtpCode(int userId, String otpCode);
}
