package Flame._2.BloodCare.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Flame._2.BloodCare.entity.ApprovedReceiver;
import Flame._2.BloodCare.entity.Receiver;
import Flame._2.BloodCare.entity.RejectedReceiver;
import Flame._2.BloodCare.repository.ApprovedReceiverRepository;
import Flame._2.BloodCare.repository.ReceiverRepository;
import Flame._2.BloodCare.repository.RejectedReceiverRepository;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ApprovedReceiverController {

    @Autowired
    private ReceiverRepository receiverRepository;

    @Autowired
    private ApprovedReceiverRepository approvedReceiverRepository;

    @Autowired
    private RejectedReceiverRepository rejectedReceiverRepository;

    // Get all pending receiver requests
    @GetMapping("/pending-receiver-requests")
    public List<Receiver> getPendingReceiverRequests() {
        return receiverRepository.findAll();
    }

    // Approve a receiver request
    @PostMapping("/approve-receiver/{id}")
    public String approveReceiver(@PathVariable Long id) {
        Receiver receiver = receiverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        ApprovedReceiver approvedReceiver = new ApprovedReceiver();
        approvedReceiver.setFirstName(receiver.getFirstName());
        approvedReceiver.setMiddleName(receiver.getMiddleName());
        approvedReceiver.setLastName(receiver.getLastName());
        approvedReceiver.setEmail(receiver.getEmail());
        approvedReceiver.setMobile(receiver.getMobile());
        approvedReceiver.setAlternativeMobile(receiver.getAlternativeMobile());
        approvedReceiver.setDob(receiver.getDob());
        approvedReceiver.setAadhaar(receiver.getAadhaar());
        approvedReceiver.setGender(receiver.getGender());
        approvedReceiver.setAge(receiver.getAge());
        approvedReceiver.setWeight(receiver.getWeight());
        approvedReceiver.setBloodGroup(receiver.getBloodGroup());
        approvedReceiver.setCity(receiver.getCity());
        approvedReceiver.setDistrict(receiver.getDistrict());
        approvedReceiver.setState(receiver.getState());
        approvedReceiver.setPinCode(receiver.getPinCode());
        approvedReceiver.setReceivingDate(receiver.getReceivingDate());
        approvedReceiver.setDesiredBloodType(receiver.getDesiredBloodType());
        approvedReceiver.setQuantity(receiver.getQuantity());
        approvedReceiver.setReason(receiver.getReason());
        approvedReceiver.setApprovalDate(LocalDateTime.now());

        approvedReceiverRepository.save(approvedReceiver);
        receiverRepository.deleteById(id);

        return "Receiver approved successfully!";
    }

    // Reject a receiver request
    @PostMapping("/reject-receiver/{id}")
    public String rejectReceiver(@PathVariable Long id) {
        Receiver receiver = receiverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        RejectedReceiver rejectedReceiver = new RejectedReceiver();
        rejectedReceiver.setFirstName(receiver.getFirstName());
        rejectedReceiver.setMiddleName(receiver.getMiddleName());
        rejectedReceiver.setLastName(receiver.getLastName());
        rejectedReceiver.setEmail(receiver.getEmail());
        rejectedReceiver.setMobile(receiver.getMobile());
        rejectedReceiver.setAlternativeMobile(receiver.getAlternativeMobile());
        rejectedReceiver.setDob(receiver.getDob());
        rejectedReceiver.setAadhaar(receiver.getAadhaar());
        rejectedReceiver.setGender(receiver.getGender());
        rejectedReceiver.setAge(receiver.getAge());
        rejectedReceiver.setWeight(receiver.getWeight());
        rejectedReceiver.setBloodGroup(receiver.getBloodGroup());
        rejectedReceiver.setCity(receiver.getCity());
        rejectedReceiver.setDistrict(receiver.getDistrict());
        rejectedReceiver.setState(receiver.getState());
        rejectedReceiver.setPinCode(receiver.getPinCode());
        rejectedReceiver.setReceivingDate(receiver.getReceivingDate());
        rejectedReceiver.setDesiredBloodType(receiver.getDesiredBloodType());
        rejectedReceiver.setQuantity(receiver.getQuantity());
        rejectedReceiver.setReason(receiver.getReason());
        rejectedReceiver.setRejectionDate(LocalDateTime.now());

        rejectedReceiverRepository.save(rejectedReceiver);
        receiverRepository.deleteById(id);

        return "Receiver rejected successfully!";
    }

    // Get all approved receiver requests
    @GetMapping("/approved-receivers")
    public List<ApprovedReceiver> getApprovedReceivers() {
        return approvedReceiverRepository.findAll();
    }

    // Get all rejected receiver requests
    @GetMapping("/rejected-receivers")
    public List<RejectedReceiver> getRejectedReceivers() {
        return rejectedReceiverRepository.findAll();
    }
}
