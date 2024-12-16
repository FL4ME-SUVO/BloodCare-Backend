package Flame._2.BloodCare.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Flame._2.BloodCare.entity.ApprovedReceiver;

// ApprovedDonor Repository
@Repository
public interface ApprovedReceiverRepository extends JpaRepository<ApprovedReceiver, Long> {}

