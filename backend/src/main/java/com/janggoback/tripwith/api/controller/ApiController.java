package com.janggoback.tripwith.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.janggoback.tripwith.api.service.ApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
	private final ApiService apiService;
	
	@GetMapping("/load/attractions")
	public ResponseEntity<?> loadAttractionData(@RequestParam("dataCnt") Long dataCnt) {
		apiService.loadAttractions(dataCnt);
		
		return ResponseEntity.ok().build();
	}
}