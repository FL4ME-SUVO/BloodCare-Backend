package Flame._2.BloodCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  Flame._2.BloodCare.entity.RejectedDonor;

// RejectedDonor Repository
@Repository
public interface RejectedDonorRepository extends JpaRepository<RejectedDonor, Long> {}