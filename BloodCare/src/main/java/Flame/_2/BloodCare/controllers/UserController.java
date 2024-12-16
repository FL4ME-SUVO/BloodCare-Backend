package Flame._2.BloodCare.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import Flame._2.BloodCare.entity.User;
import Flame._2.BloodCare.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Display Login Page
    @GetMapping("/login")
    public RedirectView showLoginPage() {
        return new RedirectView("/login.html"); // Redirect to static login.html page
    }

    // Sign-in form submission
    @PostMapping("/login")
    public RedirectView login(@RequestParam("email") String email, 
                              @RequestParam("password") String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            return new RedirectView("/index.html"); // Redirect to index.html on successful login
        } else {
            return new RedirectView("/login.html?error=true"); // Redirect to login.html with error
        }
    }

    // Display Index Page
    @GetMapping("/index")
    public RedirectView showIndexPage() {
        return new RedirectView("/index.html"); // Redirect to static index.html page
    }

    // Sign-up form submission
    @PostMapping("/signup")
    public RedirectView signup(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            return new RedirectView("/login.html?error=exists"); // Redirect with error if email exists
        } else {
            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPassword(password);
            userRepository.save(newUser);
            return new RedirectView("/login.html?success=true"); // Redirect to login with success message
        }
    }

}