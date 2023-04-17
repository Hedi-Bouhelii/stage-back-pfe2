package com.ahmed.forgot;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Data
@Builder
public class PasswordRecoveryRequest {
    private String email;
}
