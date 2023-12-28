package com.herman87.demospringsecuritybychillotech.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_validation")
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Integer id;
    @Builder.Default
    @Column(name = "c_creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();
    @Column(name = "c_expiration_date")
    private LocalDateTime expirationDate;
    @Column(name = "c_otp_code")
    private String otpCode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_user", referencedColumnName = "c_id")
    private User user;
}
