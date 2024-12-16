package Flame._2.BloodCare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Flame._2.BloodCare.entity.User;

public interface UserRepository extends JpaRepository<User ,Long>{

    Optional<User> findByEmail(String email);
}