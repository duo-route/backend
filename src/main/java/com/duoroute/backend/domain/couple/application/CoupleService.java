package com.duoroute.backend.domain.couple.application;

import com.duoroute.backend.domain.couple.api.CoupleMatchRequest;
import com.duoroute.backend.domain.couple.api.CoupleResponse;
import com.duoroute.backend.domain.couple.entity.Couple;
import com.duoroute.backend.domain.couple.infra.CoupleRepository;
import com.duoroute.backend.domain.user.entity.User;
import com.duoroute.backend.domain.user.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoupleService {

    private final CoupleRepository coupleRepository;
    private final UserRepository userRepository; // User 도메인 정보 접근

    @Transactional
    public CoupleResponse matchCouple(CoupleMatchRequest request) {
        // 1. 초대받은 사람 (나) 조회
        User me = userRepository.findById(request.myUserId())
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 2. 초대 코드로 상대방(파트너) 조회
        User partner = userRepository.findByInviteCode(request.inviteCode())
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 초대 코드입니다."));

        // 3. 예외 처리: 이미 커플인지 확인
        if (me.getCoupleId() != null || partner.getCoupleId() != null) {
            throw new IllegalStateException("이미 커플로 등록된 사용자입니다.");
        }

        // 4. 예외 처리: 자기 자신의 코드를 입력했는지 확인
        if (me.getId().equals(partner.getId())) {
            throw new IllegalArgumentException("자기 자신의 초대 코드는 사용할 수 없습니다.");
        }

        // 5. 커플 테이블에 데이터 생성
        Couple newCouple = Couple.builder().build();
        Couple savedCouple = coupleRepository.save(newCouple);

        // 6. 양쪽 유저에게 커플 ID 등록 (JPA의 Dirty Checking 덕분에 save 안 해도 DB에 반영됨)
        me.connectCouple(savedCouple.getId());
        partner.connectCouple(savedCouple.getId());

        return new CoupleResponse(savedCouple.getId(), savedCouple.getStatus());
    }
}
