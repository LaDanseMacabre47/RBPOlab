package com.example.cinema.repository;

import com.example.cinema.entity.UserSession;
import com.example.cinema.entity.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    Optional<UserSession> findByRefreshToken(String refreshToken);
    List<UserSession> findByUserIdAndStatus(Long userId, SessionStatus status);
}
