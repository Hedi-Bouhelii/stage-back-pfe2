package com.ahmed.auth;
import com.ahmed.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshResponse {

    @JsonProperty("refresh_token")
    private String refreshToken;
}
