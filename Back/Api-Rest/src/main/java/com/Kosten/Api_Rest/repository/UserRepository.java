package com.Kosten.Api_Rest.repository;

import com.Kosten.Api_Rest.dto.user.UserResponseDto;
import com.Kosten.Api_Rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.departures")
    List<User> findAllWithDepartures();

    List<User> findAllByIsActiveTrue();

    Optional<User> findByResetToken(String resetToken);
}

