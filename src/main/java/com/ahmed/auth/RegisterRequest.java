package com.ahmed.auth;

import lombok.*;



@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstName;

  private String lastName;

  private String username;

  private int cin;

  private String email;

  private int phone;

  private String password;

}
