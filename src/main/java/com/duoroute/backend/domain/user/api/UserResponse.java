package com.duoroute.backend.domain.user.api;

import com.duoroute.backend.domain.user.entity.User;

public record UserResponse(
    Long id,
    String email,
    String nickname,
    String inviteCode
) {
    public static UserResponse from(User user) {
        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getNickname(),
            user.getInviteCode()
        );
    }
}
