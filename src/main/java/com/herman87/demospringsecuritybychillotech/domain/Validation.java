package com.herman87.demospringsecuritybychillotech.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_validation")
public class Validation {

    @Column(name = "c_id")
    private Integer id;
    @Builder.Default
    @Column(name = "c_creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();
    @Column(name = "c_expiration_date")
    private LocalDateTime expirationDate = LocalDateTime.now();
    @Column(name = "c_otp_code")
    private String otpCode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_user", referencedColumnName = "c_id")
    private User user;
}
