package Flame._2.BloodCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Flame._2.BloodCare.entity.BloodAvailability;
import Flame._2.BloodCare.entity.BloodAvailability.BloodAvailabilityId;



public interface BloodAvailabilityRepository extends JpaRepository<BloodAvailability, BloodAvailabilityId> {}