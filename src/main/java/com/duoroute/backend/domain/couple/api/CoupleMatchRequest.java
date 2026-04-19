package com.duoroute.backend.domain.couple.api;

public record CoupleMatchRequest(
    Long myUserId,     // 내 유저 ID (추후 JWT 토큰으로 대체)
    String inviteCode  // 상대방이 준 초대 코드
) {
}
