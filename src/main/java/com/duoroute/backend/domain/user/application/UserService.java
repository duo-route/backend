package com.duoroute.backend.domain.user.application;

import com.duoroute.backend.domain.user.api.UserResponse;
import com.duoroute.backend.domain.user.api.UserSignupRequest;
import com.duoroute.backend.domain.user.entity.User;
import com.duoroute.backend.domain.user.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse signup(UserSignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        User newUser = User.builder()
            .email(request.email())
            .password(request.password())
            .nickname(request.nickname())
            .build();

        User savedUser = userRepository.save(newUser);

        return UserResponse.from(savedUser);
    }
}
