package Flame._2.BloodCare.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Flame._2.BloodCare.entity.User;
import Flame._2.BloodCare.repository.UserRepository;
import Flame._2.BloodCare.util.PasswordHashUtil;

@RestController
@RequestMapping("/api/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    private Map<String, String> otpStorage = new HashMap<>();

    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return "Email not registered";
        }

        String otp = String.format("%06d", new Random().nextInt(999999));
        otpStorage.put(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset OTP");
        message.setText("Dear " + user.getFirstName() + ",\n\nYour OTP for password reset is: " + otp +
                        "\n\nThis OTP is valid for 5 minutes.\n\nBest regards,\nBlood Bank Management System Team");
        mailSender.send(message);

        return "OTP sent to your email";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        if (otp.equals(otpStorage.get(email))) {
            return "OTP verified";
        } else {
            return "Invalid OTP or OTP expired";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "User not found";
        }

        user.setPassword(PasswordHashUtil.hashPassword(newPassword)); // Hash the new password
        userRepository.save(user);
        otpStorage.remove(email);

        return "Password updated successfully";
    }
}
