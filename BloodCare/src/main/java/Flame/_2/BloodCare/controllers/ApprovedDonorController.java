package Flame._2.BloodCare.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Flame._2.BloodCare.entity.ApprovedDonor;
import Flame._2.BloodCare.entity.Donor;
import Flame._2.BloodCare.entity.RejectedDonor;
import Flame._2.BloodCare.repository.ApprovedDonorRepository;
import Flame._2.BloodCare.repository.DonorRepository;
import Flame._2.BloodCare.repository.RejectedDonorRepository;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ApprovedDonorController {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private ApprovedDonorRepository approvedDonorRepository;

    @Autowired
    private RejectedDonorRepository rejectedDonorRepository;

    // Get all pending donor requests
    @GetMapping("/pending-donor-requests")
    public List<Donor> getPendingDonorRequests() {
        return donorRepository.findAll();
    }

    // Approve a donor request
    @PostMapping("/approve-donor/{id}")
    public String approveDonor(@PathVariable Long id) {
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        ApprovedDonor approvedDonor = new ApprovedDonor();
        approvedDonor.setFirstName(donor.getFirstName());
        approvedDonor.setMiddleName(donor.getMiddleName());
        approvedDonor.setLastName(donor.getLastName());
        approvedDonor.setEmail(donor.getEmail());
        approvedDonor.setMobile(donor.getMobile());
        approvedDonor.setAlternativeMobile(donor.getAlternativeMobile());
        approvedDonor.setDob(donor.getDob());
        approvedDonor.setAadhaar(donor.getAadhaar());
        approvedDonor.setGender(donor.getGender());
        approvedDonor.setAge(donor.getAge());
        approvedDonor.setWeight(donor.getWeight());
        approvedDonor.setBloodGroup(donor.getBloodGroup());
        approvedDonor.setCity(donor.getCity());
        approvedDonor.setDistrict(donor.getDistrict());
        approvedDonor.setState(donor.getState());
        approvedDonor.setPinCode(donor.getPinCode());
        approvedDonor.setAppointmentDate(donor.getAppointmentDate());
        approvedDonor.setDisease(donor.getDisease());
        approvedDonor.setDonateThroughCamp(donor.getDonateThroughCamp());
        approvedDonor.setApprovalDate(LocalDateTime.now());

        approvedDonorRepository.save(approvedDonor);
        donorRepository.deleteById(id);

        return "Donor approved successfully!";
    }

    // Reject a donor request
    @PostMapping("/reject-donor/{id}")
    public String rejectDonor(@PathVariable Long id) {
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        RejectedDonor rejectedDonor = new RejectedDonor();
        rejectedDonor.setFirstName(donor.getFirstName());
        rejectedDonor.setMiddleName(donor.getMiddleName());
        rejectedDonor.setLastName(donor.getLastName());
        rejectedDonor.setEmail(donor.getEmail());
        rejectedDonor.setMobile(donor.getMobile());
        rejectedDonor.setAlternativeMobile(donor.getAlternativeMobile());
        rejectedDonor.setDob(donor.getDob());
        rejectedDonor.setAadhaar(donor.getAadhaar());
        rejectedDonor.setGender(donor.getGender());
        rejectedDonor.setAge(donor.getAge());
        rejectedDonor.setWeight(donor.getWeight());
        rejectedDonor.setBloodGroup(donor.getBloodGroup());
        rejectedDonor.setCity(donor.getCity());
        rejectedDonor.setDistrict(donor.getDistrict());
        rejectedDonor.setState(donor.getState());
        rejectedDonor.setPinCode(donor.getPinCode());
        rejectedDonor.setAppointmentDate(donor.getAppointmentDate());
        rejectedDonor.setDisease(donor.getDisease());
        rejectedDonor.setDonateThroughCamp(donor.getDonateThroughCamp());
        rejectedDonor.setRejectionDate(LocalDateTime.now());

        rejectedDonorRepository.save(rejectedDonor);
        donorRepository.deleteById(id);

        return "Donor rejected successfully!";
    }

    // Get all approved donor requests
    @GetMapping("/approved-donors")
    public List<ApprovedDonor> getApprovedDonors() {
        return approvedDonorRepository.findAll();
    }

    
    // Get all rejected donor requests
    @GetMapping("/rejected-donors")
    public List<RejectedDonor> getRejectedDonors() {
        return rejectedDonorRepository.findAll();
    }


}
