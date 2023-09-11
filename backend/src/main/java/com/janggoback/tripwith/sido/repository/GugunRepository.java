package com.janggoback.tripwith.sido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.janggoback.tripwith.sido.domain.Gugun;
import com.janggoback.tripwith.sido.domain.Sido;

public interface GugunRepository extends JpaRepository<Gugun, Long> {
	List<Gugun> findBySido(Sido sido);
}
