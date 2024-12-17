package Flame._2.BloodCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  Flame._2.BloodCare.entity.RejectedDonor;
import Flame._2.BloodCare.entity.User;

// RejectedDonor Repository
@Repository
public interface RejectedDonorRepository extends JpaRepository<RejectedDonor, Long> {
    List<RejectedDonor> findAllByUser(User user);

}