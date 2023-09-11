package com.janggoback.tripwith.attraction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.janggoback.tripwith.attraction.domain.Attraction;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
	Page<Attraction> findByNameContaining(String name, Pageable pageable);
	Page<Attraction> findByAreaCodeAndSigunguCode(Long sidoCode, Long gugunCode, Pageable pageable);
}
