package Flame._2.BloodCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Flame._2.BloodCare.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email); // Directly return User
}
