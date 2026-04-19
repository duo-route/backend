package com.duoroute.backend.domain.couple.api;

import com.duoroute.backend.domain.couple.application.CoupleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/couples")
@RequiredArgsConstructor
public class CoupleController {

    private final CoupleService coupleService;

    @PostMapping("/match")
    public ResponseEntity<CoupleResponse> match(@RequestBody CoupleMatchRequest request) {
        CoupleResponse response = coupleService.matchCouple(request);
        return ResponseEntity.ok(response);
    }
}
