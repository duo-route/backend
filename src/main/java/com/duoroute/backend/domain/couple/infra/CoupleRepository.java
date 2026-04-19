package com.duoroute.backend.domain.couple.infra;

import com.duoroute.backend.domain.couple.entity.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoupleRepository extends JpaRepository<Couple, Long> {
}
