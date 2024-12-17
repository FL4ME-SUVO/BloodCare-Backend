package Flame._2.BloodCare.repository;

import  java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Flame._2.BloodCare.entity.RejectedReceiver;
import Flame._2.BloodCare.entity.User;


@Repository
public interface RejectedReceiverRepository extends JpaRepository<RejectedReceiver, Long> {
    List<RejectedReceiver> findAllByUser(User user);
}
