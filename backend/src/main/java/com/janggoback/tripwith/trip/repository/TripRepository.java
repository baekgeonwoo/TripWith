package com.janggoback.tripwith.trip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.janggoback.tripwith.attraction.domain.Attraction;
import com.janggoback.tripwith.trip.domain.Trip;

@Transactional
public interface TripRepository extends JpaRepository<Trip, Long>{
	Optional<Trip> findById(Long id);
    List<Trip> findByTitleLike(String searchWord);
    void deleteById(Long id);
    Page<Trip> findAll(Pageable pageable);
    Page<Trip> findByHostId(Long memberId, Pageable pageable);
}
