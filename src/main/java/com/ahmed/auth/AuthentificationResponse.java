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
public class AuthentificationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("user")
    private UserDTO user;
}
