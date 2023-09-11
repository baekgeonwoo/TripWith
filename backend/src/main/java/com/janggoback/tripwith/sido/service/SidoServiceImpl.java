package com.janggoback.tripwith.sido.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.janggoback.tripwith.sido.domain.Sido;
import com.janggoback.tripwith.sido.exception.SidoNotFoundException;
import com.janggoback.tripwith.sido.repository.GugunRepository;
import com.janggoback.tripwith.sido.repository.SidoRepository;
import com.janggoback.tripwith.sido.response.GugunResponse;
import com.janggoback.tripwith.sido.response.SidoResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SidoServiceImpl implements SidoService{
	private final SidoRepository sidoRepository;
	private final GugunRepository gugunRepository;

	@Override
	public List<SidoResponse> getAll() {
		return sidoRepository.findAll().stream().map((sido) -> {
			return SidoResponse.from(sido);
		}).collect(Collectors.toList());
	}

	@Override
	public List<GugunResponse> getGugunBySidoCode(Long sidoCode) {
		Sido sido = sidoRepository.findBySidoCode(sidoCode).orElseThrow(SidoNotFoundException::new);
		
		return gugunRepository.findBySido(sido).stream().map((gugun) -> {
			return GugunResponse.from(gugun);
		}).collect(Collectors.toList());
	}
}
