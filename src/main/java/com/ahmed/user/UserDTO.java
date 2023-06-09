package com.ahmed.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private int phone;
    private String firstName;
    private String lastName;
    private int cin;
    private Role role;

    public UserDTO() {
        super();
    }
}