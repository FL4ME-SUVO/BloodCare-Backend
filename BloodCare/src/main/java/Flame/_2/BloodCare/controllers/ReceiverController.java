package Flame._2.BloodCare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import Flame._2.BloodCare.entity.Receiver;
import Flame._2.BloodCare.entity.User;
import Flame._2.BloodCare.repository.ReceiverRepository;
import jakarta.servlet.http.HttpSession;


@Controller
public class ReceiverController {

    @Autowired
    private ReceiverRepository receiverRepository;

    @PostMapping("/ReceiverRegistration")
    public RedirectView registerReceiver(
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
            @RequestParam("receivingDate") String receivingDate,
            @RequestParam("desiredBloodType") String desiredBloodType,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("reason") String reason,HttpSession session
    ) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new RedirectView("/login.html");
        }
        // Create a new Receiver object and set its properties
        Receiver receiver = new Receiver();
        receiver.setFirstName(firstName);
        receiver.setMiddleName(middleName);
        receiver.setLastName(lastName);
        receiver.setEmail(email);
        receiver.setMobile(mobile);
        receiver.setAlternativeMobile(alternativeMobile);
        receiver.setDob(dob);
        receiver.setAadhaar(aadhaar);
        receiver.setGender(gender);
        receiver.setAge(age);
        receiver.setWeight(weight);
        receiver.setBloodGroup(bloodGroup);
        receiver.setCity(city);
        receiver.setDistrict(district);
        receiver.setState(state);
        receiver.setPinCode(pinCode);
        receiver.setReceivingDate(receivingDate);
        receiver.setDesiredBloodType(desiredBloodType);
        receiver.setQuantity(quantity);
        receiver.setReason(reason);
        receiver.setUser(user);

        // Save the receiver to the database
        receiverRepository.save(receiver);

        // Redirect to a confirmation or index page after successful registration
        return new RedirectView("/index.html");

    }
}