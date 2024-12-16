package Flame._2.BloodCare.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Flame._2.BloodCare.entity.Receiver;
import Flame._2.BloodCare.entity.User;


@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
    // Additional query methods can be defined here if needed
    long countByUser(User user); // Count total requests by user
    List<Receiver> findAllByUser(User user); // Find all requests by user
}

