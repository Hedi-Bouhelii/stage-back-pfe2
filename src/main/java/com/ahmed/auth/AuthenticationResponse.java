package com.ahmed.auth;


import lombok.AllArgsConstructor;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationResponse {
  private Long id;
  private String username;
  private String email;
  private int phone;
  private String firstName;
  private String lastName;
  private int cin;
  private String role;


}
