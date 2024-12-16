package Flame._2.BloodCare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public RedirectView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              HttpSession session) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(PasswordHashUtil.hashPassword(password))) {
            session.setAttribute("user", user);
            return new RedirectView("/index.html");
        } else {
            return new RedirectView("/login.html?error=true");
        }
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/login.html");
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
}