package com.ahmed.forgot;

import com.ahmed.user.User;
import com.ahmed.user.UserRepository;
import com.ahmed.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResetPasswordController {
    private final JavaMailSender mailSender;
     
    private final  UserService userService;
    private final UserRepository userRepository;
    @Value("${spring.mail.username}")
    private String sender;

    private  final String API = "http://localhost:8080/api";

    @PostMapping("/forgot")
    public ResponseEntity<?> ResponseEntity(@RequestBody PasswordRecoveryRequest passwordRecoveryRequest) throws
            MailSendException {
                String email = passwordRecoveryRequest.getEmail();

                if(userRepository.existsByEmail(email)) {
                String token = UUID.randomUUID().toString();
                userService.updateResetPasswordToken(token, email);
                String resetPasswordLink = API + "/reset?token=" + token;
        try {
            sendEmail(email, resetPasswordLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new ForgotResponse(token));
    }
    else return ResponseEntity.badRequest().body("There is no account with this email" + email);
    }

    public String sendEmail(String recipientEmail, String link) {
        if(userRepository.existsByEmail(recipientEmail)) {
            String subject = "Someone requested that the password be reset for the following account:";
            String content = "<html>"+
                    "<body>"+
                    "<p>Hello sir, "+"</p>"+
                    "<br>"+
                    "<p>You have requested to reset your password.</p>"+
                    "<p>Click the link below to change your password:</p>"+
                    "<p><a href=\"" + link + "\">Change my password</a></p>"+
                    "<br>"+
                    "<p>Ignore this email if you do remember your password, or you have not made the request.</p>"+
                    "</body>"+
                    "</html>";
            try {
                // Creating a simple mail message
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                // Setting up necessary details
                mailMessage.setFrom(sender);
                mailMessage.setTo(recipientEmail);
                mailMessage.setText(content);
                mailMessage.setSubject(subject);
                // Sending the mail
                mailSender.send(mailMessage);
            }
            // Catch block to handle the exceptions
            catch (MailAuthenticationException e) {
                return "Error while Sending Mail" + e;
            }
            return "Mail Sent Successfully...";
        }
        else return "Email Does Not exists";

    }

    @PostMapping("/reset")
    public ResponseEntity<?> ResponseEntity(@RequestBody ResetPasswordRequest request, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("token");
        String password = request.getPassword();
        User user = userService.getByResetPasswordToken(token);
        if (user == null) {
            return ResponseEntity.badRequest().body("Error");
        } else {
            userService.updatePassword(user, password);
        }
        return ResponseEntity.ok().body("Done!");
    }
}
