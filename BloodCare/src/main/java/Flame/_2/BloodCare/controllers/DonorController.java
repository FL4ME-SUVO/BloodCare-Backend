package Flame._2.BloodCare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import Flame._2.BloodCare.entity.Donor;
import Flame._2.BloodCare.entity.User;
import Flame._2.BloodCare.repository.DonorRepository;
import jakarta.servlet.http.HttpSession;


@Controller
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;


    @PostMapping("/DonorRegistration")
    public RedirectView registerDonor(
            @RequestParam("firstName") String firstName,
            @RequestParam("middleName") String middleName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("mobile") String mobile,
            @RequestParam("alternativeMobile") String alternativeMobile,
            @RequestParam("dob") String dob,
            @RequestParam("aadhaar") String aadhaar,
            @RequestParam("gender") String gender,
            @RequestParam("age") Integer age,
            @RequestParam("weight") Integer weight,
            @RequestParam("bloodGroup") String bloodGroup,
            @RequestParam("city") String city,
            @RequestParam("district") String district,
            @RequestParam("state") String state,
            @RequestParam("pinCode") Integer pinCode,
            @RequestParam("appointmentDate") String appointmentDate,
            @RequestParam("disease") String disease,
            @RequestParam("donateThroughCamp") String donateThroughCamp,
            HttpSession session) {

        // Get the currently logged-in user from the session
        User user = (User) session.getAttribute("user"); // Assuming the user is stored in session
        
        if (user == null) {
            return new RedirectView("/login.html"); // Redirect to login page if no user found in session
        }

        // Create a new Donor object and associate it with the logged-in user
        Donor donor = new Donor();
        donor.setFirstName(firstName);
        donor.setMiddleName(middleName);
        donor.setLastName(lastName);
        donor.setEmail(email);
        donor.setMobile(mobile);
        donor.setAlternativeMobile(alternativeMobile);
        donor.setDob(dob);
        donor.setAadhaar(aadhaar);
        donor.setGender(gender);
        donor.setAge(age);
        donor.setWeight(weight);
        donor.setBloodGroup(bloodGroup);
        donor.setCity(city);
        donor.setDistrict(district);
        donor.setState(state);
        donor.setPinCode(pinCode);
        donor.setAppointmentDate(appointmentDate);
        donor.setDisease(disease);
        donor.setDonateThroughCamp(donateThroughCamp);
        donor.setUser(user); // Associate the donor with the current user

        // Save the donor to the database
        donorRepository.save(donor);

        // After saving the donor, get the total number of donor requests made by this user
        long donorRequestCount = donorRepository.countByUser(user);

        // Set the donorRequestCount in session or pass it to the view (User page)
        session.setAttribute("donorRequestCount", donorRequestCount);

        // Redirect to the user dashboard
        return new RedirectView("/index.html");

    }
}

