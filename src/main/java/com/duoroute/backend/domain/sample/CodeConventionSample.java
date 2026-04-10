package com.duoroute.backend.domain.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class CodeConventionSample {
    // [Constant] UPPER_SNAKE_CASE 사용
    private static final String DEFAULT_STATUS = "ACTIVE";

    /**
     * [Method Name] camelCase 사용, 동사로 시작
     * 설명: 사용자 목록을 조회하는 API
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUserList() {
        // [Variable Name] camelCase 사용
        String currentStatus = DEFAULT_STATUS;

        // 지역 변수 선언 시 타입 추론(var)은 Java 10+ 부터 가능하지만,
        // 협업 시 명확한 타입 명시를 선호하는 팀이 많으므로 프로젝트 규칙을 따릅니다.
        List<UserResponse> users = List.of(
            new UserResponse(1L, "테스터", currentStatus)
        );

        return ResponseEntity.ok(users);
    }

    /**
     * [Record] Java 16+ 불변 데이터 객체(DTO)로 record 적극 활용
     * 설명: 응답용 DTO
     */
    public record UserResponse(
        Long id,       // 필드명도 camelCase
        String name,
        String status
    ) {}
}
