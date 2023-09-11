package com.janggoback.tripwith.sido.service;

import java.util.List;

import com.janggoback.tripwith.sido.response.GugunResponse;
import com.janggoback.tripwith.sido.response.SidoResponse;

public interface SidoService {
	List<SidoResponse> getAll();
	List<GugunResponse> getGugunBySidoCode(Long sidoCode);
}
