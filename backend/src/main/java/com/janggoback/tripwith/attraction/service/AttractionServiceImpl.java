package com.janggoback.tripwith.attraction.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.janggoback.tripwith.attraction.domain.Attraction;
import com.janggoback.tripwith.attraction.exception.AttractionNotFoundException;
import com.janggoback.tripwith.attraction.repository.AttractionRepository;
import com.janggoback.tripwith.sido.domain.Sido;
import com.janggoback.tripwith.sido.exception.SidoNotFoundException;
import com.janggoback.tripwith.sido.repository.SidoRepository;
import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.trip.exception.TripNotFoundException;
import com.janggoback.tripwith.trip.repository.TripRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{

    private final AttractionRepository attractionRepository;
    private final SidoRepository sidoRepository;
    private final TripRepository tripRepository;
    

    @Cacheable(cacheNames = "getAttraction", key = "#id")
    @Override
    public Attraction getAttraction(Long id) {
        return attractionRepository.findById(id).orElseThrow(AttractionNotFoundException::new);
    }

    @Cacheable(cacheNames = "getAttractionList")
    @Override
    public Page<Attraction> getAttractionList(Pageable pageable) {
        return attractionRepository.findAll(pageable);
    }

	@Override
	public Page<Attraction> searchAttractionByName(String keyword, Pageable pageable) {
		return attractionRepository.findByNameContaining(keyword, pageable);
	}

    @Cacheable(cacheNames = "getAttractionList", key = "#sidoCode")
	@Override
	public Page<Attraction> searchAttractionByGugunCode(Long sidoCode, Long gugunCode, Pageable pageable) {
		return attractionRepository.findByAreaCodeAndSigunguCode(sidoCode, gugunCode, pageable);
	}

//	@Override
//	public List<Trip> getAllTripsWithAttraction(Long attractionId) {
//		List<Long> tripIdList = tripAttractionRepository.findByAttractionId(attractionId);
//		List<Trip> res = new ArrayList<>();
//		for(Long tripId : tripIdList) {
//			res.add(tripRepository.findById(tripId).orElseThrow(TripNotFoundException::new));
//		}
//		return res;
//	}

}
