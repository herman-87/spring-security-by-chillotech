package com.herman87.demospringsecuritybychillotech.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationData {
    private int userId;
    private String otpCode;
}
