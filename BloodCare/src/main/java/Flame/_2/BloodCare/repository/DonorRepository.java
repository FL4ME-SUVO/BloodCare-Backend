package Flame._2.BloodCare.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  Flame._2.BloodCare.entity.Donor;
import Flame._2.BloodCare.entity.User;


@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    // Additional query methods (if needed) can be defined here
    long countByUser(User user);
    List<Donor> findAllByUser(User user);
}