package Flame._2.BloodCare.controllers;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import Flame._2.BloodCare.entity.User;
import Flame._2.BloodCare.repository.UserRepository;
import Flame._2.BloodCare.util.PasswordHashUtil;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public RedirectView showLoginPage() {
        return new RedirectView("/login.html");
    }

@PostMapping("/login")
@ResponseBody
public Map<String, String> login(@RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 HttpSession session) {
    User user = userRepository.findByEmail(email);
    Map<String, String> response = new HashMap<>();
    if (user != null && user.getPassword().equals(PasswordHashUtil.hashPassword(password))) {
        session.setAttribute("user", user);
        response.put("status", "success");
        response.put("firstName", user.getFirstName());
        response.put("redirectUrl", "/index.html");
    } else {
        response.put("status", "error");
        response.put("message", "Invalid email or password");
    }
    return response;
}


    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/index.html");
    }

    @PostMapping("/signup")
    public RedirectView signup(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        if (userRepository.findByEmail(email) != null) {
            return new RedirectView("/login.html?error=exists");
        }

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(PasswordHashUtil.hashPassword(password));
        userRepository.save(newUser);

        return new RedirectView("/login.html?success=true");
    }

    @GetMapping("/user-dashboard")
    public RedirectView showUserDashboard(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new RedirectView("/login.html");
        }
        return new RedirectView("/userDashboard.html");
    }

   // Get User Profile
   @GetMapping("/profile")
   @ResponseBody
   public Map<String, Object> getUserProfile(HttpSession session) {
       User user = (User) session.getAttribute("user");
       Map<String, Object> response = new HashMap<>();
       if (user != null) {
           response.put("status", "success");
           response.put("data", user);
           System.out.println("hello");
       } else {
           response.put("status", "error");
           response.put("message", "User not logged in.");
       }
       return response;
   }

   // Update User Profile
   @PostMapping("/update")
   @ResponseBody
   public Map<String, String> updateUserProfile(@RequestParam Map<String, String> userDetails, HttpSession session) {
       User user = (User) session.getAttribute("user");
       Map<String, String> response = new HashMap<>();

       if (user != null) {
           // Update user attributes
           user.setFirstName(userDetails.get("firstName"));
           user.setLastName(userDetails.get("lastName"));
           user.setEmail(userDetails.get("email"));

           // Password should be hashed before saving
           String newPassword = userDetails.get("password");
           if (!newPassword.isEmpty()) {
               user.setPassword(PasswordHashUtil.hashPassword(newPassword));
           }

           userRepository.save(user);

           response.put("status", "success");
           response.put("message", "Profile updated successfully.");
       } else {
           response.put("status", "error");
           response.put("message", "User not logged in.");
       }

       return response;
   }
}