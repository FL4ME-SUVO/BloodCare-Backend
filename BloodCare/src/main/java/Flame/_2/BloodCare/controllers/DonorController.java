package Flame._2.BloodCare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import Flame._2.BloodCare.entity.Donor;
import Flame._2.BloodCare.repository.DonorRepository;

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
            @RequestParam("donateThroughCamp") String donateThroughCamp
    ) {
        // Create a new Donor object and set its properties
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

        // Save the donor to the database
        donorRepository.save(donor);

        // Redirect to the index page after successful registration
        return new RedirectView("/index");
    }
}
