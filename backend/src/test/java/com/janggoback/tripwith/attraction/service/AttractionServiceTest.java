package com.janggoback.tripwith.attraction.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.janggoback.tripwith.attraction.domain.Attraction;
import com.janggoback.tripwith.attraction.repository.AttractionRepository;
import com.janggoback.tripwith.sido.domain.Sido;
import com.janggoback.tripwith.sido.repository.SidoRepository;

@SpringBootTest
class AttractionServiceTest {
    @Autowired
    private AttractionService attractionService;
    @Autowired
    private AttractionRepository attractionRepository;
    @Autowired
    private SidoRepository areaCodeRepository;

    @BeforeEach
    void clean(){
        attractionRepository.deleteAll();
        attractionRepository.save(new Attraction("1Lsd","1L","1L","1L","1L",1L,1L,1L,LocalDateTime.now(),1.0,1.0));
        attractionRepository.save(new Attraction("1Lsd","1L","1L","1L","1L",1L,1L,1L, LocalDateTime.now(),1.0,1.0));
        attractionRepository.save(new Attraction("1Lsd","1L","1L","1L","1L",1L,1L,1L, LocalDateTime.now(),1.0,1.0));
        attractionRepository.save(new Attraction("서울시 종로구", "경복궁", "img1", "img2", "경복궁은...", 0L, 23L, 1L, LocalDateTime.now(), 1.0, 1.0));
        
        areaCodeRepository.save(new Sido("서울시", 1L, "종로구", 23L));
    }

    @Test
    @DisplayName("Attraction 1L개 조회")
    void test1(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Attraction> attractionList = attractionService.getAttractionList(pageRequest);
        for(Attraction attraction : attractionList){
            System.out.println("attraction = " + attraction.toString());
        }
    }
    
    @Test
    @DisplayName("Attraction 이름으로 검색")
    void searchByAttractionNameTest() {
    	PageRequest pageRequest = PageRequest.of(0, 1);
    	Page<Attraction> attractionList = attractionService.searchAttractionByName("경", pageRequest);
    	for(Attraction attraction : attractionList) {
    		assertThat(attraction.getName().contains("경"));
    	}
    }
    
    @Test
    @DisplayName("Attraction 위치로 검색")
    void searchByAttractionAreaTest() {
    	PageRequest pageRequest = PageRequest.of(0, 1);
    	Page<Attraction> attractionList = attractionService.searchAttractionByArea("종로", pageRequest);
    	Optional<Sido> areaCode = areaCodeRepository.findByAreaName("종로구");
    	for(Attraction attraction : attractionList) {
    		assertThat(attraction.getSigunguCode().equals(areaCode));
    	}
    }
}