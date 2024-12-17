package Flame._2.BloodCare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import Flame._2.BloodCare.entity.ApprovedDonor;
import Flame._2.BloodCare.entity.Donor;
import Flame._2.BloodCare.entity.User;
import Flame._2.BloodCare.repository.ApprovedDonorRepository;
import Flame._2.BloodCare.repository.DonorRepository;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class DonorDashboardController {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private ApprovedDonorRepository approvedDonorRepository;



    // Fetch all donor requests made by the logged-in user
    @GetMapping("/donor-requests")
    public List<Donor> getAllDonorRequests(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User not logged in");
        }
        return donorRepository.findAllByUser(user);
    }

    // Fetch approved donor requests for the logged-in user
    @GetMapping("/approved-donors")
    public List<ApprovedDonor> getApprovedDonorRequests(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User not logged in");
        }
        return approvedDonorRepository.findAllByUser(user); // Assuming approved donors are filtered by your logic
    }

    // Update the appointment date for a donor request
    @PostMapping("/update-appointment/{id}")
    public String updateAppointment(@PathVariable Long id, @RequestParam("appointmentDate") String appointmentDate) {
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found"));
        donor.setAppointmentDate(appointmentDate);
        donorRepository.save(donor);
        return "Appointment date updated successfully!";
    }

    @GetMapping("/donor-dashboard")
    public RedirectView showDonorDashboard() {
        return new RedirectView("/donorDashboard.html");
    }
}
