package com.duoroute.backend.domain.user.api;

public record UserSignupRequest(
    String email,
    String password,
    String nickname
) {
}
