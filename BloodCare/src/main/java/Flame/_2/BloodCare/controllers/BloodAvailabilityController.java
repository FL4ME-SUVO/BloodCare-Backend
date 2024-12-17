package Flame._2.BloodCare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Flame._2.BloodCare.entity.BloodAvailability;
import Flame._2.BloodCare.entity.BloodAvailability.BloodAvailabilityId;
import Flame._2.BloodCare.repository.BloodAvailabilityRepository;



@RestController
@RequestMapping("/api/blood-availability")
@CrossOrigin(origins = "*") // Allow frontend cross-origin requests
public class BloodAvailabilityController {

    @Autowired
    private BloodAvailabilityRepository repository;

    // 1. Fetch all records
    @GetMapping("/all")
    public ResponseEntity<List<BloodAvailability>> getAllAvailability() {
        List<BloodAvailability> records = repository.findAll();
        return ResponseEntity.ok(records);
    }

    // 2. Check specific availability
    @GetMapping("/{bloodGroup}/{componentType}")
    public ResponseEntity<?> checkAvailability(@PathVariable String bloodGroup, @PathVariable String componentType) {
        Optional<BloodAvailability> availability = repository.findById(new BloodAvailabilityId(bloodGroup, componentType));
        if (availability.isPresent() && availability.get().getUnitsAvailable() > 0) {
            return ResponseEntity.ok("Available: " + availability.get().getUnitsAvailable() + " units of " + componentType + " for " + bloodGroup);
        }
        return ResponseEntity.status(404).body("Not Available");
    }

}