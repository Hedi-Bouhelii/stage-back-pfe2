package com.ahmed.user;

import com.ahmed.auth.RefreshResponse;
import com.ahmed.config.JwtService;
import com.ahmed.token.Token;
import com.ahmed.token.TokenRepository;
import com.ahmed.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    
    private final UserRepository repository;

    private final TokenRepository tokenRepository;

    private final JwtService jwtService;

    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
        tokenRepository.save(token);
      }
    
      public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
          return;
        validUserTokens.forEach(token -> {
          token.setExpired(true);
          token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
      }
    
      public void refreshToken(
              HttpServletRequest request,
              HttpServletResponse response
      ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
          return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
          var user = this.repository.findByEmail(userEmail)
                  .orElseThrow();
          if (jwtService.isTokenValid(refreshToken, user)) {
            var accessToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, accessToken);
            var authResponse = RefreshResponse.builder()
            .refreshToken(refreshToken)
            .build();
             new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
          }
        }
      }

    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
           User user = repository.findUserByEmail(email);
            user.setResetPasswordToken(token);
            repository.save(user);
    }
   
  public User getByResetPasswordToken(String token) {
      return repository.findByResetPasswordToken(token);
  }   
  public void updatePassword(User user, String newPassword) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String encodedPassword = passwordEncoder.encode(newPassword);
      user.setPassword(encodedPassword);
      user.setResetPasswordToken(null);
      repository.save(user);
  }
    

    public List<UserDTO> getAllUsers() {
        List<User> users = repository.findAllUsers();
        return users.stream()
                .map(user -> new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getFirstName(),
                user.getLastName(),
                user.getCin(),
                user.getRole()))
                .collect(Collectors.toList());
    }

}
