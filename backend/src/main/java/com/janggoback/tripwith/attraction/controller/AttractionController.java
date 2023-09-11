package com.janggoback.tripwith.attraction.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.janggoback.tripwith.attraction.response.AttractionResponse;
import com.janggoback.tripwith.attraction.service.AttractionService;

import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
public class AttractionController {
    private final AttractionService attractionService;


    @GetMapping
    public ResponseEntity<?> getAttractionList(Pageable pageable) {
        return ResponseEntity.ok(attractionService.getAttractionList(pageable));
    }

//    @Cacheable(cacheNames = "getAttraction", key = "#attractionId")
    @GetMapping("/{attraction_id}")
    public ResponseEntity<?> getAttraction(@PathVariable("attraction_id") Long attractionId) {
        return ResponseEntity.ok(AttractionResponse.from(attractionService.getAttraction(attractionId)));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAttractionByName(@RequestParam String keyword, Pageable pageable){
    	return ResponseEntity.ok(attractionService.searchAttractionByName(keyword, pageable));
    }

    @GetMapping("/search/gugun")
    public ResponseEntity<?> getAttractionBySidoCode(@RequestParam("sidoCode") Long sidoCode, @RequestParam("gugunCode") Long gugunCode, Pageable pageable){
        log.info("getAttractionBySidoCode 호출");
    	return ResponseEntity.ok(attractionService.searchAttractionByGugunCode(sidoCode, gugunCode, pageable));
    }
    
//    @GetMapping("/{attraction_id}/trips")
//    public ResponseEntity<?> getTripsWithAttraction(@PathVariable("attraction_id") Long attractionId){
//    	return ResponseEntity.ok(attractionService.getAllTripsWithAttraction(attractionId));
//    }
}
