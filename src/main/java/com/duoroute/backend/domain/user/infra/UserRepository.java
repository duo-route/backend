package com.duoroute.backend.domain.user.infra;

import com.duoroute.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    java.util.Optional<User> findByInviteCode(String inviteCode);
}
