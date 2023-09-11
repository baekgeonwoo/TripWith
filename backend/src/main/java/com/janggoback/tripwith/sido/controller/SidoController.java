package com.janggoback.tripwith.sido.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.janggoback.tripwith.sido.service.SidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sidos")
@RequiredArgsConstructor
public class SidoController {
	private final SidoService sidoService;
	
	@GetMapping
	public ResponseEntity<?> getSidos(){
		return ResponseEntity.ok(sidoService.getAll());
	}
	
	@GetMapping("/{sido_code}")
	public ResponseEntity<?> getSigunguInfo(@PathVariable("sido_code") Long sidoCode){
		return ResponseEntity.ok(sidoService.getGugunBySidoCode(sidoCode));
	}
	
}
