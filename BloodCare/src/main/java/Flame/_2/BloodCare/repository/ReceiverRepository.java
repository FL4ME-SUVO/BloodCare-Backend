package Flame._2.BloodCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  Flame._2.BloodCare.entity.Receiver;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
    // Additional query methods can be defined here if needed
}
