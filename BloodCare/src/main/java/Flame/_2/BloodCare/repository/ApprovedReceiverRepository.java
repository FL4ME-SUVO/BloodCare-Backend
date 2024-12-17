package Flame._2.BloodCare.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Flame._2.BloodCare.entity.ApprovedReceiver;
import Flame._2.BloodCare.entity.User;

// ApprovedDonor Repository
@Repository
public interface ApprovedReceiverRepository extends JpaRepository<ApprovedReceiver, Long> {
    List<ApprovedReceiver> findAllByUser(User user);
}

