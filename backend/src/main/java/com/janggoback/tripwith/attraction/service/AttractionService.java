package com.janggoback.tripwith.attraction.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.janggoback.tripwith.attraction.domain.Attraction;
import com.janggoback.tripwith.trip.domain.Trip;

public interface AttractionService {
    Attraction getAttraction(Long id);
    Page<Attraction> getAttractionList(Pageable pageable);
    
    Page<Attraction> searchAttractionByName(String keyword, Pageable pageable);
	Page<Attraction> searchAttractionByGugunCode(Long sidoCode, Long gugunCode, Pageable pageable);
}
