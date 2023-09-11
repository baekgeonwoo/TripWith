package com.janggoback.tripwith.triprequest.repository;

import com.janggoback.tripwith.triprequest.domain.TripRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripRequestRepository extends JpaRepository<TripRequest, Long>{
	Optional<TripRequest> findById(Long id);
    void deleteById(Long id);
    List<TripRequest> findByTripId(Long tripId);
    List<TripRequest> findByRequesterEmail(String requesterEmail);
    Page<TripRequest> findAll(Pageable pageable);
    Optional<TripRequest> findByTripIdAndRequesterId(Long tripId, Long memberId);
}
