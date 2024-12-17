package Flame._2.BloodCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Flame._2.BloodCare.entity.ApprovedDonor;
import Flame._2.BloodCare.entity.User;

// ApprovedDonor Repository
@Repository
public interface ApprovedDonorRepository extends JpaRepository<ApprovedDonor, Long> {
    List<ApprovedDonor> findAllByUser(User user);
}