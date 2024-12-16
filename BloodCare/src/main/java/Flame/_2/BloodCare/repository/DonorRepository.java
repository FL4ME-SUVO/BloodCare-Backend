package Flame._2.BloodCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  Flame._2.BloodCare.entity.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    // Additional query methods (if needed) can be defined here
}