package com.janggoback.tripwith.sido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.janggoback.tripwith.sido.domain.Gugun;
import com.janggoback.tripwith.sido.domain.Sido;

public interface SidoRepository extends JpaRepository<Sido, Long>{
	List<Sido> findAll();
	Optional<Sido> findBySidoCode(Long SidoCode);
}