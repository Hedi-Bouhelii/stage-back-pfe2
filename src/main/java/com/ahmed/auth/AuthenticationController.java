package com.ahmed.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ahmed.config.JwtService;
import com.ahmed.user.Role;
import com.ahmed.user.User;
import com.ahmed.user.UserDTO;
import com.ahmed.user.UserRepository;
import com.ahmed.user.UserService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserRepository repository;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  private final UserService service;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    if (repository.existsByUsername(request.getUsername())) {
      return ResponseEntity.badRequest().body("Username already Taken");
    }
    if (repository.existsByEmail(request.getEmail())) {
      return ResponseEntity.badRequest().body("Email already Taken");
    }
    if (repository.existsByCin(request.getCin())) {
      return ResponseEntity.badRequest().body("invalid CIN");
    }
    if (repository.existsByPhone(request.getPhone())) {
      return ResponseEntity.badRequest().body("invalid Phone Number");
    }
      User user = User.builder()
              .firstName(request.getFirstName())
              .lastName(request.getLastName())
              .username(request.getUsername())
              .cin(request.getCin())
              .phone(request.getPhone())
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword()))
              .role(Role.USER)
              .build();
      var savedUser = repository.save(user);
      var jwtToken = jwtService.generateToken(user);
      service.saveUserToken(savedUser, jwtToken);
      return ResponseEntity.ok()
              .body("User Registred Successfully");
  }

  @PostMapping("/login")
  public AuthentificationResponse authenticate(@RequestBody AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    User user = repository.findUserByEmail(request.getEmail());
    var jwtToken = jwtService.generateToken(user);
    service.revokeAllUserTokens(user);
    service.saveUserToken(user,jwtToken);
    return new AuthentificationResponse(jwtToken,new UserDTO(
      user.getId(),
      user.getUsername(),
      user.getEmail(),
      user.getPhone(),
      user.getFirstName(),
      user.getLastName(),
      user.getCin(),
      user.getRole()));
  
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
