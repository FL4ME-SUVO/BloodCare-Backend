package Flame._2.BloodCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Flame._2.BloodCare.entity.ApprovedDonor;

// ApprovedDonor Repository
@Repository
public interface ApprovedDonorRepository extends JpaRepository<ApprovedDonor, Long> {}